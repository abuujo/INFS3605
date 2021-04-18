package service;

import java.sql.SQLException;
import java.util.UUID;

import dao.UserDAO;
import dto.User;
import exception.*;
import notification.NotificationEmail;

public class UserService {

	/**
	 * Coordinator login function
	 * @param email
	 * @param password
	 * @throws NoSuchEmailException
	 * @throws InvalidPasswordException 
	 * @throws AccountLockedException 
	 * @ 
	 */
	public static User login(String email, String password) 
			throws NoSuchEmailException, InvalidPasswordException, AccountLockedException {
		User user = UserDAO.selectByEmail(email);
		
		if(user == null) {
			throw new NoSuchEmailException();
		}  else if(user.isLocked()) {
			System.out.println("USER ACCOUNT LOCKED");
			throw new AccountLockedException();
		} else if(!user.getPassword().equals(password)) {
			
			int failed = user.getLoginAttempt();
			failed += 1;
			user.setLoginAttempt(failed);

			System.out.println("Login Attempt: "+user.getLoginAttempt());
			
			UserDAO.addOrUpdate(user);

			if(failed > 3) {
				lockUser(user);
				throw new AccountLockedException();
			} else {
				throw new InvalidPasswordException();
			}
		}
		return user;
	}
	
	public static void register(User user) throws DuplicateEmailException {
		User exist = UserDAO.selectByEmail(user.getEmail());
		if(exist != null) {
			throw new DuplicateEmailException();
		}
		
		// Otherwise, add
		UserDAO.add(user);
	}
	
	public static int getLoginAttempt(User user) {
		User usr = UserDAO.selectById(user.getUserId());
		return usr.getLoginAttempt();
	}
	
	public static void lockUser(User user) {
		user.setLocked(true);
		UserDAO.addOrUpdate(user);
		
		// Generate temporary password
		String temporary = UUID.randomUUID().toString();
		user.setPassword(temporary);
		
		// Generate unlockCode
		String unlockCode = UUID.randomUUID().toString();
		user.setUnlockCode(unlockCode);
		
		UserDAO.update(user);
		
		// Send verification email
		NotificationEmail.sendVerificationEmail(user);
	}
	
	public static void unlockUser(User user) {
		user.setLocked(false);
		user.setUnlockCode(null);
		user.setLoginAttempt(0);
		UserDAO.addOrUpdate(user);
	}
	
	public static void resetPassword(User user) {
		// Generate temporary password
		String temporary = UUID.randomUUID().toString();
		user.setPassword(temporary);
		
		UserDAO.update(user);
		
		// send email with temporary password
		NotificationEmail.sendRecoveryEmail(user);
	}
	
}


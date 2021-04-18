package notification;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.StudentDAO;
import dao.UserDAO;
import dao.AttendeeDAO;
import dao.BookingDAO;
import dao.NoteDAO;
import dto.Note;

import dto.Booking;
import dto.Student;
import dto.User;


public class NotificationEmail {
	
	public static void sendRecoveryEmail(User user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date current = new Date();
		
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "fourleavestechnology3605@gmail.com";
		String password = "weDeserveHD";
		
		String mailTo = user.getEmail();
		String subject = "Temporary Password";
		String message =
				"<font face='Arial'>"
				+ "<table width='600' max-height='100%' border='3'>"
				+ "<tr>"
					+ "<td colspan='2' bgcolor=Gold align='center'>"
						+ "<img style='width: 120px; height:100px' src='https://coursera-university-assets.s3.amazonaws.com/6c/b759685fe8d8dcb491ac80208ca2b7/unsw_logo_360x360.png' align='left'>"
						+ "<h1> INFS Undergraduate Coordinator System</h1>"
					+ "</td>"
				+ "</tr>"
				+ "<tr>"
					+ "<td colspan='1' bgcolor=Black align='center'>"
						+ "<font size=2 color='white'>"
							+ "<p> Account Locked Notification </p>"
						+ "</font>"
					+ "</td>"
				+ "</tr>"
				+ "<tr valign='center'>"
					+ "<td bgcolor='#eee' width='78%' height='78%'>" + "<br />"
						+ "<p align='right'>" + sdf.format(current) + "</p>"
						+ "<br />"
						+ "<p> Dear " + user.getFirstName()+ "</p><br />"
							+ "Your password has been reset.<br />"
							+ "Please login with temporary password provided.<br />"
							+ "Once you logged in, please change your password as soon as possible<br />"
							+ "Temporary Password: " + user.getPassword() + "<br />"
						+"</p>"
						+"<p> Kindly Regards, </p>"
						+"<p> UGCIS </p>"
					+ "</td>"
				+"</tr>"
				+"<tr>"
					+ "<td colspan='2' bgcolor=Black align='left'>"
						+ "<font size=1 color='white'>"
							+ "<p> UNSW AUSTRALIA  |  UNSW SYDNEY NSW 2052 AUSTRALIA</p>"
						+ "</font>"
						+ "<font size=1 color='white'>"
							+ "<p> ABN 57 195 873 179  |  CRICOS Provider Code 00098G</p>"
						+ "</font>"
					+ "</td>"
				+ "</tr>"
				+ "</table>"
				+ "</font>";

		try {
			sendEmail(host, port, mailFrom, password, mailTo, subject, message);
			System.out.println("Email sent to user.");
		} catch (Exception ex) {
			System.out.println("Failed to send email.");
			ex.printStackTrace();
		}		
	}
	
	public static void sendVerificationEmail(User user) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date current = new Date();
		
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "fourleavestechnology3605@gmail.com";
		String password = "weDeserveHD";
		
		String mailTo = user.getEmail();
		String subject = "Account Verification";
		String message =
				"<font face='Arial'>"
				+ "<table width='600' max-height='100%' border='3'>"
				+ "<tr>"
					+ "<td colspan='2' bgcolor=Gold align='center'>"
						+ "<img style='width: 120px; height:100px' src='https://coursera-university-assets.s3.amazonaws.com/6c/b759685fe8d8dcb491ac80208ca2b7/unsw_logo_360x360.png' align='left'>"
						+ "<h1> INFS Undergraduate Coordinator System</h1>"
					+ "</td>"
				+ "</tr>"
				+ "<tr>"
					+ "<td colspan='1' bgcolor=Black align='center'>"
						+ "<font size=2 color='white'>"
							+ "<p> Account Locked Notification </p>"
						+ "</font>"
					+ "</td>"
				+ "</tr>"
				+ "<tr valign='center'>"
					+ "<td bgcolor='#eee' width='78%' height='78%'>" + "<br />"
						+ "<p align='right'>" + sdf.format(current) + "</p>"
						+ "<br />"
						+ "<p> Dear " + user.getFirstName()+ "</p><br />"
							+ "Your account has been locked, since you failed your password more than 3 times.<br />"
							+ "Please unlock your account by typing in unlock code in the following link, and login with temporary password provided.<br />"
							+ "Link: <a href='http://localhost:8080/FourLeavesTech/verification.jsp'>http://localhost:8080/FourLeavesTech/verification.jsp</a>" + "<br />"
							+ "Unlock Code: " + user.getUnlockCode() + "<br /><br />"
							+ "Once you logged in, please change your password as soon as possible<br />"
							+ "Temporary Password: " + user.getPassword() + "<br />"
						+"</p>"
						+"<p> Kindly Regards, </p>"
						+"<p> UGCIS </p>"
					+ "</td>"
				+"</tr>"
				+"<tr>"
					+ "<td colspan='2' bgcolor=Black align='left'>"
						+ "<font size=1 color='white'>"
							+ "<p> UNSW AUSTRALIA  |  UNSW SYDNEY NSW 2052 AUSTRALIA</p>"
						+ "</font>"
						+ "<font size=1 color='white'>"
							+ "<p> ABN 57 195 873 179  |  CRICOS Provider Code 00098G</p>"
						+ "</font>"
					+ "</td>"
				+ "</tr>"
				+ "</table>"
				+ "</font>";

		try {
			sendEmail(host, port, mailFrom, password, mailTo, subject, message);
			System.out.println("Email sent to user.");
		} catch (Exception ex) {
			System.out.println("Failed to send email.");
			ex.printStackTrace();
		}
	}
	
public static void sendStudentNote(Booking booking, Student student) throws AddressException {
	
		List<Student> attendees = AttendeeDAO.selectByBookingId(booking.getBookingId());
		List<Note> notes = NoteDAO.selectAllByBookingId(booking.getBookingId());
		
		StringBuilder noteString = new StringBuilder();
		noteString.append("Notes: <br />");
		for(Note n: notes){
			noteString.append(n.getTexts() + "<br />");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date current = new Date();
		
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "fourleavestechnology3605@gmail.com";
		String password = "weDeserveHD";
		
		String names = student.getFirstName();
		String mailTo = student.getEmail();
		String[] recipientList = null;
		
		if(attendees != null) {
			for(Student s : attendees) {
				mailTo += ", "+s.getEmail();
				names += ", "+s.getFirstName();
			}
			recipientList = mailTo.split(",");
		} else {
			recipientList[0] = mailTo;
		}
		
		InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
		int counter = 0;
		for(String mail : recipientList) {
			recipientAddress[counter] = new InternetAddress(mail.trim());
			counter++;
		}
		String subject = "Notes made for appointment: " + booking.getStartDate();
		String message =
				
				
				"<font face='Arial'>"
				+ "<table width='600' max-height='100%' border='3'>"
				+ "<tr>"
					+ "<td colspan='2' bgcolor=Gold align='center'>"
						+ "<img style='width: 120px; height:100px' src='https://coursera-university-assets.s3.amazonaws.com/6c/b759685fe8d8dcb491ac80208ca2b7/unsw_logo_360x360.png' align='left'>"
						+ "<h1> INFS Undergraduate Coordinator System</h1>"
					+ "</td>"
				+ "</tr>"
				+ "<tr>"
					+ "<td colspan='1' bgcolor=Black align='center'>"
						+ "<font size=2 color='white'>"
							+ "<p> Notes for student appointment </p>"
						+ "</font>"
					+ "</td>"
				+ "</tr>"
				+ "<tr valign='center'>"
					+ "<td bgcolor='#eee' width='78%' height='78%'>" + "<br />"
						+ "<p align='right'>" + sdf.format(current) + "</p>"
						+ "<br />"
						+ "<p> Dear " + names+ "</p><br />"
							+ "The coordinator has elected to share the notes made during your appointment.<br />"
							+ "The following are all the notes made: <br />"
							+ noteString					
						+"</p>"
						+"<p> Kindly Regards, </p>"
						+"<p> UGCIS </p>"
					+ "</td>"
				+"</tr>"
				+"<tr>"
					+ "<td colspan='2' bgcolor=Black align='left'>"
						+ "<font size=1 color='white'>"
							+ "<p> UNSW AUSTRALIA  |  UNSW SYDNEY NSW 2052 AUSTRALIA</p>"
						+ "</font>"
						+ "<font size=1 color='white'>"
							+ "<p> ABN 57 195 873 179  |  CRICOS Provider Code 00098G</p>"
						+ "</font>"
					+ "</td>"
				+ "</tr>"
				+ "</table>"
				+ "</font>";

		try {
			sendMultipleEmail(host, port, mailFrom, password, recipientAddress, subject, message);
			System.out.println("Email sent to user.");
		} catch (Exception ex) {
			System.out.println("Failed to send email.");
			ex.printStackTrace();
		}
	}
	
	public static void sendEmailStudent(String email, Booking booking) {

		Student student = StudentDAO.selectByEmail(email);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date current = new Date();
		
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "fourleavestechnology3605@gmail.com";
		String password = "weDeserveHD";
		
		String mailTo = email;
		String subject = "Consultation Reminder";
		String message = 
						"<font face='Arial'>"
						+ "<table width='600' max-height='100%' border='3'>"
						+ "<tr>"
							+ "<td colspan='2' bgcolor=Gold align='center'>"
								+ "<img style='width: 120px; height:100px' src='https://coursera-university-assets.s3.amazonaws.com/6c/b759685fe8d8dcb491ac80208ca2b7/unsw_logo_360x360.png' align='left'>"
								+ "<h1> INFS Undergraduate Coordinator System</h1>"
							+ "</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td colspan='1' bgcolor=Black align='center'>"
								+ "<font size=2 color='white'>"
									+ "<p> Upcoming Booking Reminder Alert </p>"
								+ "</font>"
							+ "</td>"
						+ "</tr>"
						+ "<tr valign='center'>"
							+ "<td bgcolor='#eee' width='78%' height='78%'>" + "<br />"
								+ "<p align='right'>" + sdf.format(current) + "</p>"
								+ "<br />"
								+ "<p> Dear " + student.getFirstName()+ "</p><br />"
									+ "You have an appointment with the Undergraduate Coordinator.<br />"
									+ "Consulation time: " + booking.getStartDate() + " ~ "+ booking.getEndDate().substring(11)+"<br />"
									+ "Location: " + booking.getLocation() + "<br />"
									+ "Consultation category: " + booking.getCategory() + "<br />"
								+"</p>"
								+"<p> Kindly Regards, </p>"
								+"<p> UGCIS </p>"
							+ "</td>"
						+"</tr>"
						+"<tr>"
							+ "<td colspan='2' bgcolor=Black align='left'>"
								+ "<font size=1 color='white'>"
									+ "<p> UNSW AUSTRALIA  |  UNSW SYDNEY NSW 2052 AUSTRALIA</p>"
								+ "</font>"
								+ "<font size=1 color='white'>"
									+ "<p> ABN 57 195 873 179  |  CRICOS Provider Code 00098G</p>"
								+ "</font>"
							+ "</td>"
						+ "</tr>"
						+ "</table>"
						+ "</font>";
		
		try {
			sendEmail(host, port, mailFrom, password, mailTo, subject, message);
			System.out.println("Email sent to student.");
		} catch (Exception ex) {
			System.out.println("Failed to send email.");
			ex.printStackTrace();
		}
		
	}
	
	public static void sendEmailStudentUpdate(String email, Booking booking) {

		Student student = StudentDAO.selectByEmail(email);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date current = new Date();
		
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "fourleavestechnology3605@gmail.com";
		String password = "weDeserveHD";
		
		String mailTo = email;
		String subject = "Consultation Update Notification:";
		String message = 
				"<font face='Arial'>"
				+ "<table width='600' max-height='100%' border='3'>"
				+ "<tr>"
					+ "<td colspan='2' bgcolor=Gold align='center'>"
						+ "<img style='width: 120px; height:100px' src='https://coursera-university-assets.s3.amazonaws.com/6c/b759685fe8d8dcb491ac80208ca2b7/unsw_logo_360x360.png' align='left'>"
						+ "<h1> INFS Undergraduate Coordinator System</h1>"
					+ "</td>"
				+ "</tr>"
				+ "<tr>"
					+ "<td colspan='1' bgcolor=Black align='center'>"
						+ "<font size=2 color='white'>"
							+ "<p> Booking Updated Reminder Alert </p>"
						+ "</font>"
					+ "</td>"
				+ "</tr>"
				+ "<tr valign='center'>"
					+ "<td bgcolor='#eee' width='78%' height='78%'>"
						+ "<p align='right'>" + sdf.format(current) + "</p>"
						+ "<p> Dear " + student.getFirstName()+ ",\n" +"</p>"
						+ "<br />"
						+ "<p>"
							+ "You have an appointment with the Undergraduate Coordinator.<br />"
							+ "Consulation time: " + booking.getStartDate() + " ~ "+ booking.getEndDate().substring(11)+ "<br />"
							+ "Location: " + booking.getLocation()+ "<br />"
							+ "Consultation category: " + booking.getCategory()+ "<br />"
						+"</p>"
						+"<p> Kindly Regards, </p>"
						+"<p> UGCIS </p>"
					+ "</td>"
				+"</tr>"
				+"<tr>"
					+ "<td colspan='2' bgcolor=Black align='left'>"
						+ "<font size=1 color='white'>"
							+ "<p> UNSW AUSTRALIA  |  UNSW SYDNEY NSW 2052 AUSTRALIA</p>"
						+ "</font>"
						+ "<font size=1 color='white'>"
							+ "<p> ABN 57 195 873 179  |  CRICOS Provider Code 00098G</p>"
						+ "</font>"
					+ "</td>"
				+ "</tr>"
				+ "</table>"
				+ "</font>";
		
		try {
			sendEmail(host, port, mailFrom, password, mailTo, subject, message);
			System.out.println("Email sent to student.");
		} catch (Exception ex) {
			System.out.println("Failed to send email.");
			ex.printStackTrace();
		}
		
	}
	
	public static void sendEmailStudentDelete(String email, Booking booking) {

		Student student = StudentDAO.selectByEmail(email);
		
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "fourleavestechnology3605@gmail.com";
		String password = "weDeserveHD";
		
		String mailTo = email;
		String subject = "Consultation Removed Notification:";
		String message = "Hi " + student.getFirstName() + ",<br />" +
				"Your appointment with the Undergraduate Coordinator has been terminated";
		
		try {
			sendEmail(host, port, mailFrom, password, mailTo, subject, message);
			System.out.println("Email sent to student.");
		} catch (Exception ex) {
			System.out.println("Failed to send email.");
			ex.printStackTrace();
		}
		
	}
	
public static void sendEmailCoordinator(User coordinator, Booking booking){

		
		
		String host = "smtp.gmail.com";
		String port = "587";
		String username = "fourleavestechnology3605@gmail.com";
		String password = "weDeserveHD";
		
		String mailTo = coordinator.getEmail();
		String subject = "Consultation Reminder";
		String message = "Hi " + coordinator.getFirstName() + ",<br />" +
				"You have an appointment with a student." + "<br />" +
				"Consulation time: " + booking.getStartDate() + "<br />" +
				"Location: " + booking.getLocation() + "<br />" +
				"Consultation category: " + booking.getCategory();
		
		try {
			sendEmail(host, port, username, password, mailTo, subject, message);
			System.out.println("Email sent to coordinator.");
		} catch (Exception ex) {
			System.out.println("Failed to send email.");
			ex.printStackTrace();
		}
		
	}
	
	public static void sendEmail(String host, String port, final String userName, final String password,
			String toAddress, String subject, String message)
					throws AddressException, MessagingException, UnsupportedEncodingException {

		// sets SMTP server properties
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		String username = "fourleavestechnology3605@gmail.com";
		

			class GMailAuthenticator extends Authenticator {
				String user;
				String pw;
				public GMailAuthenticator (String username, String password)
				{
					super();
					this.user = username;
					this.pw = password;
				}
				public PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(user, pw);
				}
			}


		Session session = Session.getInstance(props, new GMailAuthenticator(username, password));

		// creates a new e-mail message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(userName, "UGCIS"));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		// set plain text message
		//msg.setText(message);
		msg.setContent(message, "text/html; charset=utf-8");

		// sends the e-mail
		Transport.send(msg);
	}

	
	public static void sendMultipleEmail(String host, String port, final String userName, final String password,
			Address[] toAddress, String subject, String message)
					throws AddressException, MessagingException, UnsupportedEncodingException {

		// sets SMTP server properties
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		String username = "fourleavestechnology3605@gmail.com";
		

			class GMailAuthenticator extends Authenticator {
				String user;
				String pw;
				public GMailAuthenticator (String username, String password)
				{
					super();
					this.user = username;
					this.pw = password;
				}
				public PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(user, pw);
				}
			}


		Session session = Session.getInstance(props, new GMailAuthenticator(username, password));

		// creates a new e-mail message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(userName, "UGCIS"));
		//InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddress);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		// set plain text message
		//msg.setText(message);
		msg.setContent(message, "text/html; charset=utf-8");

		// sends the e-mail
		Transport.send(msg);
	}
}

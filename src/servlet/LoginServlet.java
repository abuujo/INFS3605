package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.User;
import exception.AccountLockedException;
import exception.InvalidPasswordException;
import exception.NoSuchEmailException;
import service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get request... just show the index
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		
		
		try {
			User user = UserService.login(email, password);
			if(!user.isLocked()) {
				request.getSession().setAttribute("user", user);
				
				user.setLoginAttempt(0);
				UserDAO.addOrUpdate(user);
				
				response.sendRedirect("home");
			}
		} catch(NoSuchEmailException e) {
			request.setAttribute("error", "No such email");
			rd.forward(request, response);
		} catch (AccountLockedException e) {
			request.setAttribute("error", "Your Account is Locked, please check your email");
			
			User locked = UserDAO.selectByEmail(email);
			request.setAttribute("locked", locked);
			
			rd.forward(request, response);
		} catch(InvalidPasswordException e) {			
			User invalid = UserDAO.selectByEmail(email);
			int attempt = invalid.getLoginAttempt();
			
			request.setAttribute("error", "Incorrect password(Login Attempt: "+attempt+"/3)");
			rd.forward(request, response);
		}
		
	}

}


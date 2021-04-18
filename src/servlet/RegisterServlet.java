package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.User;
import exception.DuplicateEmailException;
import service.UserService;
import util.ValidateEmail;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// display registration form
		request.setAttribute("attempt", false);  // flag for whether this was a reg attempt
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Post request: handle the reg attempt
		request.setAttribute("attemp", false);
		
		// New user
		User newUser = null;
		
		// Create errors list
		List<String> errors = new ArrayList<String>();
		
		// Fetch input params
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String type = request.getParameter("type");
		
		
		// validate input params
		if (email == null || email.equals("") ||
				password == null || password.equals("") ||
				type == null || type.equals("")) { // check if any of the crucial 3 fields are empty
			errors.add("Email,password and type fields are all necessary.");
		}
		
		if (!ValidateEmail.isValid(email)) {
			errors.add("The e-mail address is invalid.");
		} else {
			if (UserDAO.selectByEmail(email) != null) {
				errors.add("The e-mail address is already in use.");
			}
		}	
		if (errors.isEmpty()) { // create new user if no errors
	    	newUser = new User();
	    	newUser.setEmail(email);
	    	newUser.setPassword(password);
	    	newUser.setFirstName(firstName);
	    	newUser.setLastName(lastName);
	    	newUser.setType(type);
	        
	        try {
	        	UserService.register(newUser);
	        	
	        } catch (DuplicateEmailException due) {
	        	System.err.println("Attempted to register a duplicate email, this shouldn't occur...");
	        	due.printStackTrace(System.err);
	        }
	        
	        request.setAttribute("registered", "register success");
		}
        
        request.setAttribute("attempt", true); // flag for whether this was a registration attempt
        request.setAttribute("errors", errors);
        
        //request.setAttribute("newUser", newUser); // needed for verify part
        request.setAttribute("activeTab", "registerTab");
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
		
	}

}

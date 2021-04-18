package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.User;
import service.UserService;

/**
 * Servlet implementation class Recovery
 */
public class RecoveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecoveryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("attempt", false);
		RequestDispatcher rd = request.getRequestDispatcher("passwordRecovery.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("attempt", false);
		
		List<String> errors = new ArrayList<String>();
		
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String type = request.getParameter("type");
		
		if(email == null || email.equals("") ||
				firstName == null || firstName.equals("") ||
				lastName == null || lastName.equals("") ||
				type == null || type.equals("")) {
				errors.add("All fields are necessary");
		}
		User user = UserDAO.selectByEmail(email);
		if(user == null) {
			errors.add("Email address not matched");
		} else { 
			if(!user.getFirstName().toLowerCase().equals(firstName.toLowerCase())) {
				errors.add("First name not matched");
			}
			if(!user.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
				errors.add("Last name not matched");
			}
			if(!user.getType().equals(type)) {
				errors.add("Type not matched");
			}
		}
		
		if(errors.isEmpty()) {
			//send password reset email
			UserService.resetPassword(user);
			request.setAttribute("recovery", "recovery mail sent");
		}
		
		request.setAttribute("attempt", true);
		request.setAttribute("errors", errors);
		
		RequestDispatcher rd = request.getRequestDispatcher("passwordRecovery.jsp");
		rd.forward(request, response);
	}

}

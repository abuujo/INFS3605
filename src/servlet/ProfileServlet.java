package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingDAO;
import dao.UserDAO;
import dao.NoteDAO;
import dto.Booking;
import dto.User;
import dto.Note;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {	// if not logged in
			response.sendRedirect("index");	// redirect to index - login page
			return;
		}
		
		User updated = updated = UserDAO.selectById(user.getUserId());
		
		request.setAttribute("user", updated);
		RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) { // we're not logged in
			response.sendRedirect("index"); // redirect to index - login page
			return;
		}
		
		String flag = request.getParameter("flag");
		User update = null;
		
		if(flag != null && flag.equals("update")) {
			
			update = UserDAO.selectById(user.getUserId());
			
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			
			update.setEmail(email);			
			update.setPassword(password);
			update.setFirstName(firstName);
			update.setLastName(lastName);
			
			UserDAO.update(update);
			
			System.out.println("Coordinator profile updated");
			//request.setAttribute("user", update);

		}
		
		//response.sendRedirect("./profile");
		doGet(request, response);
	}

}

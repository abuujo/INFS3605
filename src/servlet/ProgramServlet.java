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

import dto.User;
import dto.Program;
import service.ProgramService;
import service.StudentService;

/**
 * Servlet implementation class ProgramServlet
 */
public class ProgramServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgramServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null) {	// if not logged in
			System.out.println("USER SESSION IS NULL");
			response.sendRedirect("index");	// redirect to index - login page
			return;
		}
		
		//Search handling
		String query = request.getParameter("search");
		//TEST
		System.out.println("Search program Query="+ query);
				
		if(query != null) {
			List<Program> result = result = ProgramService.getProgramsBySearch(query);
			
			request.setAttribute("programs", result);
			
		} else {
			// List of programs
			List<Program> programs = ProgramService.getAllPrograms();
			
			request.setAttribute("programs", programs);
		}		
		
		
		request.setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("program.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

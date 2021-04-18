package servlet;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

import dto.Student;
import dto.User;
import dto.Sponsor;
import dto.Preference;
import dao.StudentDAO;
import dao.PreferenceDAO;
import dao.SponsorDAO;

import notification.PreferenceEmail;
import service.StudentService;

/**
 * Servlet implementation class BookingServlet
 */
public class EmailPreferenceServlet extends HttpServlet {
	
	
	
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailPreferenceServlet() {
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
		
		
		// display registration form
		request.setAttribute("attempt", false);  // flag for whether this was a reg attempt
		RequestDispatcher rd = request.getRequestDispatcher("emailPreferences.jsp");
		
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {	// if not logged in
			response.sendRedirect("index");	// redirect to index - login page
			return;
		}
		
		List<String> errors = new ArrayList<String>();
		
		String ipType = request.getParameter("ipType");
		String info = request.getParameter("details");
		
		if(ipType == null || ipType.equals("") ||
				info == null || info.equals("")){
			errors.add("Student's information, Category and Date fields are necessary.");
		}
		
		if(errors.isEmpty()) {
			List<Student> students = StudentService.getStudentList();
			for(Student s: students){
				
				if(s.getType().equals("Co-op") && s.getEmail() != null) {
					PreferenceEmail.sendPreferenceFormEmail(s, info, ipType);							
				}
			}
		}
		
		request.setAttribute("attempt", true);
		request.setAttribute("errors", errors);
		//request.setAttribute("user", user);
		
		RequestDispatcher rd = request.getRequestDispatcher("emailPreferences.jsp");
		rd.forward(request, response);
	}

}
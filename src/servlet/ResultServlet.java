package servlet;

import java.io.IOException;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EnrolmentDAO;
import dao.StudentDAO;
import dto.User;
import dto.Course;
import dto.Enrolment;
import service.EnrolmentService;
import util.Helper;

/**
 * Servlet implementation class ResultServlet
 */
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
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
		
		List<String> semYearList = EnrolmentService.getSemYearList();
		request.setAttribute("semYearList", semYearList);
		
		String selected = request.getParameter("selected");
		
		if(selected != null) {
			//TEST
			System.out.println("SemYear: "+selected);
			request.setAttribute("selected", selected);
			
			// All enrolments taken in the selected Year/Semester
			List<Enrolment> results = EnrolmentService.getEnrolmentsBySemYear(selected);
			EnrolmentService.setCourseCode(results);
			EnrolmentService.setzId(results);
			Collections.sort(results);
			
			// Filtered result of enrolments
			Map<String, List<Map<String, Integer>>> filtered = EnrolmentService.getFilteredResults(results);
			request.setAttribute("filtered", filtered);
			
			// averages of students for semester
			Map<String, Double> avgs = EnrolmentService.setAverage(filtered);
			// sort map by values
			avgs = EnrolmentService.sortByValue(avgs);
			request.setAttribute("avgs", avgs);
			//TEST
			//System.out.println(avgs);
			
			Map<String, Integer> ids = EnrolmentService.setStudentId(filtered);
			request.setAttribute("ids", ids);
			
			//all courses provided in that year/semester
			List<String> courses = EnrolmentService.getCoursesBySemYear(selected);
			request.setAttribute("courses", courses);

			
		}
		
		/*
		String filter = request.getParameter("filter");
		List<Enrolment> results = null;
		
		if(filter == null || filter.equals("DEFAULT")) {
			results = EnrolmentService.getAllCoops();
		} else if(filter.equals("FL")) {
			results = EnrolmentService.getFLgrades();
		} else if(filter.equals("PS")) {
			results = EnrolmentService.getPSgrades();
		} else if(filter.equals("CR")) {
			results = EnrolmentService.getCRgrades();
		} else if(filter.equals("DN")) {
			results = EnrolmentService.getDNgrades();
		} else if(filter.equals("HD")) {
			results = EnrolmentService.getHDgrades();
		}
		EnrolmentService.setCourseCode(results);
		EnrolmentService.setzId(results);
		
		
		request.setAttribute("results", results);*/
		request.setAttribute("user", user);
		
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
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

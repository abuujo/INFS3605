package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingDAO;
import dao.BookingListDAO;
import dao.NoteDAO;
import dao.PreferenceDAO;
import dao.ProgramDAO;
import dao.StudentDAO;
import dto.Booking;
import dto.User;
import dto.Note;
import dto.Preference;
import dto.Program;
import dto.Student;
import notification.NotificationEmail;
import service.BookingService;
import service.StudentService;

/**
 * Servlet implementation class StudentsListServlet
 */
public class StudentsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentsListServlet() {
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
		System.out.println("Search student Query="+ query);
				
		if(query != null) {
			List<Student> result = StudentService.getStudentsBySearch(query);
			
			request.setAttribute("students", result);
			
		} else {
			// List of students
			List<Student> students = StudentService.getStudentList();
			
			request.setAttribute("students", students);
		}		

		
		request.setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("studentsList.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		
		String studentId = request.getParameter("studentId");
		Student del = null;
		String flag = request.getParameter("flag");
		
		if(flag.equals("deleteStudent")) {
			del = StudentDAO.selectById(Integer.parseInt(studentId));
			
			// Remove booking from bookingList
			BookingListDAO.deleteByStudentId(Integer.parseInt(studentId));
			
			
			// Remove booking
			//Booking deleteBooking = BookingDAO.selectAllByZid(del.getzId());
			
			
			// Delete student's preference, before delete student
			List<Preference> preferences = PreferenceDAO.selectByStudentId(Integer.parseInt(studentId));
			for(Preference p : preferences) {
				PreferenceDAO.delete(p);
			}
			
			// Now, delete the student
			StudentDAO.delete(del);
			System.out.println("DELETED STUDENT");
			
		}
		
		doGet(request, response);
	}

}
package servlet;

import java.io.IOException;
import com.dhtmlx.demo.*;
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

import dao.BaseDAO;
import dao.BookingDAO;
import dao.BookingListDAO;
import dao.StudentDAO;
import dao.CourseDAO;
import dao.ProgramDAO;
import dto.Booking;
import dto.User;
import dto.Student;
import dto.Course;
import dto.Program;
import service.BookingService;
import service.StudentService;
import service.CourseService;
import service.ProgramService;
import notification.NotificationEmail;

/**
 * Servlet implementation class BookingServlet
 */
public class BookingServlet extends HttpServlet {
	

	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
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
		
		List<String> progList = ProgramService.getAllProgramNames(ProgramDAO.selectAll());
		
		request.setAttribute("progList", progList);
		
		// display registration form
		request.setAttribute("attempt", false);  // flag for whether this was a reg attempt
		RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
		
		Booking booking = new Booking();
		
		try{
		String sql = "SELECT * FROM events";
			Connection conn = BaseDAO.getDBConnection();
			PreparedStatement ps = null;
			ResultSet result = null;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs != null){
				while(rs.next()){
					String start_date = rs.getString("start_date");
					String end_date = rs.getString("end_date");
					System.out.println(end_date);
					
					booking.setBookingId(10000);
					booking.setStartDate(start_date);
					booking.setEndDate(end_date);
					
					request.setAttribute("booking", booking);
					
					
				}
			}
		
		} catch(SQLException e){
			e.printStackTrace();
		}
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
		
		try{
			String sql = "DELETE FROM events";
			Connection conn = BaseDAO.getDBConnection();
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
		// New Booking
		Booking newBooking = null;
		Student newStudent = null;
		Course newCourse = null;
		
		// Create errors list
		List<String> errors = new ArrayList<String>();
		
		// fetch input params
		String stdType = request.getParameter("studentType");
		String zId = request.getParameter("zId");
		String stdFirstName = request.getParameter("studentFirstName");
		String stdLastName = request.getParameter("studentLastName");
		String stdEmail = request.getParameter("studentEmail");
		String stdProgram = request.getParameter("studentProgram");		
		String category = request.getParameter("category");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String location = request.getParameter("location");
		String status = request.getParameter("status");
		String stringPriority = request.getParameter("priority");
				
		// validate input params
		if(zId == null || zId.equals("") || stdFirstName == null || stdFirstName.equals("") ||
				stdLastName == null || stdLastName.equals("") || stdEmail == null || stdEmail.equals("") ||
				stdProgram == null || stdProgram.equals("") || category == null || category.equals("") ||
				startDate == null || startDate.equals("") || endDate == null || endDate.equals("") || location == null || location.equals("") ||
				status == null || status.equals("")) {
			errors.add("Student's information, Category and Date fields are necessary.");
		}
		
		if(zId != null) {
			if(!zId.matches("^[zZ][0-9]{7}")) { // z1234567 or Z1234567 format
				errors.add("Student's zID format must match z1234567 or Z1234567");
			}
		}
		
		if(stringPriority != null) {
			if(!stringPriority.matches("^[1-9]$")) {	// priority 1 ~ 9 (low to high)
				errors.add("Priority must be 1 ~ 9(Low to High)");
			}
		}
		
		try {
			if(!BookingService.isDateValid(startDate, endDate)) {
				errors.add("EndDate is earlier than StartDate");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Date validity test
		
		
		if(errors.isEmpty()) {
			newBooking = new Booking();
			newBooking.setzId(zId);
			newBooking.setCategory(category);
			newBooking.setStartDate(startDate);
			newBooking.setEndDate(endDate);
			newBooking.setLocation(location);
			newBooking.setStatus(status);
			newBooking.setPriority(Integer.parseInt(stringPriority));
			
			// add new booking
			BookingService.addBooking(newBooking);
			
			
		
			// if student not exists, add new
			Student bookingStudent = null;

				if(StudentDAO.selectByZId(zId) == null) {
					//add new student
					newStudent = new Student();
					newStudent.setType(stdType);
					newStudent.setzId(zId);
					newStudent.setFirstName(stdFirstName);
					newStudent.setLastName(stdLastName);
					newStudent.setEmail(stdEmail);
					
					Program exist = ProgramDAO.selectByProgramName(stdProgram);
					if(exist != null) {
						newStudent.setProgramId(exist.getProgramId());
					} else {
						errors.add("NO SUCH PROGRAM");
					}
					
					// Add new student
					StudentService.addStudent(newStudent);
					bookingStudent = newStudent;
					
				} else {
					System.out.print("STUDENT NOT ADDED TO DATABASE: ALREADY EXISTS");
					bookingStudent = StudentDAO.selectByZId(zId);
				}

			
			// Notification Email
			if(bookingStudent != null) {
				NotificationEmail.sendEmailStudent(stdEmail, newBooking);
			}
			
			NotificationEmail.sendEmailCoordinator(user, newBooking);
			
			request.setAttribute("bookingSuccess", "Booking has been added");
			
			// Needed for bookingList
			Booking booking = BookingDAO.selectByAllAttributes(zId, category, 
						startDate, endDate, location, 
						status, Integer.parseInt(stringPriority));
		
			if(booking != null) {
				BookingListDAO.add(booking.getBookingId(), 
							StudentDAO.selectByZId(zId).getStudentId(), 
							user.getUserId());
				
			} else {
				System.out.println("ERROR: Booking Not Exists!");
			}
			
		} else {
			System.out.print("ERROR OCCURRED: BOOKING NOT ADDED");
		}
		
		// or Update existing booking
		
		request.setAttribute("attempt", true);
		request.setAttribute("errors", errors);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
		rd.forward(request, response);
	}

}
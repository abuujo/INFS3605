package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingDAO;
import dao.BookingListDAO;
import dao.CourseDAO;
import dao.ProgramDAO;
import dao.StudentDAO;
import dto.Booking;
import dto.User;
import dto.Course;
import dto.Program;
import dto.Student;
import service.BookingService;
import service.StudentService;
import service.CourseService;
import service.ProgramService;

/**
 * Servlet implementation class BookingServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
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
		
		List<String> progList = ProgramService.getAllProgramNames(ProgramDAO.selectAll());
		
		request.setAttribute("progList", progList);
		
		// display registration form
		request.setAttribute("attempt", false);  // flag for whether this was a reg attempt
		RequestDispatcher rd = request.getRequestDispatcher("student.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		
		List<String> progList = ProgramService.getAllProgramNames(ProgramDAO.selectAll());
		
		request.setAttribute("progList", progList);
		
		// New Student
		Student newStudent = null;
		Course newCourse = null;
		
		// Create errors list
		List<String> errors = new ArrayList<String>();
		
		// fetch input params
		String zId = request.getParameter("zId");
		String type = request.getParameter("type");
		String stdFirstName = request.getParameter("studentFirstName");
		String stdLastName = request.getParameter("studentLastName");
		String stdEmail = request.getParameter("studentEmail");
		String stdProgram = request.getParameter("studentProgram");
		String stdGender = request.getParameter("studentGender");
		String stdCurrentWam = request.getParameter("studentCurrentWam");
		String stdYearEnrolled = request.getParameter("studentYearEnrolled");
		String stdAddress = request.getParameter("studentAddress");
		String stdPrivPhone = request.getParameter("studentPrivPhone");
		String stdWorkPhone = request.getParameter("studentWorkPhone");
		String stdPrivEmail = request.getParameter("studentPrivEmail");
		
		
		// validate input params
		if(zId == null || zId.equals("") || stdFirstName == null || stdFirstName.equals("") ||
				stdLastName == null || stdLastName.equals("") || stdEmail == null || stdEmail.equals("") ||
				stdProgram == null || stdProgram.equals("")) {
			errors.add("Student's information is necessary.");
		}


        
		
		if(zId != null) {
			if(!zId.matches("^[zZ][0-9]{7}")) { // z1234567 or Z1234567 format
				errors.add("Student's zID format must match z1234567 or Z1234567");
			}
		}

		if(errors.isEmpty()) {
			// if student not exists, add new
			if(StudentDAO.selectByZId(zId) == null) {
				//add new student
				newStudent = new Student();
				newStudent.setzId(zId);
				newStudent.setType(type);
				newStudent.setFirstName(stdFirstName);
				newStudent.setLastName(stdLastName);
				newStudent.setEmail(stdEmail);
				
				Program exist = ProgramDAO.selectByProgramName(stdProgram);
				if(exist != null) {
					newStudent.setProgramId(exist.getProgramId());
				} else {
					errors.add("NO SUCH PROGRAM");
				}
				newStudent.setGender(stdGender);
				newStudent.setCurrentWam(stdCurrentWam);
				newStudent.setYearEnrolled(stdYearEnrolled);
				newStudent.setAddress(stdAddress);
				newStudent.setPrivPhone(stdPrivPhone);
				newStudent.setWorkPhone(stdWorkPhone);
				newStudent.setPrivEmail(stdPrivEmail);
				
				StudentService.addStudent(newStudent);
			}

			
			request.setAttribute("addStudent", "Student has been added");
		}
		
		// or Update existing booking
		
		request.setAttribute("attempt", true);
		request.setAttribute("errors", errors);

		RequestDispatcher rd = request.getRequestDispatcher("student.jsp");
		rd.forward(request, response);
	}

}
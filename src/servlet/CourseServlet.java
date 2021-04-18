package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EnrolmentDAO;
import dao.NoteDAO;
import dto.Course;
import dto.Enrolment;
import dto.Note;
import dto.Program;
import dto.User;
import service.CourseService;
import service.ProgramService;

/**
 * Servlet implementation class HomeServlet
 */
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
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
		
		//Search handling
		String query = request.getParameter("search");
		//TEST
		System.out.println("Search course Query="+ query);
				
		if(query != null) {
			List<Course> result = CourseService.getCoursesBySearch(query);
			
			request.setAttribute("courses", result);
			
		} else {
			// List of courses
			List<Course> courses = CourseService.getAllCourses();
			
			request.setAttribute("courses", courses);
		}
		
		
		//request.setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("course.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*User user = (User) request.getSession().getAttribute("user");
		if(user == null) {	// if not logged in
			response.sendRedirect("index");	// redirect to index - login page
			return;
		}
		
		request.setAttribute("attempt", false);
		
		String flag = request.getParameter("flag");

		String studentId = request.getParameter("studentId");
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		if(flag != null) {
			if(flag.equals("addCourse")) {
				// fetch input params
				String courseCode = request.getParameter("courseCode");
				String semYear = request.getParameter("semYear");
				String mark = request.getParameter("mark");
				
				Course course = CourseService.getCourseByCourseCode(courseCode.toUpperCase());
				
				
				if(course != null) {
					int courseId = course.getCourseId();
					
					Enrolment enrolment = new Enrolment();
					enrolment.setCourseId(courseId);
					enrolment.setStudentId(Integer.parseInt(studentId));
					enrolment.setMark(Integer.parseInt(mark));
					enrolment.setSemYear(semYear);
					enrolment.setCourseCode(courseCode);
					
					EnrolmentDAO.add(enrolment);
					
					System.out.println("Enrolment added");
					
				} else {
					System.out.println("NO SUCH COURSE");
				}
				
			}
		}*/

		doGet(request, response);
		//response.sendRedirect("studentDetail?id="+studentId);
	}

}

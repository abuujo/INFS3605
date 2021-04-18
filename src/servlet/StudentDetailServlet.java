package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingDAO;
import dao.CourseDAO;
import dao.EnrolmentDAO;
import dao.OffenseDAO;
import dao.PreferenceDAO;
import dao.ProgramDAO;
import dao.SponsorDAO;
import dao.StudentDAO;
import dto.Student;
import dto.Booking;
import dto.User;
import dto.Course;
import dto.Enrolment;
import dto.Offense;
import dto.Preference;
import dto.Program;
import service.CourseService;
import service.EnrolmentService;
import service.PreferenceService;
import service.ProgramService;
import service.SponsorService;
import service.StudentService;

import reporting.PdfReporter;
/**
 * Servlet implementation class HomeServlet
 */
public class StudentDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDetailServlet() {
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
		
		//request.setAttribute("user", user);
		
		List<String> progList = ProgramService.getAllProgramNames(ProgramDAO.selectAll());
		request.setAttribute("progList", progList);
		
		List<String> sponsors = SponsorService.getAllSponsorNames();
		request.setAttribute("sponsors", sponsors);
		
		List<String> courseList = CourseService.getAllCourseCodes();
		request.setAttribute("courseList", courseList);
		
		Student student = null;
		
		String idParam = request.getParameter("id");
		if(idParam != null && idParam.matches("^[0-9]{1,9}$")) { // id cannot be more than 1 billion
			int id = Integer.parseInt(idParam);
			student = StudentDAO.selectById(id);

			Program program = ProgramDAO.selectById(student.getProgramId());
			student.setProgram(program.getProgramName());
			
			List<Enrolment> enrolments = EnrolmentDAO.selectByStudentId(id);
			EnrolmentService.setCourseCode(enrolments);
			request.setAttribute("enrolments", enrolments);
			
			List<Offense> offenses = OffenseDAO.selectAllByStudentId(id);
			request.setAttribute("offenses", offenses);
			
			if(student.getType().equals("Co-op")) {
				List<Preference> preferences = PreferenceDAO.selectByStudentId(id);
				
				for(Preference p : preferences) {
					PreferenceService.setPreferenceNames(p);
					if(p.getIPLevel() != null && p.getIPLevel().equals("IP1")) {
						request.setAttribute("pref1", p);						
					} else if(p.getIPLevel() != null && p.getIPLevel().equals("IP2")) {
						request.setAttribute("pref2", p);						
					} else if(p.getIPLevel() != null && p.getIPLevel().equals("IP3")) {
						request.setAttribute("pref3", p);
					}
				}
			}
		}
		
		request.setAttribute("student", student);
		RequestDispatcher rd = request.getRequestDispatcher("studentDetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("user", user);
		
		List<String> progList = ProgramService.getAllProgramNames(ProgramDAO.selectAll());
		request.setAttribute("progList", progList);	
		
		List<String> sponsors = SponsorService.getAllSponsorNames();
		request.setAttribute("sponsors", sponsors);
		
		String flag = request.getParameter("flag");
		String studentId = request.getParameter("studentId");
		
		
		if(studentId != null && (flag != null && flag.equals("updateStudentDetail"))) {
			Student update = StudentService.getStudentById(Integer.parseInt(studentId));
			
			if(update != null) {
				String type = request.getParameter("type");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String email = request.getParameter("email");
				String program = request.getParameter("program");
				String privEmail = request.getParameter("privEmail");
				String privPhone = request.getParameter("privPhone");
				String workPhone = request.getParameter("workPhone");
				String gender = request.getParameter("gender");
				String address = request.getParameter("address");
				String currentWam = request.getParameter("currentWam");
				String yearEnrolled = request.getParameter("yearEnrolled");
				String ip1 = request.getParameter("ip1");
				String ip2 = request.getParameter("ip2");
				String ip3 = request.getParameter("ip3");
			
				update.setType(type);
				update.setFirstName(firstName);
				update.setLastName(lastName);
				update.setProgram(program);
				update.setProgramId(ProgramDAO.selectByProgramName(program).getProgramId());
				update.setEmail(email);
				
				update.setPrivEmail(privEmail);
				update.setPrivPhone(privPhone);
				update.setWorkPhone(workPhone);
				update.setGender(gender);
				update.setAddress(address);
				update.setCurrentWam(currentWam);
				update.setYearEnrolled(yearEnrolled);
				
				update.setIp1(ip1);
				update.setIp2(ip2);
				update.setIp3(ip3);
				
				StudentService.update(update);
				
				//TEST
				System.out.println("STUDENT DETAIL UPDATED");
			} else {
				System.out.println("ERROR: NO SUCH STUDENT");
			}
		} else if(studentId != null && (flag != null && flag.equals("addCourse"))) {
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
		} else if(studentId != null && (flag != null && flag.equals("addOffense"))) {
			String offenseName = request.getParameter("offenseName");
			String dateCommitted = request.getParameter("dateCommitted");
			
			Offense offense = new Offense();
			offense.setStudentId(Integer.parseInt(studentId));
			offense.setOffenseName(offenseName);
			offense.setDateCommitted(dateCommitted);
			
			OffenseDAO.add(offense);
			System.out.println("OFFENSE ADDED");
		} else if(studentId != null && (flag != null && flag.equals("updatePreference"))) {
			
			String firstPreference = request.getParameter("firstPreference");
			String secondPreference = request.getParameter("secondPreference");
			String thirdPreference = request.getParameter("thirdPreference");
			String fourthPreference = request.getParameter("fourthPreference");
			String fifthPreference = request.getParameter("fifthPreference");
			String sixthPreference = request.getParameter("sixthPreference");
			String ipLvl = request.getParameter("ipLvl");
			
			Preference update = PreferenceDAO.selectByStudentIdAndIpLevel(Integer.parseInt(studentId),ipLvl);
			if(update == null) {
				update = new Preference();
				update.setStudentId(Integer.parseInt(studentId));
				update.setIPLevel(ipLvl);
			}
				
			update.setFirstPref(SponsorService.getSponsorBySponsorName(firstPreference).getSponsorId());
			update.setSecondPref(SponsorService.getSponsorBySponsorName(secondPreference).getSponsorId());
			update.setThirdPref(SponsorService.getSponsorBySponsorName(thirdPreference).getSponsorId());
			update.setFourthPref(SponsorService.getSponsorBySponsorName(fourthPreference).getSponsorId());
			update.setFifthPref(SponsorService.getSponsorBySponsorName(fifthPreference).getSponsorId());
			update.setSixthPref(SponsorService.getSponsorBySponsorName(sixthPreference).getSponsorId());
			
			PreferenceDAO.addOrUpdate(update);
			System.out.println("PREFERENCE WAS UPDATED");
			
		} else if(studentId != null && (flag != null && flag.equals("reportGenerate"))) {
			
			response.setContentType("application/pdf");
			OutputStream out = response.getOutputStream();
			
			Student student = StudentService.getStudentById(Integer.parseInt(studentId));
			String path = request.getContextPath();
			PdfReporter.doIt(student, out, path);
			return;
		} 
		
		doGet(request, response);
		//response.sendRedirect("./studentDetail?id="+studentId);
	}

}
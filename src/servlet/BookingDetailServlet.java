package servlet;



import java.io.IOException;

import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AttendeeDAO;
import dao.BookingDAO;
import dao.NoteDAO;
import dao.ProgramDAO;
import dao.StudentDAO;
import dto.Booking;
import dto.User;
import dto.Note;
import dto.Student;

import notification.NotificationEmail;
import reporting.PdfReporter;
import service.ProgramService;
import service.StudentService;
import dto.*;

/**
 * Servlet implementation class BookingDetailsServlet
 */
@WebServlet("/BookingDetailsServlet")
public class BookingDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingDetailServlet() {
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
		
		RequestDispatcher rd = request.getRequestDispatcher("bookingDetail.jsp");
		
		List<String> progList = ProgramService.getAllProgramNames(ProgramDAO.selectAll());
		request.setAttribute("progList", progList);
		
		Booking booking = null;
		Student student = null;
		
		String idParam = request.getParameter("id");
		if(idParam != null && idParam.matches("^[0-9]{1,9}$")) { // id cannot be more than 1 billion
			int id = Integer.parseInt(idParam);

			booking = BookingDAO.selectById(id);
			
			if(booking != null) {
				List<Note> notes = NoteDAO.selectAllByBookingId(booking.getBookingId());
				List<Student> attendees = AttendeeDAO.selectByBookingId(booking.getBookingId());
				StudentService.setProgramName(attendees);
				
				request.setAttribute("attendees", attendees);
				request.setAttribute("notes", notes);
				request.setAttribute("booking", booking);
			}
			
			student = StudentDAO.selectByZId(booking.getzId());
			if(student != null) {
				request.setAttribute("studentId", student.getStudentId());
			}
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
		//request.setAttribute("user", user);
		
		List<String> progList = ProgramService.getAllProgramNames(ProgramDAO.selectAll());
		request.setAttribute("progList", progList);
		
		String bookingId = request.getParameter("bookingId");
		String studentId = request.getParameter("studentId");
		String noteId = request.getParameter("noteId");
		String flag = request.getParameter("flag");
		String attendeeFlag = request.getParameter("attendeeFlag");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		if(flag != null && flag.equals("updateBooking")) {
			Booking updateBooking = BookingDAO.selectById(Integer.parseInt(bookingId));
			
			if(updateBooking != null) {
				//update booking detail
				
				String category = request.getParameter("category");
				String location = request.getParameter("location");
				String status = request.getParameter("status");
				String priority = request.getParameter("priority");
				
				updateBooking.setCategory(category);
				updateBooking.setLocation(location);
				updateBooking.setStatus(status);
				updateBooking.setPriority(Integer.parseInt(priority));
				
				BookingDAO.update(updateBooking);
	
				Student student = StudentDAO.selectByZId(updateBooking.getzId());
				
				if(student != null) {
					String email = student.getEmail();
	
					// Notify updates to student
					NotificationEmail.sendEmailStudentUpdate(email, updateBooking);
	
					
				} else {
					System.out.println("Updated, but failed to send email as email not exists!\n");
				}
			}
			
		} else if(flag != null && flag.equals("addNote")) {
			String texts = request.getParameter("note");

			Date current = new Date();
			String dateCreated = sdf.format(current);
			
		
			Note newNote = new Note();
			newNote.setDateCreated(dateCreated);
			newNote.setBookingId(Integer.parseInt(bookingId));
			newNote.setTexts(texts);
			newNote.getTexts();
			
			// Add to database
			NoteDAO.add(newNote);
			System.out.println("NOTE WAS ADDED");
		} else if(flag != null && flag.equals("addAttendee")) {

			String zID = request.getParameter("zID");
			String email = request.getParameter("email");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String program = request.getParameter("program");
			String type = request.getParameter("type");
			
			Student student = StudentDAO.selectByZId(zID);
			if(student == null) {
				student = new Student();
				student.setzId(zID);
				student.setEmail(email);
				student.setType(type);
				student.setFirstName(firstName);
				student.setLastName(lastName);
					
				Program p = ProgramDAO.selectByProgramName(program);
				student.setProgram(program);
				student.setProgramId(p.getProgramId());
					
				StudentService.addStudent(student);
			} else {
				student.setzId(zID);
				student.setEmail(email);
				student.setType(type);
				student.setFirstName(firstName);
				student.setLastName(lastName);
					
				Program p = ProgramDAO.selectByProgramName(program);
				student.setProgram(program);
				student.setProgramId(p.getProgramId());
				
				StudentDAO.update(student);
			}
			Student std = StudentDAO.selectByZId(zID);

			// prevent adding same student to attendees list
			if(AttendeeDAO.selectByStudent(std) == null) {
				AttendeeDAO.add(Integer.parseInt(bookingId), std.getStudentId());
				System.out.println("ATTENDEE WAS ADDED");
			}

			
		} else if(flag != null && flag.equals("Update")) { // Update note
			String texts = request.getParameter("note");
			
			Date modified = new Date();
			String lastModified = sdf.format(modified);
			
			Note update = NoteDAO.selectById(Integer.parseInt(noteId));
			
			update.setTexts(texts);
			update.setDateCreated(lastModified);
			NoteDAO.update(update);
			System.out.println("NOTE UPDATED");
			
		} else if(flag != null && flag.equals("Delete")) { // Delete note
			
			NoteDAO.delete(Integer.parseInt(noteId));
			System.out.println("NOTE DELETED");
			
		} else if(flag != null && flag.equals("sendEmail")) {
			Student s = StudentService.getStudentById(Integer.parseInt(studentId));
			Booking b = BookingDAO.selectById(Integer.parseInt(bookingId));
			try {
				NotificationEmail.sendStudentNote(b,s);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} 
		
		if(attendeeFlag != null && attendeeFlag.equals("Update")) { // Update attendee
			// NOT NECESSARY
		} else if(attendeeFlag != null && attendeeFlag.equals("Delete")) { // Delete attendee
			AttendeeDAO.delete(Integer.parseInt(bookingId), Integer.parseInt(studentId));
			System.out.println("ATTENDEE WAS REMOVED FROM THE BOOKING");
		}
		
		
		List<Student> attendees = AttendeeDAO.selectByBookingId(Integer.parseInt(bookingId));
		request.setAttribute("attendees", attendees);
		
		List<Note> notes = NoteDAO.selectAllByBookingId(Integer.parseInt(bookingId));
		request.setAttribute("notes", notes);
		
		doGet(request, response);
		//response.sendRedirect("./bookingDetail?id="+bookingId);
		
	}

}

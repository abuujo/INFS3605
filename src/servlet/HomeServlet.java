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

import dao.AttendeeDAO;
import dao.BookingDAO;
import dao.BookingListDAO;
import dao.NoteDAO;
import dao.StudentDAO;
import dto.Booking;
import dto.User;
import dto.Note;
import dto.Student;
import notification.NotificationEmail;
import service.BookingService;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
		
		request.setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		
		//Search handling
		String query = request.getParameter("search");
		//TEST
		System.out.println("Search booking Query="+ query);
		
		if(query != null) {
			List<Booking> result = BookingService.getBookingsBySearch(user, query);
			
			Collections.sort(result);
			request.setAttribute("bookings", result);
			
		} else {
			// List of bookings
			List<Booking> bookings = BookingService.getBookingsByUser(user);
			
			Collections.sort(bookings);
			request.setAttribute("bookings", bookings);
		}
		
		
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
		
		String bookingId = request.getParameter("bookingId");
		Booking del = null;
		String flag = request.getParameter("flag");
		
		if(flag.equals("deleteBooking")) {
			del = BookingDAO.selectById(Integer.parseInt(bookingId));
			
			
			// Remove all notes under the booking
			List<Note> list = NoteDAO.selectAllByBookingId(Integer.parseInt(bookingId));
			
			for(Note note : list) {
				NoteDAO.delete(note.getNoteId());
			}
			
			// Remove all attendees under the booking
			List<Student> attendees = AttendeeDAO.selectByBookingId(Integer.parseInt(bookingId));
			for(Student s : attendees) {
				AttendeeDAO.delete(Integer.parseInt(bookingId), s.getStudentId());
			}
			
			// Remove from bookingList
			BookingListDAO.deleteByBookingId(Integer.parseInt(bookingId));
			
			// Now, delete the booking
			BookingDAO.delete(del);
			
			
			// Alert student that booking has been deleted
			Student student = StudentDAO.selectByZId(del.getzId());
			
			if(student != null) {
				String email = student.getEmail();
				NotificationEmail.sendEmailStudentDelete(email, del);

			} else {
				System.out.println("Updated, but failed to send email as email not exists!");
			}
		}
		
		doGet(request, response);
	}

}

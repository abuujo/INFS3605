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

import dao.NoteDAO;
import dto.Booking;
import dto.User;
import dto.Note;

/**
 * Servlet implementation class NoteServlet
 */
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoteServlet() {
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
		RequestDispatcher rd = request.getRequestDispatcher("bookingDetail.jsp");
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
		String noteId = request.getParameter("noteId");
		int bookingId = (Integer) request.getSession().getAttribute("bookingId");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		if(flag != null) {
			if(flag.equals("addNote")) {
				// fetch input params
				String texts = request.getParameter("note");

				Date current = new Date();
				String dateCreated = sdf.format(current);
				
			
				Note newNote = new Note();
				newNote.setDateCreated(dateCreated);
				newNote.setBookingId(bookingId);
				newNote.setTexts(texts);
				newNote.getTexts();
				
				// Add to database
				NoteDAO.add(newNote);
				
				
			} else {
				System.out.println("SOMETHING WENT WRONG WHILE ADDING NOTE");
			}
		}

		

		List<Note> notes = NoteDAO.selectAllByBookingId(bookingId);
		
		request.setAttribute("notes", notes);*/
		doGet(request, response);
		//response.sendRedirect("bookingDetail?id="+bookingId);
		
	}

}

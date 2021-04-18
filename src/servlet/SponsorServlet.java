package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SponsorDAO;
import dto.Sponsor;
import dto.User;
import service.SponsorService;

/**
 * Servlet implementation class SponsorServlet
 */

public class SponsorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SponsorServlet() {
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
		
		List<Sponsor> sponsors = SponsorService.getAllSponsors();
		request.setAttribute("sponsors", sponsors);
		
		RequestDispatcher rd = request.getRequestDispatcher("sponsor.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("user", user);
		
		String flag = request.getParameter("flag");
		
		if(flag.equals("addSponsor")) {
			String sponsorName = request.getParameter("sponsorName");
			String description = request.getParameter("description");
			
			Sponsor newSponsor = new Sponsor();
			newSponsor.setSponsorName(sponsorName);
			newSponsor.setDescription(description);
			
			//Add
			SponsorDAO.add(newSponsor);
			System.out.println("NEW SPONSOR ADDED");
		} else if(flag.equals("Update")) {
			String sponsorId = request.getParameter("sponsorId");
			String description = request.getParameter("description");
			
			Sponsor update = SponsorService.getSponsorBySponsorId(Integer.parseInt(sponsorId));
			update.setDescription(description);
			
			SponsorDAO.update(update);
			System.out.println("SPONSOR UPDATED");
			
		} else if(flag.equals("Delete")) {
			String sponsorId = request.getParameter("sponsorId");
			Sponsor delete = SponsorDAO.selectById(Integer.parseInt(sponsorId));
			
			SponsorDAO.delete(delete);
			System.out.println("SPONSOR DELETED");
			
		}
		
		//response.sendRedirect("sponsor");
		doGet(request, response);
	}

}

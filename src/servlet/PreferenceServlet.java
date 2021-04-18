package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import reporting.PdfReporter;
import dto.Offense;
import dto.Preference;
import dto.Student;
import dto.Sponsor;
import dao.OffenseDAO;
import dao.PreferenceDAO;
import dao.ProgramDAO;
import dao.SponsorDAO;
import dao.StudentDAO;
import service.*;

/**
 * Servlet implementation class IndexServlet
 */
public class PreferenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PreferenceServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			

		List<String> sponsors = SponsorService.getAllSponsorNames();
		request.setAttribute("sponsors", sponsors);
		
		Student student = null;
		
		String idParam = request.getParameter("id");
		String ipParam = request.getParameter("ip");
		
		if(idParam != null && idParam.matches("^[0-9]{1,9}$")) { // id cannot be more than 1 billion
			int id = Integer.parseInt(idParam);
			student = StudentDAO.selectById(id);
			request.setAttribute("student", student);
		}
		if(ipParam != null){
			request.setAttribute("IPLevel", ipParam);
		}

		// Otherwise, display index page
		RequestDispatcher rd = request.getRequestDispatcher("preferenceForm.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> sponsors = SponsorService.getAllSponsorNames();
		request.setAttribute("sponsors", sponsors);
		
		String studentId = request.getParameter("studentId");
		String ipType = request.getParameter("ip");
		
		List<String> errors = new ArrayList<String>();
		
		if(studentId != null && ipType != null){
			Student student = StudentService.getStudentById(Integer.parseInt(studentId));
			
			String first = request.getParameter("firstPreference");
			String second = request.getParameter("secondPreference");	
			String third = request.getParameter("thirdPreference");			
			String fourth = request.getParameter("fourthPreference");			
			String fifth = request.getParameter("fifthPreference");			
			String sixth = request.getParameter("sixthPreference");
			
			if(first == null || first.equals("") ||
					second == null || second.equals("") ||
					third == null || third.equals("") ||
					fourth == null || fourth.equals("") ||
					fifth == null || fifth.equals("") ||
					sixth == null || sixth.equals("")) {
				errors.add("You need to fill all fields");
			}
			
			if(errors.isEmpty()) {
				Preference thisPref = new Preference();
				Sponsor firstSponsor = SponsorDAO.selectBySponsorName(first);
				Sponsor secondSponsor = SponsorDAO.selectBySponsorName(second);
				Sponsor thirdSponsor = SponsorDAO.selectBySponsorName(third);
				Sponsor fourthSponsor = SponsorDAO.selectBySponsorName(fourth);
				Sponsor fifthSponsor = SponsorDAO.selectBySponsorName(fifth);
				Sponsor sixthSponsor = SponsorDAO.selectBySponsorName(sixth);
				
				//set they're ID's
				thisPref.setFirstPref(firstSponsor.getSponsorId());
				thisPref.setSecondPref(secondSponsor.getSponsorId());
				thisPref.setThirdPref(thirdSponsor.getSponsorId());
				thisPref.setFourthPref(fourthSponsor.getSponsorId());
				thisPref.setFifthPref(fifthSponsor.getSponsorId());
				thisPref.setSixthPref(sixthSponsor.getSponsorId());
				
				//set they're Names
				thisPref.setFirstPrefName(first);
				thisPref.setSecondPrefName(second);
				thisPref.setThirdPrefName(third);
				thisPref.setFourthPrefName(fourth);
				thisPref.setFifthPrefName(fifth);
				thisPref.setSixthPrefName(sixth);
				
				thisPref.setStudentId(student.getStudentId());
				thisPref.setIPLevel(ipType);
				
				PreferenceDAO.addOrUpdate(thisPref);
			}
			
		} else {
			System.out.println("no student ID");
		}
		request.setAttribute("errors", errors);
	
		RequestDispatcher rd = request.getRequestDispatcher("preferenceForm.jsp");
		rd.forward(request, response);
		//response.sendRedirect("./preferenceForm?id="+studentId+"&ip="+ipType);
		
	}

}

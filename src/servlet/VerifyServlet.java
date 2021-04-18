package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.User;
import service.UserService;

/**
 * Servlet implementation class VerifyServlet
 */
public class VerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("verification.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String)request.getSession().getAttribute("email");
		String uCode = (String)request.getSession().getAttribute("uCode");
		String unlockCode = request.getParameter("unlockCode");
		
		
		if(unlockCode != null) {
			if(uCode.equals(unlockCode)) {
				User user = UserDAO.selectByEmail(email);
				UserService.unlockUser(user);
			} else {
				request.setAttribute("error", "Unlock Code not matched, please try again");
				RequestDispatcher rd = request.getRequestDispatcher("verification.jsp");
				rd.forward(request, response);
				return;
			}
		}
		
		request.setAttribute("unlocked", "Your account has been unlocked");
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	

}

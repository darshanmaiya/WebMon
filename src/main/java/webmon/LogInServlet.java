package webmon;

import javax.servlet.http.*;

import webmon.models.User;
import webmon.utils.AuthenticationUtils;
import webmon.utils.Constants;
import webmon.utils.DatastoreUtils;

@SuppressWarnings("serial")
public class LogInServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			if(AuthenticationUtils.isLoggedIn(req)) {
				resp.sendRedirect("/webmon");
				return;
			}
			String referrer = req.getHeader("referer");
			if(referrer != null && referrer.endsWith("signup"))
				req.setAttribute("parameter", "signup");
			req.getRequestDispatcher(Constants.jspRoot + "login.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			User loggedInUser = new User();
			
			if(DatastoreUtils.checkUserCredentials(email, password, loggedInUser)) {
				HttpSession session = req.getSession(true);
				session.setAttribute("user", loggedInUser);
				resp.getWriter().print("success");
			}
			else
				resp.getWriter().print("fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
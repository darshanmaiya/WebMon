package webmon;

import javax.servlet.http.*;

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
			String referrer = req.getParameter("signup");
			if(referrer != null && referrer.equals("true"))
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
			
			if(DatastoreUtils.checkUserCredentials(email, password)) {
				HttpSession session = req.getSession(true);
				session.setAttribute("user", DatastoreUtils.getUser(email));
				resp.getWriter().print("success");
			}
			else
				resp.getWriter().print("fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
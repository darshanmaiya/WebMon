package webmon;

import javax.servlet.http.*;

import webmon.utils.Constants;

/**
 * The Class LogOutServlet.
 */
@SuppressWarnings("serial")
public class LogOutServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession(false);
			if(session != null)
				session.setAttribute("user", null);
			req.setAttribute("parameter", "logout");
			req.getRequestDispatcher(Constants.jspRoot + "login.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
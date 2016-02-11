package webmon;

import javax.servlet.http.*;

import webmon.utils.Constants;

@SuppressWarnings("serial")
public class ErrorServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setAttribute("errorCode", 401);
			req.setAttribute("errorMessage", "Unauthorized");
			req.getRequestDispatcher(Constants.jspRoot + "error.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
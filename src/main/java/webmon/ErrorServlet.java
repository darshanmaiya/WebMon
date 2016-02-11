package webmon;

import javax.servlet.http.*;

import webmon.utils.Constants;

@SuppressWarnings("serial")
public class ErrorServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String errorCode = req.getParameter("errorCode");
			if(errorCode == null) {
				req.setAttribute("errorCode", "404");
				req.setAttribute("errorMessage", "Page not found");
			} else {
				req.setAttribute("errorCode", errorCode);
				req.setAttribute("errorMessage", req.getParameter("errorMessage"));
			}
			
			req.getRequestDispatcher(Constants.jspRoot + "error.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
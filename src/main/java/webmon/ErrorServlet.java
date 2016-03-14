package webmon;

import javax.servlet.http.*;

import webmon.utils.Constants;

/**
 * The Class ErrorServlet.
 */
@SuppressWarnings("serial")
public class ErrorServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String errorCode = String.valueOf(req.getAttribute(Constants.stringErrorCode));
			if(errorCode == null || errorCode.equals("null") || errorCode.equals("")) {
				req.setAttribute(Constants.stringErrorCode, Constants.stringError404);
				req.setAttribute(Constants.stringErrorMessage, Constants.stringError404Desc);
				
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
			
			req.getRequestDispatcher(Constants.jspRoot + "error.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
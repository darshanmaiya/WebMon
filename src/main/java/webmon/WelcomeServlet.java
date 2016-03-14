package webmon;

import javax.servlet.http.*;

/**
 * The Class WelcomeServlet.
 */
@SuppressWarnings("serial")
public class WelcomeServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			if(req.getRequestURI().equals("/"))
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			else
				req.getRequestDispatcher("/error").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package webmon;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class WelcomeServlet extends HttpServlet {

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
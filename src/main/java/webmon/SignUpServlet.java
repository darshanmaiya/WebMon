package webmon;

import javax.servlet.http.*;

import webmon.utils.Constants;

@SuppressWarnings("serial")
public class SignUpServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.getRequestDispatcher(Constants.jspRoot + "signup.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
	}
  }
}
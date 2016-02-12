package webmon;

import javax.servlet.http.*;
import webmon.models.User;
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
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// Get user info
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String password = req.getParameter("password");
			
			// Get free UserID from datastore/memcache and assign it to the user
			// ToDo: Change last parameter of newUser constructor to free userId instead of 1
			User newUser = new User(name, email, phone, password, 1);
			if (newUser != null)
			{
				// Update free userid by 1
			}
			
			// Store newUser in memcache and subsequently datastore
			
		} catch (Exception e) {
			e.printStackTrace();
	}
  }
}
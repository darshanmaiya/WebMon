package webmon;

import java.io.PrintWriter;

import javax.servlet.http.*;

import webmon.models.User;
import webmon.utils.Constants;
import webmon.utils.DatastoreUtils;

/**
 * The Class SignUpServlet.
 */
@SuppressWarnings("serial")
public class SignUpServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.getRequestDispatcher(Constants.jspRoot + "signup.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter pw = resp.getWriter();
			String email = req.getParameter("email");
			
			if(DatastoreUtils.checkUser(email)) {
				pw.print(Constants.stringUserExists);
			} else {
				String name = req.getParameter("name");
				String phone = req.getParameter("phone");
				String password = req.getParameter("password");
				User newUser = new User(name, email, phone, password);
				DatastoreUtils.putUser(newUser);
				
				pw.print(Constants.stringUserAdded);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package webmon.utils;

import javax.servlet.http.*;

public class AuthenticationUtils {

	public static boolean isLoggedIn(HttpServletRequest request) {
		boolean loggedIn = true;
		
		HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null)
        	loggedIn = false;
		
		return loggedIn;
	}
	
	public static boolean isAuthorized(HttpServletRequest request) {
		boolean authorized = true;
		// Stub to check User access to Resource
		return authorized;
	}
}

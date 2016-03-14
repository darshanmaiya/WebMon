package webmon.utils;

import javax.servlet.http.*;

import webmon.models.User;

/**
 * The Class AuthenticationUtils.
 */
public class AuthenticationUtils {

	/**
	 * Checks if user is logged in.
	 *
	 * @param request the request object
	 * @return true, if user is logged in
	 */
	public static boolean isLoggedIn(HttpServletRequest request) {
		boolean loggedIn = true;
		
		HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null)
        	loggedIn = false;
		
		return loggedIn;
	}
	
	/**
	 * Checks if user is authorized.
	 *
	 * @param request the request object
	 * @return true, if user is authorized
	 */
	public static boolean isAuthorized(HttpServletRequest request) {
		boolean authorized = true;
		
		if(!isLoggedIn(request))
			authorized = false;
		else {
			User user = DatastoreUtils.getUser(String.valueOf(request.getSession(false).getAttribute("user")));
			// Requested resource is user
			String requestURL = request.getRequestURL().toString();
	        if(requestURL.matches("^.*webmon/users/[0-9]+$")) {
	        	if(user.getId() != Long.valueOf(requestURL.substring(requestURL.lastIndexOf("/") + 1)))
	        		authorized = false;
	        } else if(requestURL.matches("^.*webmon/websites/[0-9]+$")) { // Requested resource is website
	        	if(!user.getMonitoredWebsites().contains(Long.valueOf(requestURL.substring(requestURL.lastIndexOf("/") + 1))))
	        		authorized = false;
	        }
		}
		
		return authorized;
	}
}

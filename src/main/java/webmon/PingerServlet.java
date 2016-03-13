package webmon;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import webmon.models.Website;
import webmon.utils.DatastoreUtils;

public class PingerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8049243788658828394L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        int id = Integer.valueOf(request.getParameter("id"));
        
        Website website = DatastoreUtils.getWebsite(id);
        website.getResponseTime();
		DatastoreUtils.putWebsite(website);
		
		response.sendRedirect("/");
    }
}
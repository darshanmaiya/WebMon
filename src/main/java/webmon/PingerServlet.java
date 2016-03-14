package webmon;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import webmon.models.Website;
import webmon.utils.DatastoreUtils;

/**
 * The Class PingerServlet.
 */
public class PingerServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8049243788658828394L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        int id = Integer.valueOf(request.getParameter("id"));
        
        Website website = DatastoreUtils.getWebsite(id);
        website.getResponseTime();
		DatastoreUtils.putWebsite(website);
		
		response.setStatus(HttpServletResponse.SC_OK);
    }
}
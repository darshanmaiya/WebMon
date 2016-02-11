package webmon.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import webmon.utils.AuthenticationUtils;
import webmon.utils.Constants;

// Implements Filter class
public class AuthorizationFilter implements Filter  {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
					  throws IOException, ServletException {

		try {
    		if(!AuthenticationUtils.isLoggedIn((HttpServletRequest)request)) {
    			 
            	request.setAttribute(Constants.stringErrorCode, Constants.stringError401);
            	request.setAttribute(Constants.stringErrorMessage, Constants.stringError401NotLoggedIn);
            	
            	request.getRequestDispatcher("/error").forward(request, response);
            	return;
            }
            
            if(!AuthenticationUtils.isAuthorized((HttpServletRequest)request)) {
            	
            	request.setAttribute(Constants.stringErrorCode, Constants.stringError401);
            	request.setAttribute(Constants.stringErrorMessage, Constants.stringError401Unauthorized);
            	
            	request.getRequestDispatcher("/error").forward(request, response);
            	return;
            }
    	} catch (ServletException e) {
    		e.printStackTrace();
    	}

		// Pass request back down the filter chain
		chain.doFilter(request,response);
	}

	@Override
	public void destroy() {
		
	}
}
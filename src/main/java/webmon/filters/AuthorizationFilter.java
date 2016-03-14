package webmon.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import webmon.utils.AuthenticationUtils;
import webmon.utils.Constants;

/**
 * The Class AuthorizationFilter.
 */
// Implements Filter class
public class AuthorizationFilter implements Filter  {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
					  throws IOException, ServletException {

		try {
    		if(!AuthenticationUtils.isLoggedIn((HttpServletRequest)request)) {
    			 
            	request.setAttribute(Constants.stringErrorCode, Constants.stringError401);
            	request.setAttribute(Constants.stringErrorMessage, Constants.stringError401NotLoggedIn);
            	
            	((HttpServletResponse)response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            	
            	request.getRequestDispatcher("/error").forward(request, response);
            	return;
            }
            
            if(!AuthenticationUtils.isAuthorized((HttpServletRequest)request)) {
            	
            	request.setAttribute(Constants.stringErrorCode, Constants.stringError401);
            	request.setAttribute(Constants.stringErrorMessage, Constants.stringError401Unauthorized);
            	
            	((HttpServletResponse)response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            	
            	request.getRequestDispatcher("/error").forward(request, response);
            	return;
            }
    	} catch (ServletException e) {
    		e.printStackTrace();
    	}

		// Pass request back down the filter chain
		chain.doFilter(request,response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		
	}
}
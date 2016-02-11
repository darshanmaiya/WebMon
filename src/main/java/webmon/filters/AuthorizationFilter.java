package webmon.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;

import webmon.utils.AuthenticationUtils;
import webmon.utils.Constants;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter {
	@Context private HttpServletRequest request;
	@Context private HttpServletResponse response;
 
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	
    	try {
    		if(!AuthenticationUtils.isLoggedIn(request)) {
    			 
            	request.setAttribute(Constants.stringErrorCode, Constants.stringError401);
            	request.setAttribute(Constants.stringErrorMessage, Constants.stringError401NotLoggedIn);
            	
            	request.getRequestDispatcher("/error").forward(request, response);
            	return;
            }
            
            if(!AuthenticationUtils.isAuthorized(request)) {
            	
            	request.setAttribute(Constants.stringErrorCode, Constants.stringError401);
            	request.setAttribute(Constants.stringErrorMessage, Constants.stringError401Unauthorized);
            	
            	request.getRequestDispatcher("/error").forward(request, response);
            	return;
            }
    	} catch (ServletException e) {
    		e.printStackTrace();
    	}
    }
}
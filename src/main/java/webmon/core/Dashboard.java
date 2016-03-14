package webmon.core;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.mvc.Viewable;

import webmon.utils.Constants;

/**
 * The Class Dashboard.
 */
//Map this class to the default route
@Provider
@Path("/")
public class Dashboard {
	
	/** The uri info. */
	@Context
	UriInfo uriInfo;
	
	/** The request. */
	@Context
	Request request;
	
	/**
	 * Reroutes the request to the user dashboard.
	 *
	 * @return The response object
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getDashboard() {
		
		return Response.ok(new Viewable(Constants.jspRoot + "dashboard.jsp", null)).build();
	}
}
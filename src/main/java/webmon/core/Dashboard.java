package webmon.core;

import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.mvc.Viewable;

import webmon.utils.Constants;

//Map this class to the default route
@Provider
@Path("/")
public class Dashboard {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getDashboard() {
		//return new Viewable(Constants.jspRoot + "dashboard.jsp", null);
		return Response.ok(new Viewable(Constants.jspRoot + "dashboard.jsp", null)).build();
	}
}
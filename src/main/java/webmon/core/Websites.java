package webmon.core;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.glassfish.jersey.server.mvc.Viewable;

import webmon.utils.Constants;

//Map this class to /websites route
@Path("/websites")
public class Websites {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_HTML)
	public Response addUser() {
		return Response.ok(new Viewable(Constants.jspRoot + "addWebsite.jsp", null)).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("id") int id) {
		return "{ \"result\": \"Website details for website with id: " + id + "\"}";
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@PathParam("id") int id) {
		return "{ \"result\": \"Successfully updated website with id: " + id + "\"}";
	}
}
package webmon.core;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

//Map this class to /users route
@Path("/users")
public class Users {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("id") int id) {
		return "{ \"result\": \"User details for user with id: " + id + "\"}";
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@PathParam("id") int id) {
		return "{ \"result\": \"Successfully updated user with id: " + id + "\"}";
	}
}
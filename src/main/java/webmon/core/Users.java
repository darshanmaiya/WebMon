package webmon.core;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.glassfish.jersey.server.mvc.Viewable;

import webmon.models.User;
import webmon.utils.Constants;
import webmon.utils.DatastoreUtils;

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
	public User getUserJSON(@PathParam("id") long id) {
		User user = DatastoreUtils.getUser(id);
		user.setPassword("");
		return user;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response getUserHTML(@PathParam("id") int id) {
		return Response.ok(new Viewable(Constants.jspRoot + "user.jsp", null)).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("phone") String phone,
			@FormParam("password") String password) {
		try {
			User user = DatastoreUtils.getUser(email);
			if(name != null && !name.equals(""))
				user.setName(name);
			if(phone != null && !phone.equals(""))
				user.setPhone(phone);
			if(password != null && !password.equals(""))
				user.setPassword(password);
			
			DatastoreUtils.putUser(user);
			
			return "{ \"result\": \"Successfully updated user with email: " + user.getEmail() + "\", \"status\": 200}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "{ \"result\": \"Updating user with given details failed.\", \"status\": 500}";
	}
}
package webmon.core;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import webmon.models.User;
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
	@Produces(MediaType.TEXT_HTML)
	public String getUser(@PathParam("id") int id) {
		return "Users " + id;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addUser(@FormParam("name") String name,
		      @FormParam("email") String email,
		      @FormParam("phone") String phone,
		      @FormParam("password") String password,
		      @Context HttpServletResponse servletResponse) {
		try {
			if(DatastoreUtils.checkUser(email))
				return "User already exists";
			User newUser = new User(name, email, phone, password);
			DatastoreUtils.putUser(newUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Users POST";
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.TEXT_HTML)
	public String updateUser(@PathParam("id") int id) {
		return "Users PUT" + id;
	}
}
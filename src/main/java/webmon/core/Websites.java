package webmon.core;

import java.io.PrintWriter;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.glassfish.jersey.server.mvc.Viewable;

//import webmon.models.User;
import webmon.utils.Constants;
//import webmon.utils.DatastoreUtils;

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
	public Response addWebsite() {
		return Response.ok(new Viewable(Constants.jspRoot + "addWebsite.jsp", null)).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWebsite(@PathParam("id") int id) {
		return "{ \"result\": \"Website details for website with id: " + id + "\"}";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String saveWebsite(@FormParam("name") String name,
			@FormParam("url") String url,
			@FormParam("notifyDown") String notifyDown,
			@FormParam("notifyResponse") String notifyResponse,
			@FormParam("responseTime") String responseTime) {
		System.out.println(name + " " + url);
		try {
			/*String email = req.getParameter("email");
			
			if(DatastoreUtils.checkUser(email)) {
				pw.print(Constants.stringUserExists);
			} else {
				String name = req.getParameter("name");
				String phone = req.getParameter("phone");
				String password = req.getParameter("password");
				User newUser = new User(name, email, phone, password);
				DatastoreUtils.putUser(newUser);
				
				pw.print(Constants.stringUserAdded);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"result\": \"fail\"}";
		}
		return "{ \"result\": \"Successfully added website\"}";
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateWebsite(@PathParam("id") int id) {
		return "{ \"result\": \"Successfully updated website with id: " + id + "\"}";
	}
}
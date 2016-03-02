package webmon.core;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.glassfish.jersey.server.mvc.Viewable;

import webmon.models.Website;
import webmon.models.User;
import webmon.utils.Constants;
import webmon.utils.DatastoreUtils;

//Map this class to /websites route
@Path("/websites")
public class Websites {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	@Context
	HttpServletRequest httpRequest;
	
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
		
		try {
			User loggedInUser = (User) httpRequest.getSession(false).getAttribute("user");
			Website website;
			if(DatastoreUtils.checkWebsite(url)) {
				website = DatastoreUtils.getWebsite(url);
				if(website.getUsers().contains(loggedInUser.getId()))
					return "{ \"result\": \"" + Constants.stringWebsiteExists + "\"}";
			} else {
				website = new Website(url, name, loggedInUser.getId());
				DatastoreUtils.putWebsite(website);
			}
			
			loggedInUser.getMonitoredWebsites().add(website.getId());
			DatastoreUtils.putUser(loggedInUser);
			
			return "{ \"result\": \"" + Constants.stringWebsiteAdded + "\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"result\": \"fail\"}";
		}
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateWebsite(@PathParam("id") int id) {
		return "{ \"result\": \"Successfully updated website with id: " + id + "\"}";
	}
}
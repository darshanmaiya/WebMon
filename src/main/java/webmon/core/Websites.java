package webmon.core;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.glassfish.jersey.server.mvc.Viewable;

import webmon.models.Website;
import webmon.models.ResponseInfo;
import webmon.models.User;
import webmon.utils.Constants;
import webmon.utils.DatastoreUtils;

/**
 * The Class Websites.
 */
//Map this class to /websites route
@Path("/websites")
public class Websites {
	// Allows to insert contextual objects into the class,
	/** The uri info. */
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	
	/** The request. */
	@Context
	Request request;
	
	/** The http request. */
	@Context
	HttpServletRequest httpRequest;
	
	/**
	 * Returns the html which allows the user to add a new website.
	 *
	 * @return the response html
	 */
	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_HTML)
	public Response addWebsite() {
		return Response.ok(new Viewable(Constants.jspRoot + "addWebsite.jsp", null)).build();
	}
	
	/**
	 * Gets the website details with given id.
	 *
	 * @param id the id of the required website
	 * @return the website details in json format
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Website getWebsite(@PathParam("id") long id) {
		Website website = DatastoreUtils.getWebsite(id);
		User user = DatastoreUtils.getUser(String.valueOf(httpRequest.getSession(false).getAttribute("user")));
		int index = website.getUsers().indexOf(Long.valueOf(user.getId()));
		website.setUsers(null);
		List<Boolean> notifyWhenResponseIsHigh = new ArrayList<Boolean>();
		notifyWhenResponseIsHigh.add(website.getNotifyWhenResponseIsHigh().get(index));
		List<Boolean> notifyWhenDown = new ArrayList<Boolean>();
		notifyWhenDown.add(website.getNotifyWhenDown().get(index));
		website.setNotifyWhenResponseIsHigh(notifyWhenResponseIsHigh);
		website.setNotifyWhenDown(notifyWhenDown);
		
		int websiteIndex = user.getMonitoredWebsites().indexOf(website.getId());
		int websiteIndexInUser = 0;
		if(user.getMonitorWebsiteStart().get(websiteIndex) instanceof Integer) {
			websiteIndexInUser = user.getMonitorWebsiteStart().get(websiteIndex);
		}
		
		List<ResponseInfo> responseInfo = new ArrayList<ResponseInfo>();
		if(websiteIndexInUser < website.getResponseInfo().size())
			responseInfo.addAll(website.getResponseInfo().subList(websiteIndexInUser, website.getResponseInfo().size()));
		website.setResponseInfo(responseInfo);
		
		return website;
	}

	/**
	 * Gets the website html which allows user to see the details of a given website.
	 *
	 * @param id the id of the required website
	 * @return the website html
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response getWebsiteHTML(@PathParam("id") int id) {
		return Response.ok(new Viewable(Constants.jspRoot + "website.jsp", null)).build();
	}
	
	/**
	 * Add a new website.
	 *
	 * @param name Name of website
	 * @param url URL of website
	 * @param notifyDown Whether to notify when the website is down
	 * @param notifyResponse Whether to notify when the website has high response time
	 * @return the json containing success or failure
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String saveWebsite(@FormParam("name") String name,
			@FormParam("url") String url,
			@FormParam("notifyDown") String notifyDown,
			@FormParam("notifyResponse") String notifyResponse) {
		
		try {
			User loggedInUser = DatastoreUtils.getUser(String.valueOf(httpRequest.getSession(false).getAttribute("user")));
			Website website;
			if(DatastoreUtils.checkWebsite(url)) {
				website = DatastoreUtils.getWebsite(url);
				if(website.getUsers().contains(loggedInUser.getId()))
					return "{ \"result\": \"" + Constants.stringWebsiteExists + "\"}";
				else
					website.addUser(loggedInUser.getId(), (notifyDown != null), (notifyResponse != null));
			} else {
				website = new Website(url, name, loggedInUser.getId(), (notifyDown != null), (notifyResponse != null));
			}
			
			DatastoreUtils.putWebsite(website);
			loggedInUser.getMonitoredWebsites().add(website.getId());
			loggedInUser.getMonitorWebsiteStart().add(website.getResponseInfo().size());
			DatastoreUtils.putUser(loggedInUser);
			
			return "{ \"result\": \"" + Constants.stringWebsiteAdded + "\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"result\": \"fail\"}";
		}
	}
	
	/**
	 * Update an existing website.
	 *
	 * @param url URL of website
	 * @param notifyDown Whether to notify when the website is down
	 * @param notifyResponse Whether to notify when the website has high response time
	 * @return the json containing success or failure
	 */
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateWebsite(@FormParam("url") String url,
			@FormParam("notifyDown") String notifyDown,
			@FormParam("notifyResponse") String notifyResponse) {
		try {
			Website website = DatastoreUtils.getWebsite(url);
			boolean notDown = true;
			boolean notRes = true;
			if(notifyDown == null || notifyDown.equals(""))
				notDown = false;
			if(notifyResponse == null || notifyResponse.equals(""))
				notRes = false;
			
			User user = DatastoreUtils.getUser(String.valueOf(httpRequest.getSession(false).getAttribute("user")));
			int userid = website.getUsers().indexOf(Long.valueOf(user.getId()));
			
			website.getNotifyWhenDown().set(userid, notDown);
			website.getNotifyWhenResponseIsHigh().set(userid, notRes);
			
			DatastoreUtils.putWebsite(website);
			
			return "{ \"result\": \"Successfully updated website with email: " + website.getUrl() + "\", \"status\": 200}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "{ \"result\": \"Updating website with given details failed.\", \"status\": 500}";
	}
	
	/**
	 * Delete a website.
	 *
	 * @param id id of website to delete
	 * @return the json containing success or failure
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteWebsite(@PathParam("id") long id) {
		try {
			Website website = DatastoreUtils.getWebsite(id);
			User user = DatastoreUtils.getUser(String.valueOf(httpRequest.getSession(false).getAttribute("user")));
			
			website.removeUser(user.getId());
			
			int userIndex = user.getMonitoredWebsites().indexOf(id);
			user.getMonitoredWebsites().remove(userIndex);
			user.getMonitorWebsiteStart().remove(userIndex);
			
			DatastoreUtils.putWebsite(website);
			DatastoreUtils.putUser(user);
			
			return "{ \"result\": \"Successfully deleted website with email: " + website.getUrl() + "\", \"status\": 200}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "{ \"result\": \"Deleting website with given details failed.\", \"status\": 500}";
	}
}
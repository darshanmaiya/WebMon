package webmon.core;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * The Class HelloJersey.
 */
//Map this class to /hello route
@Path("/hello")
public class HelloJersey {
	// Allows to insert contextual objects into the class,
	/** The uri info. */
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	
	/** The request. */
	@Context
	Request request;
	
	/**
	 * Sanity check method for JAXRS.
	 *
	 * @return Hello Jersey
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHelloJersey() {
		return "Hello Jersey";
	}
}
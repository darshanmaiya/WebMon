package webmon.core;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

//Map this class to /hello route
@Path("/hello")
public class HelloJersey {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHelloJersey() {
		return "Hello Jersey";
	}
}
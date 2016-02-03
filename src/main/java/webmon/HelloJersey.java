package webmon;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

//Map this class to /test route
@Path("/test")
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
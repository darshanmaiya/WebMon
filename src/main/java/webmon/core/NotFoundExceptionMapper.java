package webmon.core;

import java.net.URI;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
	public Response toResponse(NotFoundException exception) {
		URI uri = UriBuilder.fromUri("/error").build();
	    return Response.seeOther(uri).build();
    }
}

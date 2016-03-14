package webmon.core;

import java.net.URI;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * The Class NotFoundExceptionMapper.
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(NotFoundException exception) {
		URI uri = UriBuilder.fromUri("/error").build();
	    return Response.seeOther(uri).build();
    }
}

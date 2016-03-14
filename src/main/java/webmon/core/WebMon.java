package webmon.core;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * The Class WebMon for creating a new JAX-RS application.
 */
public class WebMon extends Application {
 
	/* (non-Javadoc)
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
	public Set<Class<?>> getClasses() {
		
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(Dashboard.class);
		classes.add(Users.class);
		classes.add(org.glassfish.jersey.server.mvc.jsp.JspMvcFeature.class);
		return classes;
	}
}
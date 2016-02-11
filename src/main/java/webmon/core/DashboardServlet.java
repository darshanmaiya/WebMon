package webmon.core;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

//Map this class to /test route
@Path("/{default: .*}")
public class DashboardServlet {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHelloJersey() {
		return "Dashboard";
	}
	/*@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.getRequestDispatcher(Constants.jspRoot + "dashboard.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
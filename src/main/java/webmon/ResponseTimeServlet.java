package webmon;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.taskqueue.*;

import webmon.models.Website;
import webmon.utils.DatastoreUtils;

@SuppressWarnings("serial")
public class ResponseTimeServlet extends HttpServlet {
	private final List<Website> allWebsites = DatastoreUtils.getAllWebsites();
	private int i = 0;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		for(i=0; i<allWebsites.size(); i++) {
			Website website = allWebsites.get(i);
			if(website.getUsers().size() == 0) {
				DatastoreUtils.deleteWebsite(website.getUrl());
				continue;
			}
			
			Queue queue = QueueFactory.getDefaultQueue();
	        queue.add(TaskOptions.Builder.withUrl("/pinger")
					.param("id", String.valueOf(website.getId())));
		}
		
		resp.setStatus(HttpServletResponse.SC_OK);
	}
}
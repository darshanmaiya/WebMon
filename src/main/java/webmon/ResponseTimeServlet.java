package webmon;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.ThreadManager;

import webmon.models.Website;
import webmon.utils.DatastoreUtils;

@SuppressWarnings("serial")
public class ResponseTimeServlet extends HttpServlet {
	private final List<Website> allWebsites = DatastoreUtils.getAllWebsites();
	private int i = 0;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		for(i=0; i<allWebsites.size(); i++) {
			if(allWebsites.get(i).getUsers().size() == 0) {
				DatastoreUtils.deleteWebsite(allWebsites.get(i).getUrl());
				continue;
			}
			ThreadManager.createBackgroundThread(
				new Pinger(i, allWebsites))
			.start();
		}
	}
}
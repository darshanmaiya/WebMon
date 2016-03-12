package webmon;

import java.util.List;

import webmon.models.Website;
import webmon.utils.DatastoreUtils;

public class Pinger implements Runnable {
	private int id;
	private List<Website> allWebsites;
	
    public Pinger(int id, List<Website> allWebsites) {
       this.id = id;
       this.allWebsites = allWebsites;
    }
    
	@Override
	public void run() {
		Website w = allWebsites.get(id);
		w.getResponseTime();
		DatastoreUtils.putWebsite(w);
	}
}
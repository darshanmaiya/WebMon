package webmon.models;

import com.google.appengine.api.datastore.Entity;

import webmon.utils.DatastoreUtils;

public class WebMonInfo {
	private static long numUsers = 0;
	private static long numWebsites = 0;
	
	public static long getNewUserId () {
		DatastoreUtils.getWebMonInfo();
		++WebMonInfo.numUsers;
		DatastoreUtils.putWebMonInfo();
		
		return WebMonInfo.numUsers;
	}
	
	public static long getNewWebsiteId () {
		DatastoreUtils.getWebMonInfo();
		++WebMonInfo.numWebsites;
		DatastoreUtils.putWebMonInfo();
		
		return WebMonInfo.numWebsites;
	}
	
	public static Entity toEntity () {
        Entity entity = new Entity("WebMonInfo", "WebMonInfoKey");
        entity.setProperty("numUsers", numUsers);
        entity.setProperty("numWebsites", numWebsites);
        return entity;
    }

	public static void fromEntity (Entity entity) {
		if(entity != null) {
			numUsers = (long)entity.getProperty("numUsers");
			numWebsites = (long)entity.getProperty("numWebsites");
		}
    }
}

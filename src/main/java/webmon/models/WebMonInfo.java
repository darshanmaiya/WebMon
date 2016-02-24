package webmon.models;

import java.io.Serializable;

import com.google.appengine.api.datastore.Entity;

import webmon.utils.Constants;
import webmon.utils.DatastoreUtils;

public class WebMonInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2383497804974143721L;
	private static long numUsers = 0;
	private static long numWebsites = 0;
	
	public static long getNewUserId () {
		if(numUsers != 0)
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
        Entity entity = new Entity(Constants.stringWebMonInfo, Constants.stringWebMonInfoKey);
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

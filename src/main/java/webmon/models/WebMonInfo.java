package webmon.models;

import java.io.Serializable;

import com.google.appengine.api.datastore.Entity;

import webmon.utils.Constants;
import webmon.utils.DatastoreUtils;

/**
 * The Class WebMonInfo.
 */
public class WebMonInfo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2383497804974143721L;
	
	/** The number of users instantiated. */
	private static long numUsers = 0;
	
	/** The number of websites created. */
	private static long numWebsites = 0;
	
	/**
	 * Gets a new user id.
	 *
	 * @return the new user id
	 */
	public static long getNewUserId () {
		DatastoreUtils.getWebMonInfo();
		++WebMonInfo.numUsers;
		DatastoreUtils.putWebMonInfo();
		
		return WebMonInfo.numUsers;
	}
	
	/**
	 * Gets a new website id.
	 *
	 * @return the new website id
	 */
	public static long getNewWebsiteId () {
		DatastoreUtils.getWebMonInfo();
		++WebMonInfo.numWebsites;
		DatastoreUtils.putWebMonInfo();
		
		return WebMonInfo.numWebsites;
	}
	
	/**
	 * To app engine entity.
	 *
	 * @return the entity
	 */
	public static Entity toEntity () {
        Entity entity = new Entity(Constants.stringWebMonInfo, Constants.stringWebMonInfoKey);
        entity.setProperty("numUsers", numUsers);
        entity.setProperty("numWebsites", numWebsites);
        return entity;
    }

	/**
	 * From app engine entity.
	 *
	 * @param entity the entity
	 */
	public static void fromEntity (Entity entity) {
		if(entity != null) {
			numUsers = (long)entity.getProperty("numUsers");
			numWebsites = (long)entity.getProperty("numWebsites");
		}
    }
}

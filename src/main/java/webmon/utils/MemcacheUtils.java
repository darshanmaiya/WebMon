package webmon.utils;

import java.util.logging.*;

import com.google.appengine.api.memcache.*;

import webmon.models.User;
import webmon.models.WebMonInfo;
import webmon.models.Website;

/**
 * The Class MemcacheUtils.
 */
public final class MemcacheUtils {

	private static final MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
	
	/**
	 * Gets the user from memcache if it exists or null.
	 *
	 * @param key the key
	 * @return User
	 */
	public static User getUser(String key) {

		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	return (User) syncCache.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Store user in memcache.
     *
     * @param user User
     */
    public static void putUser(User user) {

    	syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	syncCache.put(user.getEmail(), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the WebMonInfo class from memcache.
     *
     * @return the WebMonInfo class
     */
    public static Object getWebMonInfo() {

    	syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	return syncCache.get(Constants.stringWebMonInfoKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Store WebMonInfo class to memcache.
     */
    public static void putWebMonInfo() {

    	syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	syncCache.put(Constants.stringWebMonInfoKey, WebMonInfo.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the website from memcache.
     *
     * @param url URL
     * @return the Website
     */
    public static Website getWebsite(String url) {

		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	return (Website) syncCache.get(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Stores website in memcache.
     *
     * @param website the website
     */
    public static void putWebsite(Website website) {

    	syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	syncCache.put(website.getUrl(), website);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete website from memcache.
     *
     * @param url the url
     */
    public static void deleteWebsite(String url) {

    	syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	syncCache.delete(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

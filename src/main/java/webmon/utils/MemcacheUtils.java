package webmon.utils;

import java.util.logging.*;

import com.google.appengine.api.memcache.*;

import webmon.models.User;
import webmon.models.WebMonInfo;

public final class MemcacheUtils {

	private static final MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
	
	public static User getUser(String key) {

		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	return (User) syncCache.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void putUser(User user) {

    	syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	syncCache.put(user.getEmail(), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getWebMonInfo() {

    	syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	return syncCache.get(Constants.stringWebMonInfoKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void putWebMonInfo() {

    	syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        try {
        	syncCache.put(Constants.stringWebMonInfoKey, WebMonInfo.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

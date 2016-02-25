package webmon.utils;

import com.google.appengine.api.datastore.*;

import webmon.models.User;
import webmon.models.WebMonInfo;

public final class DatastoreUtils {
	
    private static final DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    
    public static final boolean checkUser(String key) {
        try {
        	Object user = MemcacheUtils.getUser(key);
        	if(user == null)
        		user = datastoreService.get(KeyFactory.createKey(Constants.stringUser, key));
        	
        	return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
    
    public static final boolean checkUserCredentials(String key, String password, User loggedInUser) {
        try {
        	Object user = MemcacheUtils.getUser(key);
        	if(user == null) {
        		user = datastoreService.get(KeyFactory.createKey(Constants.stringUser, key));
        	}
        	
        	if(user instanceof Entity)
        		loggedInUser.fromEntity((Entity) user);
        	else
        		loggedInUser = (User)user;
        	
        	if(loggedInUser.getPassword().equals(password)) {
        		return true;
        	}
        	
        	return false;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
    
    public static final User getUser(String key) {
        try {
        	User user = MemcacheUtils.getUser(key);
        	if(user != null)
        		return user;
            return new User().fromEntity(datastoreService.get(KeyFactory.createKey(Constants.stringUser, key)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static final void putUser(User user) {
        try {
        	MemcacheUtils.putUser(user);
            datastoreService.put(user.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void getWebMonInfo() {
        try {
        	Entity cachedObject = (Entity) MemcacheUtils.getWebMonInfo();
        	if(cachedObject != null)
        		WebMonInfo.fromEntity(cachedObject);
        	else
        		WebMonInfo.fromEntity(datastoreService.get(KeyFactory.createKey(Constants.stringWebMonInfo, Constants.stringWebMonInfoKey)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final void putWebMonInfo() {
        try {
        	MemcacheUtils.putWebMonInfo();
            datastoreService.put(WebMonInfo.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

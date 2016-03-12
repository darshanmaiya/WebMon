package webmon.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import webmon.models.User;
import webmon.models.WebMonInfo;
import webmon.models.Website;

public final class DatastoreUtils {
	
    private static final DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    
    public static final boolean checkUser(String key) {
        try {
        	Object user = MemcacheUtils.getUser(key);
        	if(user == null) {
        		user = datastoreService.get(KeyFactory.createKey(Constants.stringUser, key));
        		MemcacheUtils.putUser(new User().fromEntity((Entity) user));
        	}
        	
        	return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
    
    public static final boolean checkUserCredentials(String key, String password) {
        try {
        	User loggedInUser;
        	Object user = MemcacheUtils.getUser(key);
        	if(user == null) {
        		user = datastoreService.get(KeyFactory.createKey(Constants.stringUser, key));
        		loggedInUser = new User().fromEntity((Entity) user);
        		MemcacheUtils.putUser(loggedInUser);
        	} else
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
    	LoggingUtils.logMsg("Get user: " + key);
        try {
        	User user = MemcacheUtils.getUser(key);
        	if(user != null)
        		return user;
        	
        	user = new User().fromEntity(datastoreService.get(KeyFactory.createKey(Constants.stringUser, key)));
        	MemcacheUtils.putUser(user);
        	
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static final User getUser(long id) {
    	LoggingUtils.logMsg("Get user: " + id);
        try {
        	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        	Filter idFilter = new FilterPredicate("id", FilterOperator.EQUAL, id);
        	
        	Query q = new Query(Constants.stringUser).setFilter(idFilter);
        	PreparedQuery pq = datastore.prepare(q);

        	for (Entity result : pq.asIterable()) {
        	  return new User().fromEntity(result);
        	}
        	
        	return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static final void putUser(User user) {
    	LoggingUtils.logMsg("Store user: " + user.getEmail());
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
        	if(cachedObject != null) {
        		WebMonInfo.fromEntity(cachedObject);
        	}
        	else {
        		WebMonInfo.fromEntity(datastoreService.get(KeyFactory.createKey(Constants.stringWebMonInfo, Constants.stringWebMonInfoKey)));
        		MemcacheUtils.putWebMonInfo();
        	}
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
    
    public static final Website getWebsite(String url) {
    	LoggingUtils.logMsg("Get website: " + url);
        try {
        	Website website = MemcacheUtils.getWebsite(url);
        	if(website != null)
        		return website;
        	
        	website = new Website().fromEntity(datastoreService.get(KeyFactory.createKey(Constants.stringWebsite, url)));
        	MemcacheUtils.putWebsite(website);
        	
            return website;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static final void putWebsite(Website website) {
    	LoggingUtils.logMsg("Put website: " + website.getUrl());
        try {
        	MemcacheUtils.putWebsite(website);
            datastoreService.put(website.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final boolean checkWebsite(String url) {
        try {
        	Object website = MemcacheUtils.getWebsite(url);
        	if(website == null) {
        		website = datastoreService.get(KeyFactory.createKey(Constants.stringWebsite, url));
        		MemcacheUtils.putWebsite(new Website().fromEntity((Entity) website));
        	}
        	
        	return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
    
    public static final Website getWebsite(long id) {
    	LoggingUtils.logMsg("Get website: " + id);
        try {
        	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        	Filter idFilter = new FilterPredicate("id", FilterOperator.EQUAL, id);
        	
        	Query q = new Query(Constants.stringWebsite).setFilter(idFilter);
        	PreparedQuery pq = datastore.prepare(q);

        	for (Entity result : pq.asIterable()) {
        	  return new Website().fromEntity(result);
        	}
        	
        	return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static final List<Website> getAllWebsites() {
    	LoggingUtils.logMsg("Get all websites");
        try {
        	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        	Query q = new Query(Constants.stringWebsite);
        	PreparedQuery pq = datastore.prepare(q);

        	List<Website> allWebsites = new ArrayList<Website>();
        	
        	for (Entity result : pq.asIterable()) {
        		allWebsites.add(new Website().fromEntity(result));
        	}
        	
        	return allWebsites;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static final void deleteWebsite(String key) {
    	LoggingUtils.logMsg("Delete website with key: " + key);
        try {
        	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        	datastore.delete(KeyFactory.createKey(Constants.stringWebsite, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package webmon.utils;

import com.google.appengine.api.datastore.*;

import webmon.models.User;
import webmon.models.WebMonInfo;

public class DatastoreUtils {
	private static final String strWebMonInfo = "WebMonInfo";
	private static final String strUser = "User";
	//private static final String strWebsite = "Website";
	
    private static final DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    public static User getUser(String key) {
        try {
            return new User().fromEntity(datastoreService.get(KeyFactory.createKey(strUser, key)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void putUser(User user) {
        try {
            datastoreService.put(user.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getWebMonInfo() {
        try {
        	WebMonInfo.fromEntity(datastoreService.get(KeyFactory.createKey(strWebMonInfo, "WebMonInfoKey")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void putWebMonInfo() {
        try {
            datastoreService.put(WebMonInfo.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

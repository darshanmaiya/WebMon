package webmon.models;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;

import webmon.Notifier;
import webmon.utils.Constants;
import webmon.utils.DatastoreUtils;

/**
 * The Class Website.
 */
@XmlRootElement
public class Website implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 458515780704922262L;
	
	/** The id. */
	private long id;
	
	/** The url. */
	private String url;
	
	/** The response info. */
	private List<ResponseInfo> responseInfo;
	
	/** The name. */
	private String name;
	
	/** The users. */
	private List<Long> users;
	
	/** The notify when down. */
	private List<Boolean> notifyWhenDown;
	
	/** The notify when response is high. */
	private List<Boolean> notifyWhenResponseIsHigh;
	
	/** The last notification. */
	private long lastNotification = 0;
	
	/** The response threshold. */
	private final int RESPONSE_THRESHOLD = 5000; // (in milliseconds)
	
	/** The notification timeout. */
	private final long NOTIFICATION_TIMEOUT = 21600000; // (in milliseconds)
	
	/**
	 * Instantiates a new website.
	 */
	public Website() {
	}
	
	/**
	 * Instantiates a new website.
	 *
	 * @param url the url
	 * @param name the name
	 * @param user the user
	 * @param notifyWhenDown the notify when down
	 * @param notifyWhenResponseIsHigh the notify when response is high
	 */
	public Website(String url, String name, long user, boolean notifyWhenDown, boolean notifyWhenResponseIsHigh)
	{
		this.id = WebMonInfo.getNewWebsiteId();
		this.url = url;
		this.name = name;
		this.users = new ArrayList<Long>();
		this.notifyWhenDown = new ArrayList<Boolean>();
		this.notifyWhenResponseIsHigh = new ArrayList<Boolean>();
		this.users.add(user);
		this.notifyWhenDown.add(notifyWhenDown);
		this.notifyWhenResponseIsHigh.add(notifyWhenResponseIsHigh);
		this.responseInfo = new ArrayList<ResponseInfo>();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the response info.
	 *
	 * @return the response info
	 */
	public List<ResponseInfo> getResponseInfo() {
		return responseInfo;
	}

	/**
	 * Sets the response info.
	 *
	 * @param responseInfo the new response info
	 */
	public void setResponseInfo(List<ResponseInfo> responseInfo) {
		this.responseInfo = responseInfo;
	}
	
	/**
	 * Gets the response time.
	 */
	public void getResponseTime () {
		long responseTime = 0;
		try {
			URL url = new URL(this.url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
        	connection.setConnectTimeout(1000 * RESPONSE_THRESHOLD);   // 10 seconds
        	
			long startTime = System.currentTimeMillis();
			
		    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    while (reader.readLine() != null)
		    	;
		    reader.close();
		    long endTime = System.currentTimeMillis();
		    
		    int code = connection.getResponseCode();
	       	if (code >= 200 && code < 400) {
	        	responseTime = endTime - startTime;
	        } else {
	        	responseTime = 0;
	        }
		} catch(Exception e) {
			 e.printStackTrace();
			 responseTime = 0;
		}

        // Create responseInfo object
		ResponseInfo now = new ResponseInfo(responseTime);
		responseInfo.add(now);
		
		if(responseTime == 0 || responseTime >= RESPONSE_THRESHOLD) {
			long curTime = System.currentTimeMillis();
			int size = responseInfo.size();
			if(curTime - lastNotification < NOTIFICATION_TIMEOUT || size < 5)
				return;
			
			lastNotification = curTime;
			
			if(responseTime == 0 &&
					responseInfo.get(size-2).responseTime == 0 &&
					responseInfo.get(size-3).responseTime == 0) {
				int i = 0;
				for(boolean notifyDown : notifyWhenDown) {
					if(!notifyDown)
						continue;
					
					User user = DatastoreUtils.getUser(this.getUsers().get(i));
					Notifier.notifyViaMail(user, Constants.stringWebsiteDown.replace("{0}", this.name));
					Notifier.notifyViaSms(user, Constants.stringWebsiteDown.replace("{0}", this.name));
					i++;
				}
			} else if((responseTime + responseInfo.get(size-2).responseTime +
					responseInfo.get(size-3).responseTime)/3 >= RESPONSE_THRESHOLD) {
				int i = 0;
				for(boolean notifyResponseHigh : notifyWhenResponseIsHigh) {
					if(!notifyResponseHigh)
						continue;
					
					User user = DatastoreUtils.getUser(this.getUsers().get(i));
					Notifier.notifyViaMail(user, Constants.stringWebsiteHighResponse.replace("{0}", this.name));
					Notifier.notifyViaSms(user, Constants.stringWebsiteHighResponse.replace("{0}", this.name));
					i++;
				}
			}
		}
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<Long> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<Long> users) {
		this.users = users;
	}
	
	/**
	 * Adds the user.
	 *
	 * @param userId the user id
	 * @param notifyDown the notify down
	 * @param notifyResponse the notify response
	 */
	public void addUser(long userId, boolean notifyDown, boolean notifyResponse) {
		this.users.add(userId);
		this.notifyWhenDown.add(notifyDown);
		this.notifyWhenResponseIsHigh.add(notifyResponse);
	}
	
	/**
	 * Removes the user.
	 *
	 * @param userId the user id
	 */
	public void removeUser(long userId) {
		int i = this.users.indexOf(userId);
		if(i != -1) {
			this.users.remove(i);
			this.notifyWhenDown.remove(i);
			this.notifyWhenResponseIsHigh.remove(i);
		}
	}

    /**
     * Gets the notify when down.
     *
     * @return the notify when down
     */
    public List<Boolean> getNotifyWhenDown() {
		return notifyWhenDown;
	}

	/**
	 * Sets the notify when down.
	 *
	 * @param notifyWhenDown the new notify when down
	 */
	public void setNotifyWhenDown(List<Boolean> notifyWhenDown) {
		this.notifyWhenDown = notifyWhenDown;
	}

	/**
	 * Gets the notify when response is high.
	 *
	 * @return the notify when response is high
	 */
	public List<Boolean> getNotifyWhenResponseIsHigh() {
		return notifyWhenResponseIsHigh;
	}

	/**
	 * Sets the notify when response is high.
	 *
	 * @param notifyWhenResponseIsHigh the new notify when response is high
	 */
	public void setNotifyWhenResponseIsHigh(List<Boolean> notifyWhenResponseIsHigh) {
		this.notifyWhenResponseIsHigh = notifyWhenResponseIsHigh;
	}

	/**
	 * Gets the last notification.
	 *
	 * @return the last notification
	 */
	public long getLastNotification() {
		return lastNotification;
	}

	/**
	 * Sets the last notification.
	 *
	 * @param lastNotification the new last notification
	 */
	public void setLastNotification(long lastNotification) {
		this.lastNotification = lastNotification;
	}
	
	/**
	 * To entity.
	 *
	 * @return the entity
	 */
	public Entity toEntity () {
        Entity entity = new Entity(Constants.stringWebsite, getUrl());
        entity.setProperty("name", getName());
        entity.setProperty("url", getUrl());
        entity.setProperty("id", getId());
        entity.setProperty("users", getUsers());
        entity.setProperty("notifyWhenDown", getNotifyWhenDown());
        entity.setProperty("notifyWhenResponseIsHigh", getNotifyWhenResponseIsHigh());
        entity.setProperty("lastNotification", getLastNotification());
        entity.setProperty("responseInfo", ResponseInfo.toEmbeddedEntity(getResponseInfo()));
        return entity;
    }

	/**
	 * From entity.
	 *
	 * @param entity the entity
	 * @return the website
	 */
	@SuppressWarnings("unchecked")
	public Website fromEntity (Entity entity) {
        setName((String) entity.getProperty("name"));
        setUrl((String) entity.getProperty("url"));
        setId((long) entity.getProperty("id"));
        setUsers((List<Long>) entity.getProperty("users"));
        setNotifyWhenDown((List<Boolean>) entity.getProperty("notifyWhenDown"));
        setNotifyWhenResponseIsHigh((List<Boolean>) entity.getProperty("notifyWhenResponseIsHigh"));
        setLastNotification((long) entity.getProperty("lastNotification"));
        Object ris = entity.getProperty("responseInfo");
        if(ris != null)
        	setResponseInfo(ResponseInfo.fromEmbeddedEntity((ArrayList<EmbeddedEntity>) ris));
        
        if(users == null)
        	this.users = new ArrayList<Long>();
        
        if(notifyWhenDown == null)
        	this.notifyWhenDown = new ArrayList<Boolean>();
        
        if(notifyWhenResponseIsHigh == null)
        	this.notifyWhenResponseIsHigh = new ArrayList<Boolean>();
        
        if(responseInfo == null)
        	this.responseInfo = new ArrayList<ResponseInfo>();
        
        return this;
    }
}

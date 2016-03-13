package webmon.models;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;

import webmon.utils.Constants;

@XmlRootElement
public class Website implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 458515780704922262L;
	private long id;
	private String url;
	private List<ResponseInfo> responseInfo;
	private String name;
	private List<Long> users;
	private List<Boolean> notifyWhenDown;
	private List<Boolean> notifyWhenResponseIsHigh;
	private final int RESPONSE_THRESHOLD = 5000; // (in milliseconds)
	
	public Website() {
	}
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ResponseInfo> getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(List<ResponseInfo> responseInfo) {
		this.responseInfo = responseInfo;
	}
	
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
	        	responseTime = -1 * code;
	        }
		} catch(Exception e) {
			 e.printStackTrace();
			 responseTime = 0;
		}

        // Create responseInfo object
		ResponseInfo now = new ResponseInfo(responseTime);
		responseInfo.add(now);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getUsers() {
		return users;
	}

	public void setUsers(List<Long> users) {
		this.users = users;
	}
	
	public void addUser(long userId, boolean notifyDown, boolean notifyResponse) {
		this.users.add(userId);
		this.notifyWhenDown.add(notifyDown);
		this.notifyWhenResponseIsHigh.add(notifyResponse);
	}
	
	public void removeUser(long userId) {
		int i = this.users.indexOf(userId);
		if(i != -1) {
			this.users.remove(i);
			this.notifyWhenDown.remove(i);
			this.notifyWhenResponseIsHigh.remove(i);
		}
	}

    public List<Boolean> getNotifyWhenDown() {
		return notifyWhenDown;
	}

	public void setNotifyWhenDown(List<Boolean> notifyWhenDown) {
		this.notifyWhenDown = notifyWhenDown;
	}

	public List<Boolean> getNotifyWhenResponseIsHigh() {
		return notifyWhenResponseIsHigh;
	}

	public void setNotifyWhenResponseIsHigh(List<Boolean> notifyWhenResponseIsHigh) {
		this.notifyWhenResponseIsHigh = notifyWhenResponseIsHigh;
	}

	public Entity toEntity () {
        Entity entity = new Entity(Constants.stringWebsite, getUrl());
        entity.setProperty("name", getName());
        entity.setProperty("url", getUrl());
        entity.setProperty("id", getId());
        entity.setProperty("users", getUsers());
        entity.setProperty("notifyWhenDown", getNotifyWhenDown());
        entity.setProperty("notifyWhenResponseIsHigh", getNotifyWhenResponseIsHigh());
        entity.setProperty("responseInfo", ResponseInfo.toEmbeddedEntity(getResponseInfo()));
        return entity;
    }

	@SuppressWarnings("unchecked")
	public Website fromEntity (Entity entity) {
        setName((String) entity.getProperty("name"));
        setUrl((String) entity.getProperty("url"));
        setId((long) entity.getProperty("id"));
        setUsers((List<Long>) entity.getProperty("users"));
        setNotifyWhenDown((List<Boolean>) entity.getProperty("notifyWhenDown"));
        setNotifyWhenResponseIsHigh((List<Boolean>) entity.getProperty("notifyWhenResponseIsHigh"));
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

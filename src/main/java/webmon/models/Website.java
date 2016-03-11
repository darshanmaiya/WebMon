package webmon.models;

import java.io.Serializable;
import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

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
	private Pinger pinger;
	private String name;
	private List<Long> users;
	private List<Boolean> notifyWhenDown;
	private List<Boolean> notifyWhenResponseIsHigh;
	private List<Boolean> isBeingMonitored;
	public static long numUsersMonitoring;
	
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
		this.isBeingMonitored = new ArrayList<Boolean>();
		this.users.add(user);
		this.notifyWhenDown.add(notifyWhenDown);
		this.notifyWhenResponseIsHigh.add(notifyWhenResponseIsHigh);
		this.isBeingMonitored.add(false);
		this.responseInfo = new ArrayList<ResponseInfo>();
		
		Website.numUsersMonitoring++;
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
	
	public void startMonitoring(){
		this.pinger = new Pinger();
		this.pinger.startService(url, responseInfo);
	}
	
	public void stopMonitoring(){
		this.pinger.stopService();
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

	public List<Boolean> getIsBeingMonitored() {
		return isBeingMonitored;
	}

	public void setIsBeingMonitored(List<Boolean> isBeingMonitored) {
		this.isBeingMonitored = isBeingMonitored;
	}

	public Entity toEntity () {
        Entity entity = new Entity(Constants.stringWebsite, getUrl());
        entity.setProperty("name", getName());
        entity.setProperty("url", getUrl());
        entity.setProperty("id", getId());
        entity.setProperty("users", getUsers());
        entity.setProperty("notifyWhenDown", getNotifyWhenDown());
        entity.setProperty("notifyWhenResponseIsHigh", getNotifyWhenResponseIsHigh());
        entity.setProperty("isBeingMonitored", getIsBeingMonitored());
        entity.setProperty("responseInfo", getResponseInfo());
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
        setIsBeingMonitored((List<Boolean>) entity.getProperty("isBeingMonitored"));
        setResponseInfo((ArrayList<ResponseInfo>) entity.getProperty("responseInfo"));
        return this;
    }
}

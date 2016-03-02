package webmon.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private Set<Long> users;
	
	public Website() {
	}
	
	public Website(String url, String name, long user)
	{
		this.id = WebMonInfo.getNewWebsiteId();
		this.url = url;
		this.name = name;
		this.users = new HashSet<Long>();
		this.users.add(user);
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

	public Set<Long> getUsers() {
		return users;
	}

	public void setUsers(Set<Long> users) {
		this.users = users;
	}
	
	public Entity toEntity () {
        Entity entity = new Entity(Constants.stringWebsite, getUrl());
        entity.setProperty("name", getName());
        entity.setProperty("url", getUrl());
        entity.setProperty("id", getId());
        entity.setProperty("users", getUsers());
        entity.setProperty("responseInfo", getResponseInfo());
        return entity;
    }

    @SuppressWarnings("unchecked")
	public Website fromEntity (Entity entity) {
        setName((String) entity.getProperty("name"));
        setUrl((String) entity.getProperty("url"));
        setId((long) entity.getProperty("id"));
        setUsers((HashSet<Long>) entity.getProperty("users"));
        setResponseInfo((ArrayList<ResponseInfo>) entity.getProperty("responseInfo"));
        return this;
    }
}

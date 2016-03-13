package webmon.models;

import java.io.Serializable;
import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Entity;

import webmon.utils.Constants;

@XmlRootElement
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2290949061273594465L;
	private String name;
	private String email;
	private String phone;
	private String password;
	private long id;
	private List<Long> monitoredWebsites;
	private List<Integer> monitorWebsiteStart;
	
	// Constructors
	public User () {
		
	}
	
	public User (String name, String email, String phone, String password){
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.id = WebMonInfo.getNewUserId();
		this.monitoredWebsites = new ArrayList<Long>();
		this.setMonitorWebsiteStart(new ArrayList<Integer>());
	}
	
	// Getters and setters
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getPhone () {
		return phone;
	}
	
	public void setPhone (String phone) {
		this.phone = phone;
	}
	
	public String getPassword (){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public long getId (){
		return id;
	}

	public void setId (long id){
		this.id = id;
	}
	
	public List<Long> getMonitoredWebsites (){
		return monitoredWebsites;
	}
	
	public void setMonitoredWebsites (ArrayList<Long> monitoredWebsites){
		this.monitoredWebsites = monitoredWebsites;
	}

	public List<Integer> getMonitorWebsiteStart() {
		return monitorWebsiteStart;
	}

	public void setMonitorWebsiteStart(ArrayList<Integer> monitorWebsiteStart) {
		this.monitorWebsiteStart = monitorWebsiteStart;
	}
	
	public Entity toEntity () {
        Entity entity = new Entity(Constants.stringUser, getEmail());
        entity.setProperty("name", getName());
        entity.setProperty("email", getEmail());
        entity.setProperty("id", getId());
        entity.setProperty("phone", getPhone());
        entity.setProperty("password", getPassword());
        entity.setProperty("monitoredWebsites", getMonitoredWebsites());
        entity.setProperty("monitorWebsiteStart", getMonitorWebsiteStart());
        return entity;
    }

    @SuppressWarnings("unchecked")
	public User fromEntity (Entity entity) {
        setName((String) entity.getProperty("name"));
        setEmail((String) entity.getProperty("email"));
        setId((long) entity.getProperty("id"));
        setPhone((String) entity.getProperty("phone"));
        setPassword((String) entity.getProperty("password"));
        setMonitoredWebsites((ArrayList<Long>) entity.getProperty("monitoredWebsites"));
        setMonitorWebsiteStart((ArrayList<Integer>) entity.getProperty("monitorWebsiteStart"));
        return this;
    }
} 
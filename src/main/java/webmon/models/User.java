package webmon.models;

import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Entity;

@XmlRootElement
public class User {
	private String name;
	private String email;
	private String phone;
	private String password;
	private long id;
	private Set<Integer> monitoredWebsites;
	
	// Constructors
	public User () {
		
	}
	
	public User (String name, String email, String phone, String password){
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.id = WebMonInfo.getNewUserId();
		this.monitoredWebsites = new HashSet<Integer>();
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

	public void setId (int id){
		this.id = id;
	}
	
	public Set<Integer> getMonitoredWebsites (){
		return monitoredWebsites;
	}
	
	public void setMonitoredWebsites (Set<Integer> monitoredWebsites){
		this.monitoredWebsites = monitoredWebsites;
	}
	
	public Entity toEntity () {
        Entity entity = new Entity("User", getId());
        entity.setProperty("name", getName());
        entity.setProperty("email", getEmail());
        entity.setProperty("phone", getPhone());
        entity.setProperty("password", getPassword());
        entity.setProperty("monitoredWebsites", getMonitoredWebsites());
        return entity;
    }

    @SuppressWarnings("unchecked")
	public User fromEntity (Entity entity) {
        setId(Integer.valueOf(entity.getKey().getName()));
        setName((String) entity.getProperty("name"));
        setEmail((String) entity.getProperty("email"));
        setPhone((String) entity.getProperty("phone"));
        setPassword((String) entity.getProperty("password"));
        setMonitoredWebsites((Set<Integer>) entity.getProperty("monitoredWebsites"));
        return this;
    }
} 
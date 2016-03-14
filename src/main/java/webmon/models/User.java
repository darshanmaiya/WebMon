package webmon.models;

import java.io.Serializable;
import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Entity;

import webmon.utils.Constants;

/**
 * The Class User.
 */
@XmlRootElement
public class User implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2290949061273594465L;
	
	/** The name. */
	private String name;
	
	/** The email. */
	private String email;
	
	/** The phone. */
	private String phone;
	
	/** The password. */
	private String password;
	
	/** The id. */
	private long id;
	
	/** The user monitored websites. */
	private List<Long> monitoredWebsites;
	
	/** The list id at the monitoring of a website started. */
	private List<Integer> monitorWebsiteStart;
	
	/**
	 * Instantiates a new user.
	 */
	// Constructors
	public User () {
		
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param name Name
	 * @param email Email
	 * @param phone Phone
	 * @param password Password
	 */
	public User (String name, String email, String phone, String password){
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.id = WebMonInfo.getNewUserId();
		this.monitoredWebsites = new ArrayList<Long>();
		this.setMonitorWebsiteStart(new ArrayList<Integer>());
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	// Getters and setters
	public String getName () {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail () {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail (String email) {
		this.email = email;
	}
	
	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone () {
		return phone;
	}
	
	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone (String phone) {
		this.phone = phone;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword (){
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId (){
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId (long id){
		this.id = id;
	}
	
	/**
	 * Gets the monitored websites.
	 *
	 * @return the monitored websites
	 */
	public List<Long> getMonitoredWebsites (){
		return monitoredWebsites;
	}
	
	/**
	 * Sets the monitored websites.
	 *
	 * @param monitoredWebsites the new monitored websites
	 */
	public void setMonitoredWebsites (ArrayList<Long> monitoredWebsites){
		this.monitoredWebsites = monitoredWebsites;
	}

	/**
	 * Gets the monitor website start.
	 *
	 * @return the monitor website start
	 */
	public List<Integer> getMonitorWebsiteStart() {
		return monitorWebsiteStart;
	}

	/**
	 * Sets the monitor website start.
	 *
	 * @param monitorWebsiteStart the new monitor website start
	 */
	public void setMonitorWebsiteStart(ArrayList<Integer> monitorWebsiteStart) {
		this.monitorWebsiteStart = monitorWebsiteStart;
	}
	
	/**
	 * Convert the user class to an App Engine entity.
	 *
	 * @return the entity
	 */
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

    /**
     * Load properties from the given entity class to the current user class.
     *
     * @param entity the entity
     * @return the user
     */
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
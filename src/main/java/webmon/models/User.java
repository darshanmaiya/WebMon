package webmon.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and JSON
public class User {
  private String name;
  private String email;
  private String phone;
  private String password;
  private int id;
  
  // Constructors
  public User(){
	  
  }
  
  public User (String name, String email, String phone, String  password, int id){
    this.name = name;
    this.email = email;
	this.phone = phone;
	this.password = password;
	this.id = id;
  }
  
  // Getters and setters
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getPassword(){
	  return password;
  }
  
  public void setPassword(String password){
	  this.password = password;
  }
  
  public int getId(){
	  return id;
  }
  
  public void setId(int id){
	  this.id = id;
  }
} 
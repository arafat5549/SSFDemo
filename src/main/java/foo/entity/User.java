package foo.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	
   private String name;
   private String id;
   private String password;
   private String email;
   
   public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
private int uid;
   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public User(){}
	public User(String name, String id, String password,int uid) {
			super();
			this.name = name;
			this.id = id;
			this.password = password;
			this.uid = uid;
		}
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", password=" + password
				+ ", email=" + email + ", uid=" + uid + "]";
	}
	

   
}

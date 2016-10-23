package foo.entity;

import java.io.Serializable;

import foo.test.Bean2Sql.ID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@NoArgsConstructor
@AllArgsConstructor
public @Data class User implements Serializable{
	
   private String name;
   private String id;
   private String password;
   @ID
   private int uid;
   
//public String getName() {
//	return name;
//}
//public void setName(String name) {
//	this.name = name;
//}
//public int getId() {
//	return id;
//}
//public void setId(int id) {
//	this.id = id;
//}
//public String getPassword() {
//	return password;
//}
//public void setPassword(String password) {
//	this.password = password;
//}
//public User(String name, int id, String password) {
//		super();
//		this.name = name;
//		this.id = id;
//		this.password = password;
//	}
//@Override
//	public String toString() {
//		return "User [name=" + name + ", id=" + id + ", password=" + password
//				+ "]";
//	}

   
}

package com.ssf.utils.test;

public class UserException extends Exception{
   /**
	 * 
	 */
private static final long serialVersionUID = 1L;

private String error;
   public UserException(String error){
	   this.error = error;
   }
public String getError() {
	return error;
}
public void setError(String error) {
	this.error = error;
}
   
   
}

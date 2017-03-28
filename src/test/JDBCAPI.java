package test;

import java.sql.Connection;

public interface JDBCAPI {
 
	public Connection openConnection(String url,String username,String pwd);
}

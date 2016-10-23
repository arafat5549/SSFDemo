package foo.test;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.Test;

import foo.dao.UserDao;
import foo.entity.User;
import foo.test.Bean2Sql.SqlType;

public class SqlTest 
{
	@Test
	public void sqlTest(){
		Bean2Sql b2q = new Bean2Sql(SqlType.UPDATE,new User(),null);//new Bean2Sql(User.class);
		System.out.println(b2q.getSqlBuffer());
	}
}

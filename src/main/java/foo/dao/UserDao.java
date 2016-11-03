package foo.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import foo.entity.User;

@Repository
public interface UserDao 
{
	//String ADD_MONEY = "update account set money = money+#{1} where id = #{0}";
	String FETCH_USER ="selct * from users where uid =#{uid}";
	String UPDATE_USER ="UPDATE users SET name=#{name},id=#{id},password=#{password}, WHERE uid=#{uid}";
	
	@Select(FETCH_USER)
	public User fetchPerson(Integer personID );
	@Update(UPDATE_USER)
    public void update(User person );
}

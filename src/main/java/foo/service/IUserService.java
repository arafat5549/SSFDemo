package foo.service;

public interface IUserService 
{
	public void login();
	public void regist();
	public boolean update(Integer personId, String name);
}

package foo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import foo.dao.UserDao;
import foo.entity.User;
import foo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Resource
	private UserDao userDAO;
	
	@Override
	public void login() {
		
	}

	@Override
	public void regist() {
		
	}
	
	@Override
    public boolean update(Integer personId, String name )
    {
        User person = userDAO.fetchPerson( personId );
        if( person != null )
        {
        	User updatedPerson = new User(name ,"","",person.getUid());
        	userDAO.update( updatedPerson );
            return true;
        }
        else
        {
            return false;
        }
    }

}

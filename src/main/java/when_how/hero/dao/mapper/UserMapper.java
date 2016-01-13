package when_how.hero.dao.mapper;

import java.util.List;

import when_how.hero.dao.entity.User;

public interface UserMapper {
	
	public User getUser(long id);
	
	public User getUserByAccount(String account);
	
	public List<User> getUserList();
	
	public void insertUser(User user);
	
}

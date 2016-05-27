package when_how.hero.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import when_how.hero.dao.entity.User;

public interface UserMapper {
	
	public User getUser(@Param("userId") long userId);
	
	public User getUserByAccount(@Param("account") String account);
	
	public List<User> getUserList();
	
	public void insertUser(User user);
	
}

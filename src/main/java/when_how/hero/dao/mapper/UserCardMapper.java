package when_how.hero.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import when_how.hero.dao.entity.UserCard;

public interface UserCardMapper {
	
	public void insertUserCard(UserCard userCard);
	
	public List<UserCard> getUserCardList(@Param("userId") long userId);
	
}

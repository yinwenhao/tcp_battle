package when_how.hero.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import when_how.hero.dao.entity.UserDeck;

public interface UserDeckMapper {

	public List<UserDeck> getUserDeck(@Param("userId") long userId);

	public int updateUserDeck(UserDeck userDeck);

}

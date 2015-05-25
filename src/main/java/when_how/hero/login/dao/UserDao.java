package when_how.hero.login.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import when_how.hero.domain.User;
import when_how.hero.hibernate.dao.BaseDao;

/**
 * @author when_how
 * 
 */
@SuppressWarnings("unchecked")
@Component("userDao")
public class UserDao extends BaseDao<User, Integer> implements IUserDao {

	@Override
	public User getUserByAccount(String account) {
		String hql = "from User where account=?";
		List<User> resultList = (List<User>) getResultByHQLAndParam(hql, account);
		if (resultList.size() != 1) {
			return null;
		}
		return resultList.get(0);
	}

}
package when_how.hero.request.dao;

import when_how.hero.hibernate.dao.IBaseDao;
import when_how.hero.request.domain.User;

/**
 * @author when_how
 * 
 */

public interface IUserDao extends IBaseDao<User, Integer> {

	/**
	 * 通过账号和运营商查询用户
	 * @param account
	 * @return
	 */
	public User getUserByAccount(String account);
	
}

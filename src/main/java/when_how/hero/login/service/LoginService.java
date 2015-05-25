package when_how.hero.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.json.MyResponse;
import when_how.hero.domain.User;
import when_how.hero.login.dao.IUserDao;


/**
 * @author when_how
 * 
 */

@Service("loginService")
public class LoginService implements ILoginService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserDao userDao;

	@Override
	public MyResponse login(String account, String password) {
		if (account == null || account.isEmpty()) {
			return new MyResponse(MyErrorMessage.needLogin);
		}
		User user = userDao.getUserByAccount(account);
		if (user == null || !user.getPassword().equals(password)) {
			return new MyResponse(MyErrorMessage.needLogin);
		}
		return new MyResponse(MyErrorMessage.success);
	}

	@Override
	public String logout(int playerId) {
		return null;
	}

}

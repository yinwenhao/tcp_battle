package when_how.hero.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.json.MyResponse;
import when_how.hero.dao.entity.User;
import when_how.hero.dao.mapper.UserMapper;
import when_how.hero.service.LoginService;


/**
 * @author when_how
 * 
 */

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;

	@Override
	public MyResponse login(String account, String password) {
		if (account == null || account.isEmpty()) {
			return new MyResponse(MyErrorMessage.needLogin);
		}
		User user = userMapper.getUserByAccount(account);
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

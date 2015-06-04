package when_how.hero.request.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.json.MyResponse;
import when_how.hero.request.dao.IUserDao;
import when_how.hero.request.domain.User;
import when_how.hero.request.dto.UserDto;


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
	public MyResponse login(String account, String password, UserDto userDto) {
		if (account == null || account.isEmpty()) {
			return new MyResponse(MyErrorMessage.needLogin);
		}
		User user = userDao.getUserByAccount(account);
		if (user == null || !user.getPassword().equals(password)) {
			return new MyResponse(MyErrorMessage.needLogin);
		}

		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUser_lv(user.getUser_lv());
		userDto.setVip(user.getVip());
		userDto.setLoginTime(System.currentTimeMillis());
		
		return new MyResponse(MyErrorMessage.success);
	}

	@Override
	public String logout(int playerId) {
		return null;
	}

}

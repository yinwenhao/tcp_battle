/**
 * 
 */
package when_how.hero.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import when_how.hero.common.action.BaseAction;
import when_how.hero.common.json.MyResponse;
import when_how.hero.constants.MyErrorMessage;
import when_how.hero.service.LoginService;

/**
 * @author when_how
 * 
 */
@Scope("prototype")
// 配置多例
@Controller("login")
public class LoginAction extends BaseAction {

	@Autowired
	private LoginService loginService;

	private String token;

	/**
	 * 连接战斗服务器，开始对战
	 * 
	 * @return
	 * @throws Exception
	 */
	public void login() throws Exception {
		if (token == null) {
			setResponse(new MyResponse(MyErrorMessage.wrongParam));
			return;
		}
		MyResponse result = loginService.login(token);
		setResponse(result);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

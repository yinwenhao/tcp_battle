/**
 * 
 */
package when_how.hero.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.action.BaseAction;
import when_how.hero.common.json.MyResponse;
import when_how.hero.service.LoginService;

import com.opensymphony.xwork2.Action;

/**
 * @author when_how
 * 
 */
@Scope("prototype") //配置多例
@Controller("login")
public class LoginAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private LoginService loginService;

	private String token;

	/**
	 * 连接战斗服务器，开始对战
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String login() throws Exception {
		if (token == null) {
			setResponse(new MyResponse(MyErrorMessage.wrongParam));
			return Action.SUCCESS;
		}
		MyResponse result = loginService.login(token);
		setResponse(result);
		return Action.SUCCESS;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

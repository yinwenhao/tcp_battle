/**
 * 
 */
package when_how.hero.request.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import when_how.hero.common.MyConstants;
import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.action.BaseAction;
import when_how.hero.common.json.MyResponse;
import when_how.hero.common.one_login.PlayerLoginNanoTime;
import when_how.hero.netty.MySessionManager;
import when_how.hero.request.dto.PlayerDto;
import when_how.hero.request.service.ILoginService;

import com.opensymphony.xwork2.Action;

/**
 * @author when_how
 * 
 */
@Scope("prototype") //配置多例
@Controller("loginAction")
public class LoginAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ILoginService loginService;

	/** 用户账号 */
	private String account;

	/** 用户密码 */
	private String password;

	/**
	 * 用户登陆
	 * 
	 * @return
	 */
	public String login() {
		MyResponse result = loginService.login(account, password);
		setResponse(result);
		PlayerDto playerDto = (PlayerDto) getSession().get(
				MyConstants.SESSION_KEY_PLAYER);
		PlayerLoginNanoTime.putPlayerLoginNanoTime(playerDto);
		return Action.SUCCESS;
	}

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	public String logout() {
		PlayerDto playerDto = (PlayerDto) getSession().get(MyConstants.SESSION_KEY_PLAYER);
		PlayerLoginNanoTime.removePlayerLoginNanoTime(playerDto);
		MySessionManager.removeSession(getSession());
		setResponse(new MyResponse(MyErrorMessage.success));
		return Action.SUCCESS;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

}

/**
 * 
 */
package when_how.hero.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import when_how.hero.common.MyException;
import when_how.hero.common.action.BaseAction;
import when_how.hero.common.json.MyResponse;
import when_how.hero.service.TurnService;

/**
 * @author when_how
 * 
 */
@Scope("prototype")
// 配置多例
@Controller("turn")
public class TurnAction extends BaseAction {

	@Autowired
	private TurnService turnService;

	/**
	 * 结束回合
	 * 
	 * @return
	 */
	public void endTurn() throws MyException {
		MyResponse result = turnService.endTurn(getUid());
		setResponse(result);
	}

}

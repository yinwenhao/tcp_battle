/**
 * 
 */
package when_how.hero.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import when_how.hero.common.action.BaseAction;
import when_how.hero.common.json.MyResponse;
import when_how.hero.service.TurnService;

import com.opensymphony.xwork2.Action;

/**
 * @author when_how
 * 
 */
@Scope("prototype")
// 配置多例
@Controller("turn")
public class TurnAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TurnService turnService;

	private int turn;

	/**
	 * 结束回合
	 * 
	 * @return
	 */
	public String endTurn() {
		MyResponse result = turnService.endTurn(getUid(), turn);
		setResponse(result);
		return Action.SUCCESS;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

}
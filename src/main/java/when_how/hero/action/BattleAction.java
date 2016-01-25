/**
 * 
 */
package when_how.hero.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import when_how.hero.common.action.BaseAction;
import when_how.hero.common.json.MyResponse;
import when_how.hero.service.BattleService;

import com.opensymphony.xwork2.Action;

/**
 * @author when_how
 * 
 */
@Scope("prototype") //配置多例
@Controller("battle")
public class BattleAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private BattleService battleService;

	private int i;
	
	private int location;

	private int target;
	
	private int chooseOne;

	/**
	 * 出牌
	 * 
	 * @return
	 */
	public String useCard() {
		MyResponse result = battleService.useCard(getUid(), i, location, target, chooseOne);
		setResponse(result);
		return Action.SUCCESS;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getChooseOne() {
		return chooseOne;
	}

	public void setChooseOne(int chooseOne) {
		this.chooseOne = chooseOne;
	}

}

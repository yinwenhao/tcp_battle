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
@Scope("prototype")
// 配置多例
@Controller("battle")
public class BattleAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private BattleService battleService;

	private int targetIndex;

	private int i;

	private int target;

	/**
	 * 使用英雄技能
	 * 
	 * @return
	 */
	public String useHeroSkill() {
		MyResponse result = battleService.useHeroSkill(getUid(), targetIndex,
				target);
		setResponse(result);
		return Action.SUCCESS;
	}

	/**
	 * 英雄攻击
	 * 
	 * @return
	 */
	public String heroAttack() {
		MyResponse result = battleService.heroAttack(getUid(), targetIndex,
				target);
		setResponse(result);
		return Action.SUCCESS;
	}

	/**
	 * 随从攻击
	 * 
	 * @return
	 */
	public String servantAttack() {
		MyResponse result = battleService.servantAttack(getUid(), targetIndex,
				i, target);
		setResponse(result);
		return Action.SUCCESS;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}

}

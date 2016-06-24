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
import when_how.hero.service.CardService;

/**
 * @author when_how
 * 
 */
@Scope("prototype")
// 配置多例
@Controller("card")
public class CardAction extends BaseAction {

	@Autowired
	private CardService cardService;

	private int targetIndex;

	private int i;

	private int location;

	private int target;

	private int chooseOne;

	private String changeIndexString;

	/**
	 * 出牌
	 * 
	 * @return
	 */
	public void useCard() throws MyException {
		MyResponse result = cardService.useCard(getUid(), targetIndex, i, location, target, chooseOne);
		setResponse(result);
	}

	/**
	 * 换牌
	 * 
	 * @return
	 */
	public void changeCardsInHand() throws MyException {
		String[] ss = changeIndexString.trim().split(",");
		int[] changeIndex = new int[ss.length];
		for (int i = 0; i < ss.length; i++) {
			changeIndex[i] = Integer.valueOf(ss[i]);
		}
		MyResponse result = cardService.changeCardsInHand(getUid(), changeIndex);
		setResponse(result);
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

	public int getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}

	public String getChangeIndexString() {
		return changeIndexString;
	}

	public void setChangeIndexString(String changeIndexString) {
		this.changeIndexString = changeIndexString;
	}

}

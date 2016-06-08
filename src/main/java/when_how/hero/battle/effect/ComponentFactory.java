package when_how.hero.battle.effect;

import java.util.List;

import when_how.hero.battle.data.Battle;
import when_how.hero.sdata.domain.SEffect;

public interface ComponentFactory {

	/**
	 * 
	 * @param effects
	 * @param battle
	 * @param location
	 *            随从放置位置
	 * @param target
	 *            目标序号，-1表示英雄
	 * @return
	 */
	public MyComponent getBattlecryComposite(List<SEffect> effects,
			Battle battle, int location, int target);

}

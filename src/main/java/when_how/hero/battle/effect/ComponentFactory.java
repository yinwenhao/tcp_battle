package when_how.hero.battle.effect;

import java.util.List;

import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;
import when_how.hero.sdata.domain.SEffect;

public interface ComponentFactory {

	/**
	 * 战吼配件
	 * 
	 * @param effects
	 * @param battle
	 * @param location
	 *            随从放置位置
	 * @param target
	 *            目标序号，-1表示英雄
	 * @param targetPlayer
	 * @return
	 */
	public MyComponent getBattlecryComposite(List<SEffect> effects, Battle battle, int location, int target,
			Player targetPlayer);

	/**
	 * 法术配件
	 * 
	 * @param effects
	 * @param battle
	 * @param target
	 * @param targetPlayer
	 * @return
	 */
	public MyComponent getSpellComposite(List<SEffect> effects, Battle battle, int target, Player targetPlayer);

}

package when_how.hero.battle.effect;

import java.util.List;

import org.springframework.stereotype.Component;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.effect.impl.SummonComponent;
import when_how.hero.sdata.domain.SEffect;

@Component("componentFactory")
public class ComponentFactoryImpl implements ComponentFactory {

	@Override
	public MyComponent getBattlecryComposite(List<SEffect> effects,
			Battle battle, int location, int target, int targetPlayerIndex) {
		Composite result = new BattlecryCompositeImpl();
		for (SEffect se : effects) {
			MyComponent c = createComponent(se, battle, location, target);
			result.add(c);
		}
		return result;
	}

	private MyComponent createComponent(SEffect se, Battle battle,
			int location, int target) {
		switch (se.getType()) {
		case TypeConstants.SUMMON:
			// 召唤，[玩家，召唤物的cardId，召唤数量]
			return new SummonComponent(
					se.getParam()[0] == 0 ? battle.getTurnPlayer()
							: battle.getNextTurnPlayer(), se.getParam()[2],
					se.getParam()[1],
					location == BattleConstants.TARGET_NONE ? battle
							.getTurnPlayer().getServantNum() : location + 1);
		}
		return null;
	}

	@Override
	public MyComponent getSpellComposite(List<SEffect> effects, Battle battle, int target, int targetPlayerIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}

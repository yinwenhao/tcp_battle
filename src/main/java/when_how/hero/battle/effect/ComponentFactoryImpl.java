package when_how.hero.battle.effect;

import java.util.List;

import org.springframework.stereotype.Component;

import when_how.hero.battle.data.Battle;
import when_how.hero.battle.effect.impl.SummonComponent;
import when_how.hero.sdata.domain.SEffect;

@Component("componentFactory")
public class ComponentFactoryImpl implements ComponentFactory {

	@Override
	public MyComponent getBattlecryComposite(List<SEffect> effects,
			Battle battle, int location, int target) {
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
			return new SummonComponent(battle.getTurnPlayer(), se.getParam(),
					location);
		}
		return null;
	}

}
package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.checker.MyChecker;
import when_how.hero.common.MyException;
import when_how.hero.sdata.domain.SEffect;

public class AttributeComponent implements MyComponent {

	private Player targetPlayer;

	private int attAttribute;

	private int hpAttribute;

	private int target;

	private SEffect se;

	public AttributeComponent(SEffect se, Player targetPlayer, int target, int attAttribute, int hpAttribute) {
		this.targetPlayer = targetPlayer;
		this.hpAttribute = hpAttribute;
		this.attAttribute = attAttribute;
		this.target = target;
		this.se = se;
	}

	@Override
	public void display() throws MyException {
		Servant targetServant = targetPlayer.getServants().get(target);
		targetServant.setAtt(targetServant.getAtt() + attAttribute);
		targetServant.addHpMax(hpAttribute);
		targetServant.getEffect().add(se);
	}

	@Override
	public void checkParam() throws MyException {
		MyChecker.checkTargetPositive(target);
	}

}

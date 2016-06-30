package when_how.hero.battle.effect;

import java.util.List;

import org.springframework.stereotype.Component;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.effect.impl.AttributeComponent;
import when_how.hero.battle.effect.impl.BackToHandComponent;
import when_how.hero.battle.effect.impl.CardComponent;
import when_how.hero.battle.effect.impl.ChargeComponent;
import when_how.hero.battle.effect.impl.CollectComponent;
import when_how.hero.battle.effect.impl.DamageAoeComponent;
import when_how.hero.battle.effect.impl.DamageComponent;
import when_how.hero.battle.effect.impl.DamageRangeComponent;
import when_how.hero.battle.effect.impl.DiscoverComponent;
import when_how.hero.battle.effect.impl.DivineShieldComponent;
import when_how.hero.battle.effect.impl.HealComponent;
import when_how.hero.battle.effect.impl.SilenceComponent;
import when_how.hero.battle.effect.impl.StealthComponent;
import when_how.hero.battle.effect.impl.SummonComponent;
import when_how.hero.battle.effect.impl.TauntComponent;
import when_how.hero.battle.effect.impl.WindfuryComponent;
import when_how.hero.sdata.cache.SEffectCache;
import when_how.hero.sdata.domain.SEffect;

@Component("componentFactory")
public class ComponentFactoryImpl implements ComponentFactory {

	@Override
	public MyComponent getBattlecryComposite(List<Integer> effects, Battle battle, int location, int target,
			Player targetPlayer) {
		Composite result = new BattlecryCompositeImpl();
		for (int s : effects) {
			SEffect se = SEffectCache.CACHE.get(s);
			MyComponent c = createComponent(se, battle, targetPlayer, location, target);
			result.add(c);
		}
		return result;
	}

	private MyComponent createComponent(SEffect se, Battle battle, Player targetPlayer, int location, int target) {
		switch (se.getType()) {
		case TypeConstants.SUMMON:
			// 自己召唤, [召唤物的cardId，召唤数量]
			return new SummonComponent(battle.getTurnPlayer(), se.getParam()[1], se.getParam()[0],
					location == BattleConstants.LOCATION_DEFAULT ? location : location + 1);
		case TypeConstants.ATTRIBUTE:
			// 随从增益, [攻击增益，hp增益]
			return new AttributeComponent(se, targetPlayer, target, se.getParam()[0], se.getParam()[1]);
		case TypeConstants.BACK:
			// 回手, [费用变化]
			return new BackToHandComponent(targetPlayer, target, se.getParam()[0]);
		case TypeConstants.CARD:
			// 自己抽牌, [抽牌数量]
			return new CardComponent(battle.getTurnPlayer(), se.getParam()[0]);
		case TypeConstants.CARD_ENEMY:
			// 对手抽牌, [抽牌数量]
			return new CardComponent(battle.getNextTurnPlayer(), se.getParam()[0]);
		case TypeConstants.COLLECT:
			// 收藏, [可选卡牌id1，可选卡牌id2，可选卡牌id3]
			return new CollectComponent(battle.getTurnPlayer(), se.getParam());
		case TypeConstants.DAMAGE:
			// 伤害, [伤害值]
			return new DamageComponent(targetPlayer, se.getParam()[0], target);
		case TypeConstants.DAMAGE_AOE:
			// 全体伤害, [伤害值]
			return new DamageAoeComponent(targetPlayer, se.getParam()[0]);
		case TypeConstants.DAMAGE_RANGE:
			// 范围伤害, [伤害值, 附近伤害值]
			return new DamageRangeComponent(targetPlayer, se.getParam()[0], se.getParam()[1], target);
		case TypeConstants.DISCOVER:
			// 发现, [可选卡牌id1，可选卡牌id2，可选卡牌id3]
			return new DiscoverComponent(battle.getTurnPlayer(), se.getParam());
		case TypeConstants.HEAL:
			// 治疗, [治疗量]
			return new HealComponent(targetPlayer, target, se.getParam()[0]);
		case TypeConstants.SILENCE:
			// 沉默
			return new SilenceComponent(targetPlayer, target);
		case TypeConstants.SUMMON_ENEMY:
			// 对手召唤, [召唤物的cardId，召唤数量]
			return new SummonComponent(battle.getNextTurnPlayer(), se.getParam()[1], se.getParam()[0],
					location == BattleConstants.LOCATION_DEFAULT ? location : location + 1);
		case TypeConstants.WINDFURY:
			// 风怒, [额外攻击次数]
			return new WindfuryComponent(se, targetPlayer, target, se.getParam()[0]);
		case TypeConstants.DIVINE_SHIELD:
			// 圣盾
			return new DivineShieldComponent(se, targetPlayer, target);
		case TypeConstants.CHARGE:
			// 冲锋
			return new ChargeComponent(se, targetPlayer, target);
		case TypeConstants.STEALTH:
			// 潜行
			return new StealthComponent(se, targetPlayer, target);
		case TypeConstants.TAUNT:
			// 嘲讽
			return new TauntComponent(se, targetPlayer, target);
		case TypeConstants.DAMAGE_ALL:
			// 全体伤害
			
		}
		return null;
	}

	@Override
	public MyComponent getSpellComposite(List<Integer> effects, Battle battle, int target, Player targetPlayer) {
		Composite result = new SpellCompositeImpl();
		for (int s : effects) {
			SEffect se = SEffectCache.CACHE.get(s);
			MyComponent c = createComponent(se, battle, targetPlayer, BattleConstants.LOCATION_DEFAULT, target);
			result.add(c);
		}
		return result;
	}

	@Override
	public MyComponent getEffectComposite(List<Integer> effects, Battle battle, int location, int target,
			Player targetPlayer) {
		Composite result = new EffectCompositeImpl();
		for (int s : effects) {
			SEffect se = SEffectCache.CACHE.get(s);
			MyComponent c = createComponent(se, battle, targetPlayer, location, target);
			result.add(c);
		}
		return result;
	}

}

package when_how.hero.battle.init;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Card;
import when_how.hero.battle.data.Hero;
import when_how.hero.battle.data.HeroSkill;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.dto.BattleInitData;
import when_how.hero.sdata.cache.SHeroCache;
import when_how.hero.sdata.cache.SHeroSkillCache;
import when_how.hero.sdata.domain.SHero;
import when_how.hero.sdata.domain.SHeroSkill;

@Component("battleInit")
public class BattleInitImpl implements BattleInit {

	@Override
	public Battle init(BattleInitData battleInitData) throws Exception {
		long[] uid = battleInitData.getUid();
		int[] heroId = battleInitData.getHeroId();
		int[][] cards = battleInitData.getCards();
		
		long uid1 = uid[0];
		long uid2 = uid[1];

		if (Manager.inBattle(uid1, uid2)) {
			// 已经初始化过了
			return Manager.getBattle(uid1);
		}
		
		SHero sHero1 = SHeroCache.CACHE.get(heroId[0]);
		SHeroSkill sHeroSkill1 = SHeroSkillCache.CACHE.get(sHero1.getSkillId());
		HeroSkill heroSkill1 = initHeroSkill(sHeroSkill1);
		Hero hero1 =initHero(sHero1, heroSkill1);
		Player player1 = initPlayer(uid1, hero1, cards[0]);
		
		SHero sHero2 = SHeroCache.CACHE.get(heroId[1]);
		SHeroSkill sHeroSkill2 = SHeroSkillCache.CACHE.get(sHero2.getSkillId());
		HeroSkill heroSkill2 = initHeroSkill(sHeroSkill2);
		Hero hero2 =initHero(sHero2, heroSkill2);
		Player player2 = initPlayer(uid2, hero2, cards[1]);
		
		Battle battle = initBattle(player1, player2);
		Manager.putBattle(uid1, battle);
		Manager.putBattle(uid2, battle);

		// 战斗开始
		battle.start();
		
		return battle;
	}
	
	private Player initPlayer(long uid, Hero hero, int[] cards) {
		Player result = new Player();
		result.setHero(hero);
		result.setUserId(uid);
		result.setEnergy(BattleConstants.ENERGY_INIT);
		result.setCardIds(cards);
		result.initCards();
		List<Servant> servants = new LinkedList<Servant>();
		result.setServants(servants);
		result.setHand(new ArrayList<Card>(BattleConstants.HAND_LIMIT));
		return result;
	}
	
	private Battle initBattle(Player player1, Player player2) {
		Battle result = new Battle();
		result.setPlayers(new Player[]{player1, player2});
		result.setTurn(0);
		return result;
	}
	
	private HeroSkill initHeroSkill(SHeroSkill sheroSkill) {
		HeroSkill result = new HeroSkill();
		result.setSid(sheroSkill.getSid());
		result.setCanUse(true);
		result.setCost(sheroSkill.getCost());
		return result;
	}
	
	private Hero initHero(SHero sHero, HeroSkill heroSkill) {
		Hero result = new Hero();
		result.setSid(sHero.getSid());
		result.setSkill(heroSkill);
		result.setHp(BattleConstants.HERO_HP);
		result.setHpMax(BattleConstants.HERO_HP);
		return result;
	}

}

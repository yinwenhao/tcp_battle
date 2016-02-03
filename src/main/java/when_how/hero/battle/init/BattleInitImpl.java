package when_how.hero.battle.init;

import org.springframework.stereotype.Component;

import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Hero;
import when_how.hero.battle.data.Player;
import when_how.hero.dto.BattleInitData;

@Component("battleInit")
public class BattleInitImpl implements BattleInit {

	private static final int lockNum = 1000;
	private static Object[] locks = new Object[lockNum];
	static {
		for (int i = 0; i < locks.length; i++) {
			locks[i] = new Object();
		}
	}

	@Override
	public Battle init(BattleInitData battleInitData) throws Exception {
		long[] uids = battleInitData.getUid();

		Long lockIndex = 0L;
		for (long uid : uids) {
			lockIndex += uid;
		}
		synchronized (locks[lockIndex.hashCode() % lockNum]) {
			// 保证一个battle只初始化一次
			if (Manager.inBattle(uids)) {
				// 已经初始化过了
				return Manager.getBattle(uids[0]);
			}

			Player[] players = new Player[uids.length];
			for (int i = 0; i < uids.length; i++) {
				Hero hero = new Hero(battleInitData.getHeroId()[i]);
				Player player = new Player(uids[i], hero,
						battleInitData.getCards()[0]);
				players[i] = player;
			}

			Battle battle = new Battle(players);

			Manager.putBattle(uids, battle);

			// 战斗初始化
			battle.init();

			return battle;
		}
	}

}

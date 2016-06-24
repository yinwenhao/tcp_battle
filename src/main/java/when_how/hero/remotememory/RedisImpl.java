package when_how.hero.remotememory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;
import when_how.hero.constants.RedisKey;

@Component("redisRemoteMemory")
public class RedisImpl implements RemoteMemory {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public void delete(String keyHead, Object key) {
		redisTemplate.delete(keyHead + key);
	}

	@Override
	public void putBattleResult(Battle battle) {
		for (Player p : battle.getPlayers()) {
			redisTemplate.opsForValue().set(RedisKey.battleResult + p.getUserId(), String.valueOf(p.getWinOrLose()));
			Manager.removeBattle(p.getUserId());
		}
	}

	@Override
	public String getString(String keyHead, Object key) {
		String wholeKey = keyHead + key;
		String result = null;
		try {
			result = redisTemplate.opsForValue().get(wholeKey);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("key:" + wholeKey + " value:" + result);
			}
		}
		return result;
	}

}

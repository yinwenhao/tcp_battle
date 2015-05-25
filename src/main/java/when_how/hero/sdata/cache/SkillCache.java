package when_how.hero.sdata.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.Skill;


public class SkillCache extends SdataCache<Integer, Skill> {

	private static SkillCache self = null;

	private static Lock lock = new ReentrantLock();

	private static List<Integer> idList = new ArrayList<Integer>();

	private SkillCache() {
		List<Object[]> aList = SDataLoader.getModels(Skill.class);
		for (Object[] a : aList) {
			idList.add((Integer) a[0]);
			put((Integer) a[0], (Skill) a[1]);
		}
	}

	/**
	 * 获得一个随机技能
	 * 
	 * @return
	 */
	public int getRandomSkill() {
		return idList.get(new Random().nextInt(idList.size()));
	}

	/**
	 * 获得单例
	 * 
	 * @return
	 */
	public static SkillCache getInstance() {
		if (self == null) {
			lock.lock();
			try {
				if (self == null) {
					self = new SkillCache();
				}
			} finally {
				lock.unlock();
			}
		}
		return self;
	}
}

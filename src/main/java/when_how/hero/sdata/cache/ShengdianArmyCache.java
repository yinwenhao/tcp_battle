package when_how.hero.sdata.cache;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.ShengdianArmy;


public class ShengdianArmyCache extends SdataCache<Integer, ShengdianArmy> {

	private static ShengdianArmyCache self = null;

	private static Lock lock = new ReentrantLock();

	/** 圣殿敌军的数量 */
	public int num = 0;

	private ShengdianArmyCache() {
		List<Object[]> aList = SDataLoader.getModels(ShengdianArmy.class);
		for (Object[] a : aList) {
			put((Integer) a[0], (ShengdianArmy) a[1]);
		}
		num = aList.size();
	}

	/**
	 * 获得单例
	 * 
	 * @return
	 */
	public static ShengdianArmyCache getInstance() {
		if (self == null) {
			lock.lock();
			try {
				if (self == null) {
					self = new ShengdianArmyCache();
				}
			} finally {
				lock.unlock();
			}
		}
		return self;
	}
}

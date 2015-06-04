package when_how.hero.sdata.cache;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.ArmyHero;


public class ArmyHeroCache extends SdataCache<Integer, ArmyHero>{
	
	private static ArmyHeroCache self = null;
	
	private static Lock lock = new ReentrantLock();
	
	private ArmyHeroCache() {
		List<Object[]> aList = SDataLoader.getModels(ArmyHero.class);
		for (Object[] a : aList) {
			put((Integer)a[0], (ArmyHero)a[1]);
		}
	}
	
	/**
	 * 获得单例
	 * @return
	 */
	public static ArmyHeroCache getInstance() {
		if (self == null) {
			lock.lock();
			try {
				if (self == null) {
					self = new ArmyHeroCache();
				}
			} finally {
				lock.unlock();
			}
		}
		return self;
	}
}

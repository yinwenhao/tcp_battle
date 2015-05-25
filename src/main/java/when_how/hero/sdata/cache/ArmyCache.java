package when_how.hero.sdata.cache;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.Army;


public class ArmyCache extends SdataCache<Integer, Army>{
	
	private static ArmyCache self = null;
	
	private static Lock lock = new ReentrantLock();
	
	private ArmyCache() {
		List<Object[]> aList = SDataLoader.getModels(Army.class);
		for (Object[] a : aList) {
			put((Integer)a[0], (Army)a[1]);
		}
	}
	
	/**
	 * 获得单例
	 * @return
	 */
	public static ArmyCache getInstance() {
		if (self == null) {
			lock.lock();
			try {
				if (self == null) {
					self = new ArmyCache();
				}
			} finally {
				lock.unlock();
			}
		}
		return self;
	}
}

package when_how.hero.sdata.cache;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.ArmyGeneral;


public class ArmyGeneralCache extends SdataCache<Integer, ArmyGeneral>{
	
	private static ArmyGeneralCache self = null;
	
	private static Lock lock = new ReentrantLock();
	
	private ArmyGeneralCache() {
		List<Object[]> aList = SDataLoader.getModels(ArmyGeneral.class);
		for (Object[] a : aList) {
			put((Integer)a[0], (ArmyGeneral)a[1]);
		}
	}
	
	/**
	 * 获得单例
	 * @return
	 */
	public static ArmyGeneralCache getInstance() {
		if (self == null) {
			lock.lock();
			try {
				if (self == null) {
					self = new ArmyGeneralCache();
				}
			} finally {
				lock.unlock();
			}
		}
		return self;
	}
}

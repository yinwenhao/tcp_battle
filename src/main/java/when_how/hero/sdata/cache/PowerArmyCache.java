package when_how.hero.sdata.cache;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.PowerArmy;


public class PowerArmyCache extends SdataCache<Integer, PowerArmy> {

	private static PowerArmyCache self = null;

	private static Lock lock = new ReentrantLock();

	private PowerArmyCache() {
		List<Object[]> aList = SDataLoader.getModels(PowerArmy.class);
		for (Object[] a : aList) {
			put((Integer) a[0], (PowerArmy) a[1]);
		}
	}

	/**
	 * 根据armyId获得power
	 * 
	 * @param armyId
	 * @return
	 */
	public PowerArmy getPowerByArmy(int armyId) {
		for (PowerArmy pa : getModels()) {
			String[] s = pa.getArmies().split("-");
			if (armyId >= Integer.valueOf(s[0])
					&& armyId <= Integer.valueOf(s[1])) {
				return pa;
			}
		}
		return null;
	}

	/**
	 * 获得单例
	 * 
	 * @return
	 */
	public static PowerArmyCache getInstance() {
		if (self == null) {
			lock.lock();
			try {
				if (self == null) {
					self = new PowerArmyCache();
				}
			} finally {
				lock.unlock();
			}
		}
		return self;
	}
}

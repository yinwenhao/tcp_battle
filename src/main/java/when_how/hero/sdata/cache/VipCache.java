package when_how.hero.sdata.cache;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.Vip;


public class VipCache extends SdataCache<Integer, Vip> {

	private static VipCache self = null;

	private static Lock lock = new ReentrantLock();

	private VipCache() {
		List<Object[]> aList = SDataLoader.getModels(Vip.class);
		for (Object[] a : aList) {
			put((Integer) a[0], (Vip) a[1]);
		}
	}

	/**
	 * 通过累积充值金币获得VIP等级
	 * 
	 * @param gold
	 * @return
	 */
	public int getVipLevelByGold(int gold) {
		int result = 0;
		for (Vip v : getModels()) {
			if (gold >= v.getGold() && result < v.getId()) {
				result = v.getId();
			}
		}
		return result;
	}

	/**
	 * 通过现在的VIP等级获得下一级VIP等级
	 * 
	 * @param vip
	 * @return
	 */
	public int getNextVip(int vip) {
		if (get(vip + 1) != null) {
			return vip + 1;
		} else {
			return vip;
		}
	}

	/**
	 * 获得单例
	 * 
	 * @return
	 */
	public static VipCache getInstance() {
		if (self == null) {
			lock.lock();
			try {
				if (self == null) {
					self = new VipCache();
				}
			} finally {
				lock.unlock();
			}
		}
		return self;
	}
}

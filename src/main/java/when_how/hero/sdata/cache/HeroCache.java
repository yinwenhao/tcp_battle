package when_how.hero.sdata.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.Hero;


public class HeroCache extends SdataCache<Integer, Hero> {

	private static HeroCache self = null;

	private static Lock lock = new ReentrantLock();

	private static final int randomSize = 12;

	private static Map<Integer, List<Hero>> heroMap = new HashMap<Integer, List<Hero>>();

	private static int pinzhiMax = 0;

	private HeroCache() {
		List<Object[]> aList = SDataLoader.getModels(Hero.class);
		for (Object[] a : aList) {
			Hero h = (Hero) a[1];
			put((Integer) a[0], h);
			if (!heroMap.containsKey(h.getQuality())) {
				List<Hero> hl = new ArrayList<Hero>();
				heroMap.put(h.getQuality(), hl);
			}
			heroMap.get(h.getQuality()).add(h);
			if (h.getQuality() > pinzhiMax) {
				pinzhiMax = h.getQuality();
			}
		}
	}

	/**
	 * 获得最大品质
	 * 
	 * @return
	 */
	public int getPinzhiMax() {
		return pinzhiMax;
	}

	/**
	 * 为充值送英雄活动随机一个黄色英雄
	 * 
	 * @return
	 */
	public Hero getRandomHeroForPayHeroEvent() {
		return get(new Random().nextInt(6) + 4);
	}

	/**
	 * 随机获得一个指定品质的英雄
	 * 
	 * @return
	 */
	public Hero getRandomHeroByPinzhi(int pinzhi) {
		List<Hero> hl = heroMap.get(pinzhi);
		return hl.get(new Random().nextInt(hl.size()));
	}

	/**
	 * 随机获得一个英雄
	 * 
	 * @return
	 */
	public Hero getRandomHero() {
		return get(new Random().nextInt(randomSize) + 1);
	}

	/**
	 * 获得单例
	 * 
	 * @return
	 */
	public static HeroCache getInstance() {
		if (self == null) {
			lock.lock();
			try {
				if (self == null) {
					self = new HeroCache();
				}
			} finally {
				lock.unlock();
			}
		}
		return self;
	}
}

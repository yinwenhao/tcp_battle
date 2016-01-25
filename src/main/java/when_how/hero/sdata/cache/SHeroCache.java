package when_how.hero.sdata.cache;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.SHero;

@Component("sHeroCache")
public class SHeroCache extends SdataCache<Integer, SHero> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public static SHeroCache CACHE;

	private SHeroCache() throws IOException {
		List<SHero> aList = SDataLoader.getModelsFromJson(SHero.class);
		for (SHero a : aList) {
			put(a.getSid(), a);
		}
		CACHE = this;
		log.info("init finish");
	}

}

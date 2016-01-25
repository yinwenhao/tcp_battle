package when_how.hero.sdata.cache;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.SEffect;

@Component("sEffectCache")
public class SEffectCache extends SdataCache<Integer, SEffect> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public static SEffectCache CACHE;

	private SEffectCache() throws IOException {
		List<SEffect> aList = SDataLoader.getModelsFromJson(SEffect.class);
		for (SEffect a : aList) {
			put(a.getSid(), a);
		}
		CACHE = this;
		log.info("init finish");
	}

}

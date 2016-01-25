package when_how.hero.sdata.cache;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.SHeroSkill;

@Component("sHeroSkillCache")
public class SHeroSkillCache extends SdataCache<Integer, SHeroSkill> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public static SHeroSkillCache CACHE;

	private SHeroSkillCache() throws IOException {
		List<SHeroSkill> aList = SDataLoader
				.getModelsFromJson(SHeroSkill.class);
		for (SHeroSkill a : aList) {
			put(a.getSid(), a);
		}
		CACHE = this;
		log.info("init finish");
	}

}

package when_how.hero.sdata.cache;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.SCard;

@Component("sCardCache")
public class SCardCache extends SdataCache<Integer, SCard> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public static SCardCache CACHE;

	private SCardCache() throws IOException {
		List<SCard> aList = SDataLoader.getModelsFromJson(SCard.class);
		for (SCard a : aList) {
			put(a.getSid(), a);
		}
		CACHE = this;
		log.info("init finish");
	}

}

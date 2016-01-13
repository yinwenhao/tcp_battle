package when_how.hero.trigger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("testQuartz")
public class TestQuartz {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public void test() {
		log.debug("testQuartz.test()");
	}
	
}

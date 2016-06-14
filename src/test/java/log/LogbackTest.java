package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
	
	private static Logger log = LoggerFactory.getLogger("ASYNC");

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (int i=0; i<100000; i++) {
			log.info("test for logback.");
			log.debug("test for logback.");
			log.warn("test for logback.");
			log.error("test for logback.");
		}
		System.out.println(System.currentTimeMillis()-start);
	}

}

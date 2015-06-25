package when_how.hero.trigger.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import when_how.hero.netty.MySessionManager;

@Component("jobCheckMySession") // 使用org.springframework.scheduling.quartz.SpringBeanJobFactory可以不加这个注释，而且是多例的
public class JobCheckMySession implements Job {
	/** log */
	private Logger log = LoggerFactory.getLogger(JobCheckMySession.class);

	@Override
	public void execute(JobExecutionContext je) throws JobExecutionException {
		try {
			if (log.isDebugEnabled()) {
				log.debug("execute job: [" + getClass() + "]");
			}
			MySessionManager.checkMySession();
		} catch (Exception e) {
			log.error("", e);
		}
	}

}

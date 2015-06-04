package when_how.hero.trigger;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import when_how.hero.netty.MyTcpConstants;

public class MySpringJobFactory implements JobFactory {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler)
			throws SchedulerException {
		JobDetail jobDetail = bundle.getJobDetail();
		Class<? extends Job> jobClass = jobDetail.getJobClass();
		try {
			if (log.isDebugEnabled()) {
				log.debug("Producing instance of Job '" + jobDetail.getKey()
						+ "', class=" + jobClass.getName());
			}
			return MyTcpConstants.factory.getBean(jobClass);
		} catch (Exception e) {
			SchedulerException se = new SchedulerException(
					"Problem instantiating class '"
							+ jobDetail.getJobClass().getName() + "'", e);
			throw se;
		}
	}

}

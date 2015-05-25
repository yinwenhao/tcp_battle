package when_how.hero.trigger;

import java.text.ParseException;
import java.util.List;

import org.quartz.SchedulerException;


public interface IJobManager {
	public void initJobTrigger(List<MyJobCron> jobList) throws SchedulerException, ParseException;
}

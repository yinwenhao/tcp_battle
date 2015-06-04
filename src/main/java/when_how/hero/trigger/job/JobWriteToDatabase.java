package when_how.hero.trigger.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import when_how.hero.mydbcache.DBCache;
import when_how.hero.request.dao.IUserDao;
import when_how.hero.request.domain.User;

@Component("jobWriteToDatabase")
public class JobWriteToDatabase implements Job {
	/** log */
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserDao userDao;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void execute(JobExecutionContext je) throws JobExecutionException {
		try {
//			log.info("execute job: [" + getClass() + "]");
			for (User o : DBCache.cacheUser.values()) {
				userDao.saveOrUpdate(o);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}

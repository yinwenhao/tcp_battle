package when_how.hero.netty.dispatcher;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("requestExecutor")
public class RequestExecutor implements InitializingBean, DisposableBean {

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 等待线程池关闭的时间（秒）
	 */
	private static final long AWAIT_SECONDS = 15;

	// 创建一个可重用固定线程数的线程池
	private ThreadPoolExecutor pool;

	// 线程池大小，spring赋值是反射的，不会调用set方法
	@Value(value = "${requestExecutor.poolSize}")
	private int poolSize;

	public void execute(Runnable task) {
		pool.execute(task);
	}

	@Override
	public void destroy() throws Exception {
		pool.shutdown();
		if (!pool.awaitTermination(AWAIT_SECONDS, TimeUnit.SECONDS)) {
			log.warn("pool: close threads... (" + new Date()
					+ " ) take too long time: " + AWAIT_SECONDS + "s.");
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

}

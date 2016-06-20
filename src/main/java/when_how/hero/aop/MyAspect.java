package when_how.hero.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import when_how.hero.common.MyException;
import when_how.hero.common.json.MyResponse;
import when_how.hero.constants.MyErrorNo;

@Aspect
@Component("myAspect")
public class MyAspect {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* when_how.hero..*.*(..))")
	private void allMethods() {
	}

	@Pointcut("execution(* when_how.hero.service..*.*(..))")
	private void services() {
	}

	// @Pointcut("innerServices() || outerServices() || processors()")
	// private void logCost() {
	//
	// }

	@Around("services()")
	public Object doSetResultWithException(ProceedingJoinPoint jp) throws Throwable {
		Object result;
		try {
			result = jp.proceed();
		} catch (MyException me) {
			result = new MyResponse(me.getErrNo());
		} catch (Exception e) {
			log.error("catch service exception", e);
			result = new MyResponse(MyErrorNo.commonError);
		}
		return result;
	}

}

package when_how.hero.service.handler;

import when_how.hero.common.json.MyResponse;

/**
 * 责任链模式
 * 
 * @author yinwenhao
 *
 */
public abstract class Handler {

	private Handler nextHandler;

	public Handler getNextHandler() {
		if (nextHandler == null) {
			return new SuccessHandler();
		}
		return nextHandler;
	}

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public abstract MyResponse handle();

}
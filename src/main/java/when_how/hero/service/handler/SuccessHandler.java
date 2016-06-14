package when_how.hero.service.handler;

import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.json.MyResponse;

public class SuccessHandler extends Handler {

	@Override
	public MyResponse handle() {
		return new MyResponse(MyErrorMessage.success);
	}

}

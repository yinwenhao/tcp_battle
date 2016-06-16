package when_how.hero.service.handler;

import when_how.hero.common.json.MyResponse;
import when_how.hero.constants.MyErrorNo;

public class SuccessHandler extends Handler {

	@Override
	public MyResponse handle() {
		return new MyResponse(MyErrorNo.success);
	}

}

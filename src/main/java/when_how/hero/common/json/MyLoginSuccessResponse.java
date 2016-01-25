package when_how.hero.common.json;

import com.fasterxml.jackson.annotation.JsonIgnore;

import when_how.hero.common.MyErrorMessage;

public class MyLoginSuccessResponse extends MyResponse {
	
	@JsonIgnore
	private long uid;
	
	public MyLoginSuccessResponse() {
		super(MyErrorMessage.success);
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

}

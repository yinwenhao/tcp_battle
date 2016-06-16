package when_how.hero.common.json;

import com.fasterxml.jackson.annotation.JsonIgnore;

import when_how.hero.battle.data.Battle;
import when_how.hero.constants.MyErrorNo;

public class MyLoginSuccessResponse extends MyResponse {

	@JsonIgnore
	private long uid;

	public MyLoginSuccessResponse(long uid) {
		super(MyErrorNo.success);
		this.uid = uid;
	}

	/**
	 * 战场信息的返回结果
	 * 
	 * @param battle
	 * @param uid
	 */
	public MyLoginSuccessResponse(long uid, Battle battle) {
		super(battle, uid);
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

}

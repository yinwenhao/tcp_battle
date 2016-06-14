package when_how.hero.common.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import when_how.hero.battle.data.Battle;
import when_how.hero.constants.MyErrorMessage;
import when_how.hero.dto.own.OwnBattleData;
import when_how.hero.netty.serial.impl.JsonAutoCloseOutput;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;

@JsonInclude(Include.NON_NULL)
public class MyResponse {

	private static final Logger log = LoggerFactory.getLogger(MyResponse.class);
	
	private int state;
	
	private int sss;
	
	private String content;
	
	private Object data;
	
	public MyResponse() {}
	
	/**
	 * 战场信息的返回结果
	 * 
	 * @param battle
	 * @param uid
	 */
	public MyResponse(Battle battle, long uid) {
		this.setState(MyErrorMessage.success);
		OwnBattleData obd = new OwnBattleData(battle, uid, null);
		this.setData(obd);
	}
	
	/**
	 * 战场信息的返回结果
	 * 
	 * @param battle
	 * @param uid
	 * @param battleReport
	 */
	public MyResponse(Battle battle, long uid, String battleReport) {
		this.setState(MyErrorMessage.success);
		OwnBattleData obd = new OwnBattleData(battle, uid, battleReport);
		this.setData(obd);
	}
	
	public MyResponse(int state) {
		this.state = state;
	}
	
	public MyResponse(int state, String content) {
		this.state = state;
		this.content = content;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		try {
			return JsonAutoCloseOutput.MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.error("toString error", e);
		}
		return super.toString();
	}

	public int getSss() {
		return sss;
	}

	public void setSss(int sss) {
		this.sss = sss;
	}

}

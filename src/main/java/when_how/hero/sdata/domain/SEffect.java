package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;

/**
 * 
 * @author when_how
 *
 */
@SdataTable(table="effect")
public class SEffect {

	@SdataColumn(type="id")
	private int sid;
	
	@SdataColumn
	private String name;
	
	@SdataColumn
	private String intro;
	
	@SdataColumn
	private int type;
	
	@SdataColumn
	private int[] param;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int[] getParam() {
		return param;
	}

	public void setParam(int[] param) {
		this.param = param;
	}
	
}

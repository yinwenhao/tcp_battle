package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;

/**
 * 
 * @author when_how
 *
 */
@SdataTable(table="card")
public class SCard {

	@SdataColumn(type="id")
	private int sid;
	
	@SdataColumn
	private String name;
	
	@SdataColumn
	private String intro;

	@SdataColumn
	private int hp;

	@SdataColumn
	private int att;

	@SdataColumn
	private int cost;

	@SdataColumn
	private int type;

	@SdataColumn
	private int[] aureoleEffect; // 光环

	@SdataColumn
	private int[] battlecryEffect; // 战吼

	@SdataColumn
	private int[] deathrattleEffect; // 亡语

	@SdataColumn
	private int[] inspireEffect; // 激励

	@SdataColumn
	private int[] chooseoneEffect; // 抉择

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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int[] getAureoleEffect() {
		return aureoleEffect;
	}

	public void setAureoleEffect(int[] aureoleEffect) {
		this.aureoleEffect = aureoleEffect;
	}

	public int[] getBattlecryEffect() {
		return battlecryEffect;
	}

	public void setBattlecryEffect(int[] battlecryEffect) {
		this.battlecryEffect = battlecryEffect;
	}

	public int[] getDeathrattleEffect() {
		return deathrattleEffect;
	}

	public void setDeathrattleEffect(int[] deathrattleEffect) {
		this.deathrattleEffect = deathrattleEffect;
	}

	public int[] getInspireEffect() {
		return inspireEffect;
	}

	public void setInspireEffect(int[] inspireEffect) {
		this.inspireEffect = inspireEffect;
	}

	public int[] getChooseoneEffect() {
		return chooseoneEffect;
	}

	public void setChooseoneEffect(int[] chooseoneEffect) {
		this.chooseoneEffect = chooseoneEffect;
	}

}

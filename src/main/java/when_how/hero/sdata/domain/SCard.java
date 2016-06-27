package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;

/**
 * 
 * @author when_how
 *
 */
@SdataTable(table = "card")
public class SCard {

	@SdataColumn(type = "id")
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
	private int[] spellEffect; // 法术效果

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

	public String getName() {
		return name;
	}

	public String getIntro() {
		return intro;
	}

	public int getHp() {
		return hp;
	}

	public int getAtt() {
		return att;
	}

	public int getCost() {
		return cost;
	}

	public int getType() {
		return type;
	}

	public int[] getSpellEffect() {
		return spellEffect;
	}

	public int[] getAureoleEffect() {
		return aureoleEffect;
	}

	public int[] getBattlecryEffect() {
		return battlecryEffect;
	}

	public int[] getDeathrattleEffect() {
		return deathrattleEffect;
	}

	public int[] getInspireEffect() {
		return inspireEffect;
	}

	public int[] getChooseoneEffect() {
		return chooseoneEffect;
	}

}

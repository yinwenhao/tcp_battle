package when_how.hero.dto.own;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.Entity;
import when_how.hero.sdata.cache.SCardCache;
import when_how.hero.sdata.domain.SCard;

@JsonInclude(Include.NON_NULL)
public class OwnEntity {

	private int sid;

	private int att;

	private int attNum;

	private int hpMax;

	private int hp;

	private int[] effect; // 受到的影响

	public OwnEntity(Entity entity) {
		this.sid = entity.getSid();
		this.att = entity.getAtt();
		this.attNum = entity.getAttNum();
		this.hpMax = entity.getHpMax();
		this.hp = entity.getHp();
		SCard sCard = SCardCache.CACHE.get(entity.getSid());
		this.effect = sCard.getEffect();
	}

	public int[] getEffect() {
		return effect;
	}

	public void setEffect(int[] effect) {
		this.effect = effect;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getAttNum() {
		return attNum;
	}

	public void setAttNum(int attNum) {
		this.attNum = attNum;
	}

}

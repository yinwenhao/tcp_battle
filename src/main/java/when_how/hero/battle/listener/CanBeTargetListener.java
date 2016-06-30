package when_how.hero.battle.listener;

public interface CanBeTargetListener {

	public boolean canBeAttackTarget(boolean banBeTarget, boolean isMine);

	public boolean canBeSpellTarget(boolean banBeTarget, boolean isMine);

}

package when_how.hero.battle.effect;

public interface Composite extends MyComponent {

	public void add(MyComponent c);

	public MyComponent remove(int index);

}

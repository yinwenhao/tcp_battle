package when_how.hero.battle.effect;

import java.util.ArrayList;
import java.util.List;

public class BaseCompositeImpl implements Composite {

	private List<MyComponent> components = new ArrayList<MyComponent>();

	@Override
	public void display() {
		for (MyComponent c : components) {
			c.display();
		}
	}

	@Override
	public void add(MyComponent c) {
		components.add(c);
	}

	@Override
	public MyComponent remove(int index) {
		return components.remove(index);
	}

}

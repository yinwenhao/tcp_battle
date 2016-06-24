package when_how.hero.battle.effect;

import java.util.ArrayList;
import java.util.List;

import when_how.hero.common.MyException;

public class BaseCompositeImpl implements Composite {

	private List<MyComponent> components = new ArrayList<MyComponent>();

	@Override
	public void display() throws MyException {
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

	@Override
	public void checkParam() throws MyException {
		for (MyComponent c : components) {
			c.checkParam();
		}
	}

}

package when_how.hero.battle.effect;

import when_how.hero.common.MyException;

public interface MyComponent {

	public void checkParam() throws MyException;

	public void display() throws MyException;

}

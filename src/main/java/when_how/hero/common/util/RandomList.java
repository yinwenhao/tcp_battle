package when_how.hero.common.util;

import java.util.ArrayList;
import java.util.Random;

public class RandomList<T> {
	
	public void getRandomList(ArrayList<T> list) {
		Random random = new Random();
		for (int i=list.size(); i>0; i--) {
			int j = random.nextInt(i);
			if (j != i-1) {
				 T c = list.get(i-1);
				 list.set(i-1, list.get(j));
				 list.set(j, c);
			}
		}
	}
}

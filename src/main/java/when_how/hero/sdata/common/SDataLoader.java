package when_how.hero.sdata.common;

import java.util.List;


/**
 * 静态数组载入器
 * @author when_how
 */
public class SDataLoader {
	
	public static List<Object[]> getModels(Class<?> clazz) {
		String path = "";

		SDataXMLLoader loader = SDataXMLLoader.getInstance(path);
		return loader.getModels(clazz);
	}
}

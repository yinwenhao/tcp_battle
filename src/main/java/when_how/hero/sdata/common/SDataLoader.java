package when_how.hero.sdata.common;

import java.util.List;

import when_how.hero.common.PropertiesConstants;
import when_how.hero.common.PropertiesKeys;


/**
 * 静态数组载入器
 * @author when_how
 */
public class SDataLoader {
	
	public static List<Object[]> getModels(Class<?> clazz) {
		String path = PropertiesConstants.getInstance().getProperties(PropertiesKeys.SDATA);

		SDataXMLLoader loader = SDataXMLLoader.getInstance(path);
		return loader.getModels(clazz);
	}
}

package when_how.hero.sdata.common;

import java.io.IOException;
import java.util.List;


/**
 * 静态数组载入器
 * @author when_how
 */
public class SDataLoader {
	
	/**
	 * 从xml载入
	 * @param clazz
	 * @return List<[id,Object]>
	 */
	public static List<Object[]> getModelsFromXml(Class<?> clazz) {
		String path = "";

		SDataXMLLoader loader = SDataXMLLoader.getInstance(path);
		return loader.getModels(clazz);
	}
	
	/**
	 * 从json载入
	 * @param clazz
	 * @return
	 * @throws IOException 
	 */
	public static <T> List<T> getModelsFromJson(Class<T> clazz) throws IOException {
		return SDataJsonLoader.getModels(clazz);
	}
	
}

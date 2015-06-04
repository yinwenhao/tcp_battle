package when_how.hero.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesConstants {
	/** 静态对象，用于单例 */
	private static PropertiesConstants propertiesConstants = new PropertiesConstants();

	/** 单例实体 */
	private Properties serverProperties = new Properties();

	/** 载入的配置文件的修改时间表 */
	private Map<String, Long> propertiesModifiedTime = new HashMap<String, Long>();

	/**
	 * 载入配置文件
	 * 
	 * @param propertiesFileName
	 */
	private void loadProperties(String propertiesFileName) {
		File serverFile = new File(PropertiesStaticConstants.propertiesFilePath
				+ PropertiesStaticConstants.serverPropertiesFileName);
		propertiesModifiedTime.put(PropertiesStaticConstants.serverPropertiesFileName,
				serverFile.lastModified());
		try {
			FileInputStream fis = new FileInputStream(serverFile);
			serverProperties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得单例对象
	 * 
	 * @return
	 */
	public static PropertiesConstants getInstance() {
		return propertiesConstants;
	}

	/**
	 * 私有的构造方法，用于单例
	 */
	private PropertiesConstants() {
		loadProperties(PropertiesStaticConstants.serverPropertiesFileName);
	}

	/**
	 * 通过key获得配置文件中的String值
	 * 
	 * @param key
	 * @return
	 */
	public String getProperties(String key) {
		if (needReload(PropertiesStaticConstants.serverPropertiesFileName)) {
			loadProperties(PropertiesStaticConstants.serverPropertiesFileName);
		}
		return serverProperties.getProperty(key);
	}

	/**
	 * 通过key获得配置文件中的int值
	 * 
	 * @param key
	 * @return
	 */
	public int getIntProperties(String key) {
		if (needReload(PropertiesStaticConstants.serverPropertiesFileName)) {
			loadProperties(PropertiesStaticConstants.serverPropertiesFileName);
		}
		return Integer.valueOf(serverProperties.getProperty(key));
	}

	/**
	 * 根据配置文件修改时间，判断是否需要重新载入配置文件
	 * 
	 * @param propertiesFileName
	 * @return
	 */
	private boolean needReload(String propertiesFileName) {
		File f = new File(PropertiesStaticConstants.propertiesFilePath + propertiesFileName);
		if (!propertiesModifiedTime.containsKey(propertiesFileName)
				|| !propertiesModifiedTime.get(propertiesFileName).equals(
						f.lastModified())) {
			return true;
		}
		return false;
	}

}

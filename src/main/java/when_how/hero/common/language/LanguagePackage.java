package when_how.hero.common.language;

import java.util.ResourceBundle;

public class LanguagePackage {
	
	private static ResourceBundle resb = ResourceBundle.getBundle("package");
	
	/** 从语言包获得String */
	public static String getProperties(String key) {
		return resb.getString(key);
	}

}

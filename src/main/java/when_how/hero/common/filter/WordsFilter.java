package when_how.hero.common.filter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import when_how.hero.common.PropertiesStaticConstants;


public class WordsFilter {

	private static WordsFilter self = null;

	private static Pattern pattern = Pattern
			.compile("[A-Za-z0-9\u4e00-\u9fa5]+");

	private List<String> pingbiciList;

	private WordsFilter() {
		pingbiciList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(
							PropertiesStaticConstants.propertiesFilePath
									+ PropertiesStaticConstants.pingbiciFile),
					"utf-8"));
			String line = br.readLine();
			while (line != null) {
				pingbiciList.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WordsFilter getInstance() {
		if (self == null) {
			self = new WordsFilter();
		}
		return self;
	}

	/**
	 * 获得屏蔽词处理之后的String，并返回是否有屏蔽词
	 * 
	 * @param text
	 */
	public String getSafeWords(String text) {
		for (String l : pingbiciList) {
			if (text.contains(l)) {
				text = text.replaceAll(l, "**");
			}
		}
		return text;
	}
	
	/**
	 * 
	 * @param text
	 */
	public boolean hasPingbici(String text) {
		for (String l : pingbiciList) {
			if (text.contains(l)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测玩家昵称、联盟名称的合法性
	 * 
	 * @param text
	 */
	public boolean checkName(String name) {
		return pattern.matcher(name).matches();
	}

}

package when_how.hero.common.util;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息格式化器
 * @author when_how
 *
 */
public class MyMessageFormatter {
	
	private List<TextPattern> patternList = new ArrayList<TextPattern>(10);
	
	private String pattern;
	
	private List<Integer> offSetList = new ArrayList<Integer>(10);
	
	/** cacheMap */
	private static Map<String, MyMessageFormatter> cacheMap = new ConcurrentHashMap<String, MyMessageFormatter>();
	
	/** lock */
	private static Object lock = new Object();
	
	/**
	 * 格式化字符串
	 * @param pattern
	 * @param params
	 * @return
	 */
	public static String format(String pattern, Object...params) {
		MyMessageFormatter formatter = cacheMap.get(pattern);
		if (null == formatter) {
			synchronized (lock) {
				if (null == cacheMap.get(pattern)) {
					formatter = new MyMessageFormatter(pattern);
					cacheMap.put(pattern, formatter);
				}
				
			}
		}
		
		return formatter.format(params);
	}
	
	/**
	 * 格式化字符串
	 * @param pattern2
	 * @param params
	 * @return
	 */
	private String format(Object... params) {
		StringBuilder builder = new StringBuilder(this.pattern.length());
		int lastOffset = 0;
		int i = 0;
		for (Integer offset : offSetList) {
			builder.append(this.pattern.substring(lastOffset, offset));
			int index = this.patternList.get(i).index;
			if (index < params.length) {
				builder.append(params[index]);
			} else {
				builder.append("{").append(index).append("}");
			}
			lastOffset = offset;
			i++;
		}
		
		// Append最后
		builder.append(this.pattern.substring(lastOffset, this.pattern.length()));		
		return builder.toString();
	}

	public MyMessageFormatter(String pattern) {		
		applyPattern(pattern);
	}

	/**
	 * 解析Pattern
	 * Hello'{0}'
	 * @param pattern
	 */
	private void applyPattern(String pattern) {
		int braceStack = 0;
		StringBuilder builder = new StringBuilder();
		StringBuilder builder1 = new StringBuilder();
		for (int i = 0; i < pattern.length(); i++) {
			char ch = pattern.charAt(i);
			if (ch == '{' && braceStack == 0) {
				// 第一个花挎号
				braceStack++;
			} else if (braceStack != 0) {
				switch (ch) {
				case '{':
					braceStack++;
					builder.append(ch);
					break;
				case '}':
					if (braceStack == 1) {
//						maxOffSet++;
						offSetList.add(builder.length());
						patternList.add(new TextPattern(Integer.parseInt(builder1.toString()), null));
						builder1.setLength(0);
						braceStack--;
						break;
					} else {
						braceStack--;
						builder.append(ch);
					}
				default:
					builder1.append(ch);
					break;
				}
			} else {
				builder.append(ch);
			}
		}
		this.pattern = builder.toString();
	}
	
	/**
	 * TextPattern
	 * @author when_how
	 *
	 */
	private class TextPattern {	
		public TextPattern(int index, Format format) {
			this.index = index;
			this.format = format;
		}
		/** 序号 */
		public int index;
		/** 格式化器 */
		@SuppressWarnings("unused")
		public Format format;
	}
}
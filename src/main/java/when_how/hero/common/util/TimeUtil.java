package when_how.hero.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	
	/** 半小时的毫秒数 */
	public static final int halfhourMillisecond = 1800 * 1000;

	/** 一小时的毫秒数 */
	public static final int hourMillisecond = 3600 * 1000;

	/** 一天的毫秒数 */
	public static final int dayMillisecond = 3600 * 1000 * 24;

	public static final String timeStr = "yyyy-MM-dd HH:mm:ss";

	private static DateFormat df = new SimpleDateFormat(timeStr);

	public static String formatDateToString(Date time) {
		return df.format(time);
	}

	public static Date parseDateFromString(String dateString)
			throws ParseException {
		return df.parse(dateString);
	}

	public static Date getNext0Time(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}

	public static Date getNext5Time(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		if (cal.get(Calendar.HOUR_OF_DAY) >= 5) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		cal.set(Calendar.HOUR_OF_DAY, 5);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

}

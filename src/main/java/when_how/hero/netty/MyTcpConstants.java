package when_how.hero.netty;

import org.springframework.beans.factory.BeanFactory;

import when_how.hero.common.util.TimeUtil;


public class MyTcpConstants {
	
	/** mySession过期的毫秒数 */
	public static final int mySessionTime = TimeUtil.halfhourMillisecond;

	/** 读检测时间（超过这个时间没有读到数据，则断开连接） */
	public static final int readTimeForClose = 30;

	public static BeanFactory factory;

	/** 编码方式 */
	public static final String charset = "UTF-8";

	/** tcp包内容的长度（字节） */
	public static final int lengthFieldLength = 4;
	public static final int maxFrameLength = Integer.MAX_VALUE;

	/** tcp包内容的类型标志字节长度（如：字符串、zip包等） */
	public static final int typeFieldLength = 1;
	/** tcp包内容的类型标志 字符串 默认的 */
	public static final byte type_string = 0x00;
	/** tcp包内容的类型标志 gzip包 */
	public static final byte type_gzip = 0x01;

	/** tcp包自定义sessionId的字节长度 */
	public static final int sessionIdFieldLength = 4;
	
	/** tcp包自定义接口编号的字节长度 */
	public static final int jiekouIdFieldLength = 2;

	public static long getLong(byte[] bb) {
		return ((((long) bb[0] & 0xff) << 56) | (((long) bb[1] & 0xff) << 48)
				| (((long) bb[2] & 0xff) << 40) | (((long) bb[3] & 0xff) << 32)
				| (((long) bb[4] & 0xff) << 24) | (((long) bb[5] & 0xff) << 16)
				| (((long) bb[6] & 0xff) << 8) | (((long) bb[7] & 0xff) << 0));
	}

}

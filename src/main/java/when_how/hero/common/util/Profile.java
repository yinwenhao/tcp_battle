package when_how.hero.common.util;

/**
 * 配置文件工具类
 * @author when_how
 *
 */
public class Profile {
	public static final String SERVER_FILE_NAME = "server.properties";
	
	public static class Server {
		private String test;

		public void setTest(String test) {
			this.test = test;
		}

		public String getTest() {
			return test;
		}
	}
}

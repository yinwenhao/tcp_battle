package when_how.hero;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import when_how.hero.netty.MyTcpConstants;
import when_how.hero.netty.NettyServer;

public class Main {

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configureAndWatch(System.getProperty("confPath")
				+ "/log4j.properties", 60 * 1000);
		MyTcpConstants.factory = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		NettyServer server = new NettyServer();
		server.startServer();
	}
}

package when_how.hero;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import when_how.hero.netty.MyTcpConstants;
import when_how.hero.netty.NettyServer;

public class Main {

	private static Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configureAndWatch(System.getProperty("confPath") + "/log4j.properties", 60 * 1000);
		log.error("test {} -> {}", 1, 2);
		MyTcpConstants.factory = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		NettyServer server = new NettyServer();
		server.startServer();
	}
}

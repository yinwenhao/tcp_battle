package when_how.hero;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import when_how.hero.netty.DefaultMySessionListener;
import when_how.hero.netty.MyNettyMain;
import when_how.hero.netty.MyTcpConstants;


public class Main {

	public static void main(String[] args) throws Exception {
		Logger logger = LoggerFactory.getLogger(Main.class);
		MyNettyMain.initNetty(new DefaultMySessionListener(), 1, 0, MyTcpConstants.readTimeForClose);
	}
}

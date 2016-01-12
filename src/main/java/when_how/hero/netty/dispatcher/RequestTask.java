package when_how.hero.netty.dispatcher;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import when_how.hero.common.json.MyResponse;

public class RequestTask implements Runnable {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private String actionClassBean;

	private String method;

	private Map<String, Object> param;

	private ChannelHandlerContext ctx;

	public RequestTask(String actionClassBean, String method,
			Map<String, Object> param, ChannelHandlerContext ctx) {
		this.actionClassBean = actionClassBean;
		this.method = method;
		this.param = param;
		this.ctx = ctx;
	}

	@Override
	public void run() {
		try {
			MyResponse response = MyDispatcher.getResult(actionClassBean,
					method, param);
			ctx.writeAndFlush(response);
		} catch (Exception e) {
			log.error(actionClassBean + "." + method + "(" + param + ")", e);
		}
	}

}

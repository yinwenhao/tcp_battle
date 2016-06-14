package when_how.hero.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import when_how.hero.netty.MyTcpConstants;
import when_how.hero.netty.dispatcher.RequestExecutor;
import when_how.hero.netty.dispatcher.RequestTask;
import when_how.hero.request.Request;

public class TcpServerHandler extends ChannelInboundHandlerAdapter {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		Request request = (Request) msg;
		if (log.isDebugEnabled()) {
			log.debug(request.getBean() + "." + request.getMethod() + "("
					+ request.getParam() + ")");
		}
		if (request.getParam().containsKey("uid")) {
			throw new Exception("参数错误，不能含有uid");
		}
		RequestExecutor executor = MyTcpConstants.factory
				.getBean(RequestExecutor.class);
		executor.execute(new RequestTask(request.getBean(),
				request.getMethod(), request.getParam(), ctx));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.error("exceptionCaught", cause);
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		log.debug("连接上了");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		log.debug("断开连接了");
	}
}

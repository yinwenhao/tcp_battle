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
		log.debug(request.getBean() + "." + request.getMethod() + "("
				+ request.getParam() + ")");
		RequestExecutor executor = MyTcpConstants.factory
				.getBean(RequestExecutor.class);
		executor.execute(new RequestTask(request.getBean(),
				request.getMethod(), request.getParam(), ctx));
		// long startTime = System.currentTimeMillis();
		// ByteBuf msgByteBuf = (ByteBuf) msg;
		// int length = msgByteBuf.readInt();
		// System.out.println("length: " + length);
		// int sessionId = msgByteBuf.readInt();
		// System.out.println("sessionId: " + sessionId);
		// SocketAddress remoteAddress = ctx.channel().remoteAddress();
		// sessionId = MySessionManager.checkAndGetSessionId(sessionId,
		// remoteAddress.hashCode());
		// MySession session = MySessionManager.getSession(sessionId);
		// session.setCtx(ctx);
		//
		// short jiekouId = msgByteBuf.readShort();
		// System.out.println("jiekouId: " + jiekouId);
		// byte type = msgByteBuf.readByte();
		// System.out.println("type: " + type);
		// byte[] contentBytes = new byte[msgByteBuf.readableBytes()];
		// msgByteBuf.readBytes(contentBytes);
		// System.out.println("content length: " + contentBytes.length);
		//
		// MySimpleEncrypt.decode(contentBytes);
		// String content = null;
		// switch (type) {
		// case MyTcpConstants.type_string:
		// // 字符串
		// content = new String(contentBytes, MyTcpConstants.charset);
		// break;
		// case MyTcpConstants.type_gzip:
		// // gzip
		// content = new String(GZipUtils.decompress(contentBytes),
		// MyTcpConstants.charset);
		// break;
		// default:
		// // 默认字符串
		// content = new String(contentBytes, MyTcpConstants.charset);
		// break;
		// }
		// System.out.println("content: " + content);
		//
		// MyResponse response =MyDispatcher.getResult(jiekouId, content,
		// session);
		// session.sendResponse(jiekouId, response);
		//
		// long endTime = System.currentTimeMillis();
		// if (session.containsKey(MyConstants.SESSION_KEY_USER)) {
		// UserDto userDto = (UserDto)
		// session.get(MyConstants.SESSION_KEY_USER);
		// // log.info(MyConstants.formatInterfaceLog());
		// } else {
		// // log.info(MyConstants.formatInterfaceLog(0, "null", 0,
		// remoteAddress.toString(), jiekouId, endTime - startTime, 0,
		// content));
		// }
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
		// System.out.println("连接上了");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		// System.out.println("断开连接了");
	}
}

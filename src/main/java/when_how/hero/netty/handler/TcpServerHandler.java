package when_how.hero.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import when_how.hero.common.MyConstants;
import when_how.hero.common.MySimpleEncrypt;
import when_how.hero.common.json.MyResponse;
import when_how.hero.common.util.GZipUtils;
import when_how.hero.netty.MyDispatcher;
import when_how.hero.netty.MySession;
import when_how.hero.netty.MySessionManager;
import when_how.hero.netty.MyTcpConstants;
import when_how.hero.request.dto.UserDto;


public class TcpServerHandler extends ChannelInboundHandlerAdapter {

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 4字节包长度，4字节sessionId，2字节接口编号，1字节是否压缩，内容
	 * 只有内容先加密再压缩
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		long startTime = System.currentTimeMillis();
		ByteBuf msgByteBuf = (ByteBuf) msg;
		int length = msgByteBuf.readInt();
		System.out.println("length: " + length);
		int sessionId = msgByteBuf.readInt();
		System.out.println("sessionId: " + sessionId);
		SocketAddress remoteAddress = ctx.channel().remoteAddress();
		sessionId = MySessionManager.checkAndGetSessionId(sessionId, remoteAddress.hashCode());
		MySession session = MySessionManager.getSession(sessionId);
		session.setCtx(ctx);
		
		short jiekouId = msgByteBuf.readShort();
		System.out.println("jiekouId: " + jiekouId);
		byte type = msgByteBuf.readByte();
		System.out.println("type: " + type);
		byte[] contentBytes = new byte[msgByteBuf.readableBytes()];
		msgByteBuf.readBytes(contentBytes);
		System.out.println("content length: " + contentBytes.length);
		
		MySimpleEncrypt.decode(contentBytes);
		String content = null;
		switch (type) {
		case MyTcpConstants.type_string:
			// 字符串
			content = new String(contentBytes, MyTcpConstants.charset);
			break;
		case MyTcpConstants.type_gzip:
			// gzip
			content = new String(GZipUtils.decompress(contentBytes), MyTcpConstants.charset);
			break;
		default:
			// 默认字符串
			content = new String(contentBytes, MyTcpConstants.charset);
			break;
		}
		System.out.println("content: " + content);
		
		MyResponse response =MyDispatcher.getResult(jiekouId, content, session);
		session.sendResponse(jiekouId, response);

		long endTime = System.currentTimeMillis();
		if (session.containsKey(MyConstants.SESSION_KEY_USER)) {
			UserDto userDto = (UserDto) session.get(MyConstants.SESSION_KEY_USER);
//			log.info(MyConstants.formatInterfaceLog());
		} else {
//			log.info(MyConstants.formatInterfaceLog(0, "null", 0, remoteAddress.toString(), jiekouId, endTime - startTime, 0, content));
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
//		System.out.println("连接上了");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
//		System.out.println("断开连接了");
	}
}

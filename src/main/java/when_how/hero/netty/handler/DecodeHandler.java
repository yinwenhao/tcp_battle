package when_how.hero.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import when_how.hero.netty.MyTcpConstants;
import when_how.hero.netty.serial.InputFactory;

/**
 * 解码
 * 
 * @author yinwenhao
 *
 */
public class DecodeHandler extends ChannelInboundHandlerAdapter {

	private final InputFactory input;

	private final Class<?> clazz;

	public DecodeHandler(InputFactory input, Class<?> clazz) {
		this.input = input;
		this.clazz = clazz;
	}

	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object message)
			throws Exception {
		ByteBuf buffer = ByteBuf.class.cast(message);
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.readBytes(bytes);
		ctx.fireChannelRead(input.input(bytes,
				MyTcpConstants.lengthFieldLength, this.clazz));
	}

}

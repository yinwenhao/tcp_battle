package when_how.hero.netty.handler;

import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import when_how.hero.netty.listener.ExceptionListener;
import when_how.hero.netty.serial.OutputFactory;

/**
 * 编码
 * @author yinwenhao
 *
 */
public class EncodeHandler extends ChannelOutboundHandlerAdapter{

	private final PooledByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;

	private final OutputFactory output;
	
	public EncodeHandler(OutputFactory output) {
		this.output = output;
	}
	
	@Override
	public void write(ChannelHandlerContext ctx, final Object msg, ChannelPromise promise) throws Exception {
		this.writeAndFlush(ctx, this.output.output(msg));
	}

	private void writeAndFlush(ChannelHandlerContext ctx, byte[] serial) {
		ctx.writeAndFlush(this.allocator.heapBuffer(serial.length).writeBytes(serial)).addListener(ExceptionListener.TRACE);
	}
	
}

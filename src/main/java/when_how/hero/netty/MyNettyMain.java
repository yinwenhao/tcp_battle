package when_how.hero.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import when_how.hero.common.PropertiesConstants;
import when_how.hero.common.PropertiesKeys;
import when_how.hero.common.PropertiesStaticConstants;
import when_how.hero.common.listener.IMySessionListener;

public class MyNettyMain {

	public static void initNetty(IMySessionListener mySessionListener, int bossNum, int workerNum, final int readSecondForClose) throws Exception {

		PropertiesStaticConstants.propertiesFilePath = System.getProperty("webDir", "");
//		PropertyConfigurator.configure("WebContent/WEB-INF/log4j.properties");
		MyTcpConstants.factory = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		
//		MySessionManager.setMySessionListener(mySessionListener);

		EventLoopGroup bossGroup = new NioEventLoopGroup(bossNum);
		EventLoopGroup workerGroup = new NioEventLoopGroup(workerNum);
		
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch)
								throws Exception {
							ChannelPipeline p = ch.pipeline();
//							EventExecutorGroup myWorkerGroup = new DefaultEventExecutorGroup(500); 这个还没搞懂
							p.addLast("idleStateHandler", new IdleStateHandler(
									readSecondForClose, 0, 0));
							p.addLast("closeHandler", new MyReaderHandler());
							p.addLast("decoder",
									new LengthFieldBasedFrameDecoder(
											MyTcpConstants.maxFrameLength, 0,
											MyTcpConstants.lengthFieldLength));
							p.addLast("encoder", new LengthFieldPrepender(
									MyTcpConstants.lengthFieldLength));
							p.addLast("actionHandler", new TcpServerHandler());
						}
					});
			
			// 使用对象池
			b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT); 
			b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);//关键是这句
			
			// Bind and start to accept incoming connections.
			b.bind(PropertiesConstants.getInstance().getIntProperties(
					PropertiesKeys.PORT)).sync().channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
}

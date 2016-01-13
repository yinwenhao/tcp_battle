package tcp_battle;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import when_how.hero.common.json.MyResponse;
import when_how.hero.netty.MyTcpConstants;
import when_how.hero.netty.handler.DecodeHandler;
import when_how.hero.netty.handler.EncodeHandler;
import when_how.hero.netty.serial.impl.JsonSerialFactory;

public class TestNettyTcpMain {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {
    	
    	JsonSerialFactory a = new JsonSerialFactory();

//    	JsonSerialFactory aa = new JsonSerialFactory();
    	
    	final EncodeHandler encoder = new EncodeHandler(a);
    	
    	final DecodeHandler decoder = new DecodeHandler(a, MyResponse.class);
    	
        // Configure SSL.git
        final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
			 .handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch)
								throws Exception {
							ChannelPipeline p = ch.pipeline();
//							p.addLast("closeHandler", new MyReaderHandler());
							p.addLast("lengthFieldBasedFrameDecoder",
									new LengthFieldBasedFrameDecoder(
											MyTcpConstants.maxFrameLength, 0,
											MyTcpConstants.lengthFieldLength));
							p.addLast("lengthFieldPrepender", new LengthFieldPrepender(
									MyTcpConstants.lengthFieldLength));
							p.addLast("encoder", encoder);
							p.addLast("decoder", decoder);
							p.addLast("actionHandler", new EchoClientHandler());
						}
					});

            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}

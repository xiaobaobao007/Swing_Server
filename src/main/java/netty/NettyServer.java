package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class NettyServer {

	//设备连接的map
	private Map<String, Channel> channelMap = new ConcurrentHashMap<>();

	public Channel getChannel(String key) {
		return channelMap.get(key);
	}

	public void addChannel(String key, Channel channel) {
		channelMap.put(key, channel);
	}

	public void removeChannel(String key) {
		channelMap.remove(key);
	}

	//发送消息给下游设备
	public boolean writeMsg(Object msg) {
		//初始状态正常
		boolean normal = true;
		for (Channel channel : channelMap.values()) {
			try {
				if (channel.isActive()) {
					channel.writeAndFlush(msg);
				} else {
					normal = false;
				}
			} catch (Exception e) {
				normal = false;
			}
		}
		return normal;
	}

	public void bind() {
		System.out.println("service start successful");
		ServerBootstrap bootstrap = new ServerBootstrap();
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		bootstrap.group(bossGroup, workerGroup)
				//设置协议（这个协议很常用，异步的TCP协议）
				.channel(NioServerSocketChannel.class)
				//设置长连接
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				//指定待处理的消息队列的大小
				.option(ChannelOption.SO_BACKLOG, 1024)
				//当服务器成功启动会初始化执行（监听channel的变化）
//				.handler(new LoggingHandler(LogLevel.INFO))
				//当客户端连接到服务器的初始化（监听连接成功的channel的变化）
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) {
						//将Channel数据抽象为ChannelPipeline
						ChannelPipeline pipeline = socketChannel.pipeline();
						//编码格式
						pipeline.addLast("decoder", new JDKDecoder());
						//解码格式
						pipeline.addLast("encoder", new JDKEncoder());
						//心跳机制
						pipeline.addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS));
						//服务器逻辑执行
						pipeline.addLast("handler", new NettyServerHandler(NettyServer.this));
					}
				});

		try {
			ChannelFuture f = bootstrap.bind(20803).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		NettyServer nettyServer = new NettyServer();
		new Thread(nettyServer::bind).start();
		Scanner scanner = new Scanner(System.in);
		String msg;
		while (!(msg = scanner.nextLine()).equals("exit")) {
//			Msg m = new Msg();
//			m.setMsg(msg);
//			nettyServer.writeMsg(m);
		}
	}

}
package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class NettyServerHandler extends SimpleChannelInboundHandler {

	private int counter = 0;
	private NettyServer nettyServer;

	public NettyServerHandler(NettyServer nettyServer) {
		this.nettyServer = nettyServer;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
//		System.out.println("client：" + o.toString());
//		String clientName = ctx.channel().remoteAddress().toString();
//		Channel channel = nettyServer.getChannel(clientName);
//		if (channel != null) {
//			channel.writeAndFlush("1" + System.getProperty("line.separator"));
//		}
//		ctx.writeAndFlush("2" + System.getProperty("line.separator"));
//		ctx.channel().writeAndFlush("3" + System.getProperty("line.separator"));
//		ctx.flush();
		counter = 0;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		counter = 0;
		String clientName = ctx.channel().remoteAddress().toString();
		System.out.println(clientName + "connect");
		nettyServer.addChannel(clientName, ctx.channel());
		super.channelActive(ctx);
	}

	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
		System.out.println("收到新的客户端连接：" + ctx.name());
	}

	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);
		System.out.println("客户端断开");
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println(System.currentTimeMillis());
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state().equals(IdleState.READER_IDLE)) {
				//空闲4s后触发
				if (counter >= 10) {
					ctx.channel().close().sync();
					String clientName = ctx.channel().remoteAddress().toString();
					System.out.println(clientName + "outline");
					nettyServer.removeChannel(clientName);
//					//判断是否有在线的
//					if (nettyServer.getClientMapSize()) {
//						return;
//					}
				} else {
					counter++;
					System.out.println("loss" + counter + "count HB");
				}
			}
		}
	}
}
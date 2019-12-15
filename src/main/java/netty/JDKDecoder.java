package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * @author xiaobaobao
 * @date 2019/12/15，17:16
 */
public class JDKDecoder extends MessageToMessageDecoder<ByteBuf> {
	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
		final int length = byteBuf.readableBytes();
		final byte[] b = new byte[length];
		byteBuf.getBytes(byteBuf.readerIndex(), b, 0, length);

		ByteArrayInputStream bis = new ByteArrayInputStream(b);
		ObjectInputStream ois = new ObjectInputStream(bis);
		list.add(ois.readObject());
		ois.close();
	}
}
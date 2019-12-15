package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author xiaobaobao
 * @date 2019/12/15，17:17
 */
public class JDKEncoder extends MessageToByteEncoder<Object> {
	@Override
	protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);

		oos.writeObject(o);
		oos.flush();
		byteBuf.writeBytes(bos.toByteArray());
		bos.close();
		oos.close();
	}
}
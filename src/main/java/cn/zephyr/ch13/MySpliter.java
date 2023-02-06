package cn.zephyr.ch13;

import cn.zephyr.ch8.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @ClassName MySpliter
 * @Author laizonghao
 * @CreateTime 2023/1/30 22:24
 * @Description
 **/
public class MySpliter extends LengthFieldBasedFrameDecoder {
    public MySpliter() {
        super(Integer.MAX_VALUE,7,4);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议请求
        if(in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER){
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}

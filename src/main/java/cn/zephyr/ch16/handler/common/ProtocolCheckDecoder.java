package cn.zephyr.ch16.handler.common;

import cn.zephyr.ch16.common.MyPacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @ClassName Spliter
 * @Author laizonghao
 * @CreateTime 2023/2/6 14:29
 * @Description
 **/
public class ProtocolCheckDecoder extends LengthFieldBasedFrameDecoder {

    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public ProtocolCheckDecoder(){
        //数据包的最大长度、长度域的偏移量、长度域的长度
        super(Integer.MAX_VALUE,LENGTH_FIELD_OFFSET,LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端
        if(in.getInt(in.readerIndex()) != MyPacketCodeC.MAGIC_NUMBER){
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }

}

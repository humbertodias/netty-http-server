package http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.*;

public class NettyHttpServerInitializer extends ChannelInitializer {
    private static final int MB = 1024 * 1024;

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline().addLast(
                        new HttpServerCodec(),
                        new HttpObjectAggregator(1 * MB),
                        new NettyHttpServerHandler());
    }
}

package cmd;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

public class CommandCodec extends MessageToMessageCodec<FullHttpRequest, Command> {
    public static final String LF = "\n";

    @Override
    protected void encode(ChannelHandlerContext ctx, Command cmd, List<Object> out) {
        out.add(cmd + LF);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpRequest request, List<Object> out) {
        Command cmd = CommandParser.parse(request.uri()).setRequest(request);
        out.add(cmd);
    }
}

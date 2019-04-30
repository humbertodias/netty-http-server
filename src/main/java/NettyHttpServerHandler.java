package http;

import io.netty.buffer.*;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.HttpHeaders.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class NettyHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        showContent(req);
        showParameters(req);
        welcomeResponse(ctx);
    }

    private void showContent(FullHttpRequest req) {
        System.out.printf("uri: %s\n", req.uri());

        ByteBuf content = req.content();
        if (content instanceof EmptyByteBuf) {
            System.out.println("Content：No data");
        } else {
            byte[] bytes = ByteBufUtil.getBytes(content);
            System.out.println("Content:" + new String(bytes));
        }
    }

    private void showParameters(FullHttpRequest req) {
        final QueryStringDecoder queryStringDecoder = new QueryStringDecoder(req.uri());
        Map<String, List<String>> parameters = queryStringDecoder.parameters();
        System.out.println("Parameters");
        parameters.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    private void welcomeResponse(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer("Welcome".getBytes("utf-8")));
        response.headers()
                .set(Names.CONTENT_TYPE, "text/plain;charset=UTF-8")
                .set(Names.CONTENT_LENGTH, response.content().readableBytes())
                .set(Names.CONNECTION, Values.KEEP_ALIVE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
        cause.printStackTrace();
    }
}

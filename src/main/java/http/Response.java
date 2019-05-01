package http;

import cmd.Command;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public abstract class Response {

    private static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
    private static final AsciiString TEXT_PLAIN = AsciiString.cached("text/plain; charset=utf-8");
    private static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    protected PrintWriter out = new PrintWriter(baos);
    protected Command cmd;

    public Response(Command cmd) {
        this.cmd = cmd;
    }

    public void writeAndFlush(ChannelHandlerContext ctx) {
        write();
        out.flush();
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.wrappedBuffer(baos.toByteArray()));
        response.headers().set(CONTENT_TYPE, TEXT_PLAIN).set(CONTENT_LENGTH,
                response.content().readableBytes());
        ctx.writeAndFlush(response);
        baos.reset();
    }

    public abstract void write();
}

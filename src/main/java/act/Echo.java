package act;

import cmd.Command;
import http.Response;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

public class Echo extends Response {

    public Echo(Command cmd) {
        super(cmd);
    }

    private void showContent(FullHttpRequest req) {
        out.printf("uri: %s\n", req.uri());

        ByteBuf byteContent = req.content();
        String content = byteContent instanceof EmptyByteBuf ? "No data"
                : new String(ByteBufUtil.getBytes(byteContent));
        out.printf("Content: %s\n", content);
    }

    private void showParameters(FullHttpRequest req) {
        out.println("Parameters");
        final QueryStringDecoder queryStringDecoder = new QueryStringDecoder(req.uri());
        queryStringDecoder.parameters().forEach((k, v) -> out.printf("%s:%s\n", k, v));
    }

    @Override
    public void write() {
        FullHttpRequest req = cmd.getRequest();
        showContent(req);
        showParameters(req);
    }
}

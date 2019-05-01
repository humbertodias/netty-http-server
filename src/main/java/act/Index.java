package act;

import cmd.Command;
import http.Response;

public class Index extends Response {
    public Index(Command cmd) {
        super(cmd);
    }

    @Override
    public void write() {
        out.println("Welcome to Netty");
    }
}

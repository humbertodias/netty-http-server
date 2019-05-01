package act;

import cmd.Command;
import http.Response;

public class Invalid extends Response {
    public Invalid(Command cmd) {
        super(cmd);
    }

    @Override
    public void write() {
        out.println("Invalid command");
    }
}

package act;

import cmd.Command;
import http.Response;

import java.time.Instant;

public class Time extends Response {
    public Time(Command cmd) {
        super(cmd);
    }

    @Override
    public void write() {
        out.println(Instant.now());
    }
}

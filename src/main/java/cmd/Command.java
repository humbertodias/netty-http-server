package cmd;

import act.Echo;
import act.Index;
import act.Invalid;
import act.Time;
import http.Response;
import io.netty.handler.codec.http.FullHttpRequest;

public enum Command {
    INDEX {
        @Override
        public Response response() {
            return new Index(this);
        }
    },
    ECHO {
        @Override
        public Response response() {
            return new Echo(this);
        }
    },
    TIME {
        @Override
        public Response response() {
            return new Time(this);
        }
    },
    INVALID {
        @Override
        public Response response() {
            return new Invalid(this);
        }
    };

    private String argument;

    public Command setArgument(String argument) {
        this.argument = argument;
        return this;
    }
    
    public String getArgument() {
    	return argument;
    }

    private FullHttpRequest request;

    public Command setRequest(FullHttpRequest request) {
        this.request = request;
        return this;
    }

    public FullHttpRequest getRequest() {
        return request;
    }

    public abstract Response response();

}

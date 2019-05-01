package cmd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private CommandParser() {
    }

    private static final Pattern pattern = Pattern.compile("/(?<cmd>echo|time)(?<arg>.*)", Pattern.CASE_INSENSITIVE);

    public static Command parse(String line) {
        if (line.startsWith("/")) {

            if (line.length() == 1)
                return Command.INDEX;

            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String cmd = matcher.group("cmd").toUpperCase();
                String arg = matcher.group("arg");
                return Command.valueOf(cmd).setArgument(arg);
            }

            return Command.INVALID.setArgument(line);
        }
        return Command.INVALID.setArgument(line);
    }
}

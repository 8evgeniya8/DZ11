package command;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static message.ServerMessage.DATE_TIME;
import static message.ServerMessage.FAREWELL;

public class DateAndFarewellCommand implements Command {
    private final PrintWriter out;

    public DateAndFarewellCommand(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void execute() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        out.println(DATE_TIME + now.format(formatter) + FAREWELL);
        out.close();
    }
}
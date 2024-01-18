package command;

import java.io.PrintWriter;

import static message.ServerMessage.CONFIRMATION_RECEIPT_NOTICE;

public class DefaultCommand implements Command{
    private final PrintWriter out;

    public DefaultCommand(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void execute() {
        out.println(CONFIRMATION_RECEIPT_NOTICE);
    }
}

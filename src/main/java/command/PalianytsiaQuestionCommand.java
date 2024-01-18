package command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static message.ServerMessage.INCORRECT_ANSWER;
import static message.ServerMessage.PALIANYTSIA_QUESTION;

public class PalianytsiaQuestionCommand implements Command {
    private final PrintWriter out;
    private final BufferedReader in;

    public PalianytsiaQuestionCommand(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() {
        try {
            out.println(PALIANYTSIA_QUESTION);

            String answer = in.readLine();

            if (answer != null && answer.trim().equals("2")) {
                new DateAndFarewellCommand(out).execute();
            } else {
                out.println(INCORRECT_ANSWER);
                out.close();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}


package command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class CommandFactory {
    public static Command createCommand(String message, PrintWriter out, BufferedReader in) {
        Set<Character> russianExclusiveChars = new HashSet<>();
        russianExclusiveChars.add('ы');
        russianExclusiveChars.add('э');
        russianExclusiveChars.add('ъ');

        for (char ch : message.toLowerCase().toCharArray()) {
            if (russianExclusiveChars.contains(ch)) {
                return new PalianytsiaQuestionCommand(out, in);
            }
        }
        return new DefaultCommand(out);
    }
}
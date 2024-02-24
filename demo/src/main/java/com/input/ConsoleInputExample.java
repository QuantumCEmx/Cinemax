package com.input;

import org.jline.keymap.KeyMap;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.MouseEvent;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.reader.Binding;
import java.io.IOException;

public class ConsoleInputExample {
    public static void main(String[] args) {
        try {
            Terminal terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();

            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();

            String line;
            while ((line = reader.readLine("Enter command: ")) != null) {
                System.out.println("You entered: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package subway.stub;

import java.util.Scanner;
import subway.io.ConsoleReader;

public class StubConsoleReader extends ConsoleReader {
    private String[] input;
    private int idx;

    public StubConsoleReader(Scanner scanner) {
        super(scanner);
    }

    public String readLine() {
        return input[idx++];
    }

    public void setInput(String... args) {
        input = args;
    }
}

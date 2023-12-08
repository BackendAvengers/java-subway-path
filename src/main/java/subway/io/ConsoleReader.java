package subway.io;

import java.util.Scanner;

public class ConsoleReader {
    private final Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine() {
        return scanner.nextLine();
    }
}

package subway.stub;

import subway.io.ConsoleWriter;

public class StubConsoleWriter extends ConsoleWriter {
    private String output;

    @Override
    public void writeLine(String message) {
        output = message;
    }

    public String getOutput() {
        return output;
    }
}

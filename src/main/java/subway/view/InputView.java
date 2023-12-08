package subway.view;

import subway.io.ConsoleReader;
import subway.io.ConsoleWriter;

public class InputView {
    private final ConsoleReader reader;
    private final ConsoleWriter writer;

    public InputView(ConsoleReader reader, ConsoleWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    private String inputWithMessage(String inputMessage) {
        writer.writeLine(inputMessage);
        return reader.readLine();
    }

    public String inputFindRouteSignal() {
        return inputWithMessage("""
                ## 메인 화면
                1. 경로 조회
                Q. 종료
                                
                ## 원하는 기능을 선택하세요.
                """);
    }
}
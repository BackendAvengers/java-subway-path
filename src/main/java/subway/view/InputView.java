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

    public String inputMainFeature() {
        return inputWithMessage("""
                ## 메인 화면
                1. 경로 조회
                Q. 종료
                                
                ## 원하는 기능을 선택하세요.
                """);
    }

    public String inputRouteSearchCriteria() {
        return inputWithMessage("""
                ## 경로 기준
                1. 최단 거리
                2. 최소 시간
                B. 돌아가기

                ## 원하는 기능을 선택하세요.
                """);
    }

    public String inputDepartureStation() {
        return inputWithMessage("""
                ## 출발역을 입력하세요.
                """);
    }

    public String inputArrivalStation() {
        return inputWithMessage("""
                ## 도착역을 입력하세요.
                """);
    }
}
package subway.view;

import java.util.List;
import java.util.stream.Collectors;
import subway.dto.RouteSearchResultDto;
import subway.io.ConsoleWriter;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final ConsoleWriter writer;

    public OutputView(ConsoleWriter writer) {
        this.writer = writer;
    }

    private String joinWithNewLine(String... messages) {
        return String.join(LINE_SEPARATOR, messages);
    }

    private String joinWithPrefix(String prefix, String message) {
        return prefix + message;
    }

    public void outputRouteSearchResult(RouteSearchResultDto routeSearchResultDto) {
        final String prefix = "[INFO] ";
        int distance = routeSearchResultDto.distance();
        int arrivalTime = routeSearchResultDto.arrivalTime();

        String stationsMessage = getStationsMessage(routeSearchResultDto.intermediateStations(), prefix);
        String result = getRouteSearchResultMessage(prefix, distance, arrivalTime, stationsMessage);
        writer.writeLine(result);
    }

    private String getStationsMessage(List<String> intermediateStations, String prefix) {
        return intermediateStations.stream()
                .map(station -> joinWithPrefix(prefix, station))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String getRouteSearchResultMessage(String prefix, int distance, int arrivalTime, String stationsMessage) {
        return joinWithNewLine(joinWithPrefix(prefix, "---"),
                joinWithPrefix(prefix, String.format("총 거리: %,dkm", distance)),
                joinWithPrefix(prefix, String.format("총 소요 시간: %,d분", arrivalTime)),
                joinWithPrefix(prefix, "---"),
                stationsMessage) + LINE_SEPARATOR;
    }

    public void outputMessage(String message) {
        writer.writeLine(message);
    }
}

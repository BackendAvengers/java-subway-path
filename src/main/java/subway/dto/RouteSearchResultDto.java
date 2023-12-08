package subway.dto;

import java.util.List;

public record RouteSearchResultDto(List<String> intermediateStations, int distance, int arrivalTime) {
}

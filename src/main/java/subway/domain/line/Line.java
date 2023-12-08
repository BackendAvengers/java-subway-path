package subway.domain.line;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import subway.domain.station.Station;
import subway.domain.station.StationCost;

public class Line {
    private final String name;
    private final Map<Station, StationCost> stationCost = new LinkedHashMap<>();

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addStation(Station station, StationCost stationCost) {
        this.stationCost.put(station, stationCost);
    }

    private static boolean isAdjacent(Station departure, Station arrival, List<Station> allStations) {
        return allStations.indexOf(arrival) == allStations.indexOf(departure) + 1;
    }

    public List<Station> getAllStations() {
        return stationCost.keySet().stream().toList();
    }

    public StationCost findStationCost(Station station) {
        return stationCost.get(station);
    }

    public Optional<StationCost> findStationCost(Station departure, Station arrival) {
        List<Station> allStations = getAllStations();
        if (isAdjacent(departure, arrival, allStations)) {
            return Optional.of(stationCost.get(departure));
        }
        return Optional.empty();
    }
}

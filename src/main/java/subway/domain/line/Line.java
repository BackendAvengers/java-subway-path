package subway.domain.line;

import java.util.LinkedHashMap;
import java.util.Map;
import subway.domain.station.Station;
import subway.domain.station.StationCost;

public class Line {
    private final Map<Station, StationCost> stationCost = new LinkedHashMap<>();
    private String name;

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addStation(Station station, StationCost stationCost) {
        this.stationCost.put(station, stationCost);
    }
}

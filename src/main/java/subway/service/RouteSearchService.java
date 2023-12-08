package subway.service;

import static subway.exception.ErrorMessage.SAME_DEPARTURE_AND_ARRIVAL_STATION;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import subway.controller.constants.RouteSearchCriteria;
import subway.domain.line.LineRepository;
import subway.domain.station.Station;
import subway.domain.station.StationCost;
import subway.dto.RouteSearchResultDto;
import subway.init.GraphCreator;

public class RouteSearchService {
    private final GraphCreator graphCreator = new GraphCreator();

    public RouteSearchResultDto searchRoute(Station departure, Station arrival,
                                            RouteSearchCriteria routeSearchCriteria) {
        if (departure.equals(arrival)) {
            throw new IllegalArgumentException(SAME_DEPARTURE_AND_ARRIVAL_STATION.getValue(departure.getName()));
        }

        if (routeSearchCriteria.equals(RouteSearchCriteria.SHORTEST_DISTANCE)) {
            return searchRouteByShortestDistance(departure, arrival);
        }
        return searchRouteByMinimumArrivalTime(departure, arrival);
    }

    private RouteSearchResultDto searchRouteByShortestDistance(Station departure, Station arrival) {
        DijkstraShortestPath<Station, DefaultWeightedEdge> graphByShortestDistance =
                graphCreator.getGraphByShortestDistance();
        List<Station> stations = graphByShortestDistance.getPath(departure, arrival).getVertexList();

        return calculateRouteSearchResult(stations);
    }

    private RouteSearchResultDto searchRouteByMinimumArrivalTime(Station departure, Station arrival) {
        DijkstraShortestPath<Station, DefaultWeightedEdge> graphByMinimumArrivalTime
                = graphCreator.getGraphByMinimumArrivalTime();
        List<Station> stations = graphByMinimumArrivalTime.getPath(departure, arrival).getVertexList();

        return calculateRouteSearchResult(stations);
    }

    private List<StationCost> getStationCosts(List<Station> stations) {
        return IntStream.range(0, stations.size() - 1)
                .mapToObj(i -> {
                    Station curStation = stations.get(i);
                    Station nextStation = stations.get(i + 1);
                    return LineRepository.lines().stream()
                            .map(line -> line.findStationCost(curStation, nextStation))
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .findFirst()
                            .orElse(null);
                })
                .filter(Objects::nonNull)
                .toList();
    }

    private List<String> getStationNames(List<Station> stations) {
        return stations.stream()
                .map(Station::getName)
                .toList();
    }

    private int getTotalDistance(List<StationCost> stationCosts) {
        return stationCosts.stream()
                .mapToInt(StationCost::getDistance)
                .sum();
    }

    private int getTotalArrivalTime(List<StationCost> stationCosts) {
        return stationCosts.stream()
                .mapToInt(StationCost::getArrivalTime)
                .sum();
    }

    private RouteSearchResultDto calculateRouteSearchResult(List<Station> stations) {
        List<String> stationNames = getStationNames(stations);

        List<StationCost> stationCosts = getStationCosts(stations);
        int distance = getTotalDistance(stationCosts);
        int arrivalTime = getTotalArrivalTime(stationCosts);

        return new RouteSearchResultDto(stationNames, distance, arrivalTime);
    }
}

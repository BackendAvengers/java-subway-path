package subway.init;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.line.Line;
import subway.domain.line.LineRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;

public class GraphCreator {
    private final DijkstraShortestPath<Station, DefaultWeightedEdge> graphByShortestDistance;
    private final DijkstraShortestPath<Station, DefaultWeightedEdge> graphByMinimumArrivalTime;

    public GraphCreator() {
        this.graphByShortestDistance = makeShortestDistanceGraph();
        this.graphByMinimumArrivalTime = makeMinimumArrivalTimeGraph();
    }

    private static DijkstraShortestPath<Station, DefaultWeightedEdge> makeShortestDistanceGraph() {
        WeightedMultigraph<Station, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        addStation(graph);
        addStationDistanceWeight(graph);
        return new DijkstraShortestPath<>(graph);
    }

    private static void addStation(WeightedMultigraph<Station, DefaultWeightedEdge> graph) {
        for (Station station : StationRepository.stations()) {
            graph.addVertex(station);
        }
    }

    private static void addStationDistanceWeight(WeightedMultigraph<Station, DefaultWeightedEdge> graph) {
        for (Line line : LineRepository.lines()) {
            List<Station> stations = line.getAllStations();
            for (int i = 0; i < stations.size() - 1; i++) {
                Station curStation = stations.get(i);
                Station nextStation = stations.get(i + 1);
                graph.setEdgeWeight(graph.addEdge(curStation, nextStation),
                        line.findStationCost(curStation).getDistance());
            }
        }
    }

    private static void addStationArrivalTimeWeight(WeightedMultigraph<Station, DefaultWeightedEdge> graph) {
        for (Line line : LineRepository.lines()) {
            List<Station> stations = line.getAllStations();
            for (int i = 0; i < stations.size() - 1; i++) {
                Station curStation = stations.get(i);
                Station nextStation = stations.get(i + 1);
                graph.setEdgeWeight(graph.addEdge(curStation, nextStation),
                        line.findStationCost(curStation).getArrivalTime());
            }
        }
    }

    private DijkstraShortestPath<Station, DefaultWeightedEdge> makeMinimumArrivalTimeGraph() {
        WeightedMultigraph<Station, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        addStation(graph);
        addStationArrivalTimeWeight(graph);
        return new DijkstraShortestPath<>(graph);
    }

    public DijkstraShortestPath<Station, DefaultWeightedEdge> getGraphByShortestDistance() {
        return graphByShortestDistance;
    }

    public DijkstraShortestPath<Station, DefaultWeightedEdge> getGraphByMinimumArrivalTime() {
        return graphByMinimumArrivalTime;
    }
}

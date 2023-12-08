package subway.init;

import subway.domain.line.Line;
import subway.domain.line.LineRepository;
import subway.domain.station.Station;
import subway.domain.station.StationCost;
import subway.domain.station.StationRepository;

public class StationDataLoader {

    private StationDataLoader() {
    }

    public static void saveAllStations() {
        StationRepository.addStation(new Station("교대역"));
        StationRepository.addStation(new Station("강남역"));
        StationRepository.addStation(new Station("역삼역"));
        StationRepository.addStation(new Station("남부터미널역"));
        StationRepository.addStation(new Station("양재역"));
        StationRepository.addStation(new Station("매봉역"));
        StationRepository.addStation(new Station("양재시민의숲역"));
    }

    public static void saveAllLines() {
        LineRepository.addLine(getSubwayLine2());
        LineRepository.addLine(getSubwayLine3());
        LineRepository.addLine(getSubwayShinbundang());
    }

    private static Line getSubwayLine2() {
        Line line2 = new Line("2호선");
        line2.addStation(new Station("교대역"), new StationCost(2, 3));
        line2.addStation(new Station("강남역"), new StationCost(2, 3));
        line2.addStation(new Station("역삼역"), new StationCost(0, 0));

        return line2;
    }

    private static Line getSubwayLine3() {
        Line line3 = new Line("3호선");
        line3.addStation(new Station("교대역"), new StationCost(3, 2));
        line3.addStation(new Station("남부터미널역"), new StationCost(6, 5));
        line3.addStation(new Station("양재역"), new StationCost(1, 1));
        line3.addStation(new Station("매봉역"), new StationCost(0, 0));

        return line3;
    }

    private static Line getSubwayShinbundang() {
        Line shinbundangLine = new Line("신분당선");
        shinbundangLine.addStation(new Station("강남역"), new StationCost(2, 8));
        shinbundangLine.addStation(new Station("양재역"), new StationCost(10, 3));
        shinbundangLine.addStation(new Station("양재시민의숲역"), new StationCost(0, 0));
        return shinbundangLine;
    }
}

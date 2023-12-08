package subway.controller;

import static subway.exception.ErrorMessage.INVALID_FEATURE_SIGNAL_INPUT;
import static subway.exception.ErrorMessage.INVALID_ROUTE_SEARCH_CRITERIA_SIGNAL_INPUT;
import static subway.exception.ErrorMessage.NON_EXIST_STATION;

import subway.controller.constants.Feature;
import subway.controller.constants.RouteSearchCriteria;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private final StationRepository stationRepository;

    public MainController(InputView inputView, OutputView outputView, StationRepository stationRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.stationRepository = stationRepository;
    }

    public void run() {
        try {
            while (true) {
                Feature feature = selectFeatureOrQuit();
                if (feature.isQuit()) {
                    break;
                }

                RouteSearchCriteria routeSearchCriteria = selectRouteSearchCriteria();
                if (routeSearchCriteria.isBack()) {
                    continue;
                }

                Station departureStation = getDepartureStation();
                Station arrivalStation = getArrivalStation();


            }
        } catch (Exception e) {
            outputView.outputMessage(e.getMessage());
        }
    }

    private Feature selectFeatureOrQuit() {
        String signal = inputView.inputMainFeature();
        return Feature.findFeatureSignal(signal)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FEATURE_SIGNAL_INPUT.getValue(signal)));
    }

    private RouteSearchCriteria selectRouteSearchCriteria() {
        String signal = inputView.inputRouteSearchCriteria();
        return RouteSearchCriteria.findRouteSearchCriteria(signal)
                .orElseThrow(() -> new IllegalArgumentException(
                        INVALID_ROUTE_SEARCH_CRITERIA_SIGNAL_INPUT.getValue(signal)));
    }

    private Station getDepartureStation() {
        String departureStationName = inputView.inputDepartureStation();
        return stationRepository.findStation(departureStationName)
                .orElseThrow(() -> new IllegalArgumentException(NON_EXIST_STATION.getValue(departureStationName)));
    }

    private Station getArrivalStation() {
        String arrivalStationName = inputView.inputArrivalStation();
        return stationRepository.findStation(arrivalStationName)
                .orElseThrow(() -> new IllegalArgumentException(NON_EXIST_STATION.getValue(arrivalStationName)));
    }
}

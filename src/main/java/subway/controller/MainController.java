package subway.controller;

import static subway.exception.ErrorMessage.INVALID_ACTION_SIGNAL_INPUT;
import static subway.exception.ErrorMessage.INVALID_ROUTE_SEARCH_CRITERIA_SIGNAL_INPUT;
import static subway.exception.ErrorMessage.NON_EXIST_STATION;

import subway.controller.constants.Action;
import subway.controller.constants.RouteSearchCriteria;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.dto.RouteSearchResultDto;
import subway.service.RouteSearchService;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private final StationRepository stationRepository;
    private final RouteSearchService routeSearchService = new RouteSearchService();

    public MainController(InputView inputView, OutputView outputView, StationRepository stationRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.stationRepository = stationRepository;
    }

    public void run() {
        try {
            while (true) {
                Action action = selectAction();
                if (action.isQuit()) {
                    break;
                }

                searchRoute();
            }
        } catch (Exception e) {
            outputView.outputMessage(e.getMessage());
        }
    }

    private Action selectAction() {
        String signal = inputView.inputMainAction();
        return Action.findAction(signal)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ACTION_SIGNAL_INPUT.getValue(signal)));
    }

    private void searchRoute() {

        while (true) {
            try {
                RouteSearchCriteria routeSearchCriteria = selectRouteSearchCriteria();
                if (routeSearchCriteria.isBack()) {
                    break;
                }

                Station departure = getDepartureStation();
                Station arrival = getArrivalStation();
                showRoute(departure, arrival, routeSearchCriteria);
            } catch (IllegalArgumentException e) {
                outputView.outputMessage(e.getMessage());
            }
        }
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

    private void showRoute(Station departure, Station arrival, RouteSearchCriteria routeSearchCriteria) {
        RouteSearchResultDto routeSearchResultDto =
                routeSearchService.searchRoute(departure, arrival, routeSearchCriteria);
        outputView.outputRouteSearchResult(routeSearchResultDto);
    }
}

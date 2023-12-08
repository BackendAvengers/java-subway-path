package subway.controller;

import static subway.exception.ErrorMessage.INVALID_FEATURE_SIGNAL_INPUT;
import static subway.exception.ErrorMessage.INVALID_ROUTE_SEARCH_CRITERIA_SIGNAL_INPUT;

import subway.controller.constants.Feature;
import subway.controller.constants.RouteSearchCriteria;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
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
}

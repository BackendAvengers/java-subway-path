package subway.controller;

import static subway.exception.ErrorMessage.INVALID_FEATURE_SIGNAL_INPUT;

import subway.controller.constants.FeatureSignal;
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
                FeatureSignal featureSignal = selectFeatureOrQuit();
                if (featureSignal.isQuit()) {
                    break;
                }

            }
        } catch (Exception e) {
            outputView.outputMessage(e.getMessage());
        }
    }

    private FeatureSignal selectFeatureOrQuit() {
        String signal = inputView.inputMainFeature();
        return FeatureSignal.findFeatureSignal(signal)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FEATURE_SIGNAL_INPUT.getValue(signal)));
    }
}

package subway.controller.constants;

import java.util.Arrays;
import java.util.Optional;

public enum Feature {
    SEARCH_ROUTE("1"),
    QUIT("Q");

    private final String signal;

    Feature(String signal) {
        this.signal = signal;
    }

    public static Optional<Feature> findFeatureSignal(String input) {
        return Arrays.stream(values())
                .filter(feature -> feature.signal.equals(input))
                .findFirst();
    }

    public boolean isQuit() {
        return this.equals(QUIT);
    }
}
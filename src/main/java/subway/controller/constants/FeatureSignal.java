package subway.controller.constants;

import java.util.Arrays;
import java.util.Optional;

public enum FeatureSignal {
    SEARCH_ROUTE("1"),
    QUIT("Q");

    private final String value;

    FeatureSignal(String value) {
        this.value = value;
    }

    public static Optional<FeatureSignal> findFeatureSignal(String input) {
        return Arrays.stream(values())
                .filter(signal -> signal.value.equals(input))
                .findFirst();
    }

    public boolean isQuit() {
        return this.equals(QUIT);
    }
}
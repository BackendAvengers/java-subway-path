package subway.controller.constants;

import java.util.Arrays;
import java.util.Optional;

public enum Action {
    SEARCH_ROUTE("1"),
    QUIT("Q");

    private final String signal;

    Action(String signal) {
        this.signal = signal;
    }

    public static Optional<Action> findAction(String input) {
        return Arrays.stream(values())
                .filter(action -> action.signal.equals(input))
                .findFirst();
    }

    public boolean isQuit() {
        return this.equals(QUIT);
    }
}
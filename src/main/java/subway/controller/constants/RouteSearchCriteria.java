package subway.controller.constants;

import java.util.Arrays;
import java.util.Optional;

public enum RouteSearchCriteria {
    SHORTEST_DISTANCE("1"),
    MIN_ARRIVAL_TIME("2"),
    BACK("B");

    private final String signal;

    RouteSearchCriteria(String signal) {
        this.signal = signal;
    }

    public static Optional<RouteSearchCriteria> findRouteSearchCriteria(String input) {
        return Arrays.stream(values())
                .filter(criteria -> criteria.signal.equals(input))
                .findFirst();
    }

    public boolean isBack() {
        return this.equals(BACK);
    }
}

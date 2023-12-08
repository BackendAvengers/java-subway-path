package subway.exception;

public enum ErrorMessage {
    INVALID_FEATURE_SIGNAL_INPUT("해당 기능은 존재하지 않습니다."),
    INVALID_ROUTE_SEARCH_CRITERIA_SIGNAL_INPUT("해당 경로 기준은 존재하지 않습니다."),
    NON_EXIST_STATION("해당 역은 존재하지 않습니다."),
    SAME_DEPARTURE_AND_ARRIVAL_STATION("출발역과 도착역이 동일합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return PREFIX + value;
    }

    public String getValue(Object arg) {
        return getValue() + ": " + arg;
    }
}

package subway.exception;

public enum ErrorMessage {
    INVALID_FEATURE_SIGNAL_INPUT("해당 기능은 존재하지 않습니다.");

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

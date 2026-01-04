package oncall.exception;

public class OnCallException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public OnCallException(String msg) {
        super(ERROR_PREFIX + msg);
    }
}

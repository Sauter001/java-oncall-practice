package oncall.error;

public class OnCallException extends IllegalArgumentException {

    public static final String ERROR_PREFIX = "[ERROR] ";

    public OnCallException(String message) {
        super(ERROR_PREFIX + message);
    }
}

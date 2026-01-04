package oncall.exception;

public class InvalidInputException extends OnCallException {
    public InvalidInputException() {
        super("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
    }
}

package oncall.domain;

import oncall.error.InvalidInputException;

public record Month(int monthValue) {

    public static final int MIN_VAL = 1;
    public static final int MAX_VAL = 12;

    public Month {
        validateRange(monthValue);
    }

    private void validateRange(int monthValue) {
        if (monthValue < MIN_VAL || monthValue > MAX_VAL) {
            throw new InvalidInputException();
        }
    }
}

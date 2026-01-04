package oncall.domain;

import oncall.exception.InvalidInputException;

import java.util.Objects;

public class Staff {
    public static final int MAX_NAME_LEN = 5;
    private final String name;

    public Staff(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LEN) {
            throw new InvalidInputException();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Staff staff)) {
            return false;
        }
        return Objects.equals(name, staff.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

package oncall.domain;

import java.util.Objects;

public class Staff {
    private String name;

    public Staff(String name) {
        this.name = name;
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

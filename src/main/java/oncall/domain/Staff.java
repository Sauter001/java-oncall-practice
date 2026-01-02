package oncall.domain;

import oncall.error.OnCallException;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Staff {
    public static final int MIN_NAME_LENGTH = 5;
    private final String name;
    private final List<WorkingDate> workingDates;

    public Staff(String name) {
        validate(name);
        this.name = name;
        this.workingDates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void validate(String name) {
        if (name.length() < MIN_NAME_LENGTH) {
            throw new OnCallException("근무자 이름은 " + MIN_NAME_LENGTH + "이상이어야 합니다.");
        }
    }

    public void addWorkingDate(WorkingDate workingDate) {
        workingDates.add(workingDate);
    }

    public List<MonthDay> getWorkingDates() {
        List<MonthDay> sortedMonthDay = this.workingDates.stream().sorted().toList();
        return List.copyOf(sortedMonthDay);
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

package oncall.domain;

import java.time.MonthDay;

public record ScheduleDto(WorkingDate workingDate, String staffName) {
    public int month() {
        return workingDate.monthDay().getMonthValue();
    }

    public int day() {
        return workingDate.monthDay().getDayOfMonth();
    }

    public MonthDay monthDay() {
        return workingDate.monthDay();
    }

    public Weekday weekday() {
        return workingDate.weekday();
    }
}

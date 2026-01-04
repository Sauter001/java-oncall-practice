package oncall.domain.dto;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;

public record DateForm(Month month, DayOfWeek dayOfWeek) {
    public MonthDay createMonthDayFrom(int day) {
        return MonthDay.of(month, day);
    }
}

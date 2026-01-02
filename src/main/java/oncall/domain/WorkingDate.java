package oncall.domain;

import java.time.MonthDay;

public record WorkingDate(MonthDay monthDay, Weekday weekday) {
}

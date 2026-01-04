package oncall.domain.dto;

import java.time.DayOfWeek;
import java.time.Month;

public record DateForm(Month month, DayOfWeek dayOfWeek) {
}

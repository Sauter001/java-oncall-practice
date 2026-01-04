package oncall.util;

import oncall.exception.InvalidInputException;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.util.*;

public final class DateUtil {
    private static final Map<String, DayOfWeek> weekMap = new HashMap<>();
    private static final Map<DayOfWeek, String> weekNameMap = new HashMap<>();
    private static final Set<MonthDay> publicHolidays = Set.of(
            MonthDay.of(1, 1),
            MonthDay.of(3, 1),
            MonthDay.of(5, 5),
            MonthDay.of(6, 6),
            MonthDay.of(8, 15),
            MonthDay.of(10, 3),
            MonthDay.of(10, 9),
            MonthDay.of(12, 25)
    );

    private static final int DAY_OF_WEEK_LENGTH = 7;

    static {
        List<String> dayExpressions = List.of("월", "화", "수", "목", "금", "토", "일");
        List<DayOfWeek> dayOfWeeks = Arrays.asList(DayOfWeek.values());
        for (int i = 0; i < DAY_OF_WEEK_LENGTH; i++) {
            String dayExpression = dayExpressions.get(i);
            DayOfWeek dayOfWeek = dayOfWeeks.get(i);
            weekMap.put(dayExpression, dayOfWeek);
            weekNameMap.put(dayOfWeek, dayExpression);
        }
    }

    public static boolean isWeekend(DayOfWeek week) {
        return week.equals(DayOfWeek.SATURDAY) || week.equals(DayOfWeek.SUNDAY);
    }

    public static boolean isPublicHoliday(MonthDay monthDay, DayOfWeek dayOfWeek) {
        return !isWeekend(dayOfWeek) && publicHolidays.contains(monthDay);
    }

    public static DayOfWeek getDayOfWeekFrom(String name) {
        if (!weekMap.containsKey(name)) {
            throw new InvalidInputException();
        }

        return weekMap.get(name);
    }

    public static String getDayNameFrom(DayOfWeek dayOfWeek) {
        return weekNameMap.get(dayOfWeek);
    }
}

package oncall.util;

import oncall.exception.InvalidInputException;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DateUtil {
    private static final Map<String, DayOfWeek> weekMap = new HashMap<>();

    public static final int DAY_OF_WEEK_LENGTH = 7;

    static {
        List<String> dayExpressions = List.of("월", "화", "수", "목", "금", "토", "일");
        List<DayOfWeek> dayOfWeeks = Arrays.asList(DayOfWeek.values());
        for (int i = 0; i < DAY_OF_WEEK_LENGTH; i++) {
            weekMap.put(dayExpressions.get(i), dayOfWeeks.get(i));
        }
    }

    public static boolean isWeekend(DayOfWeek week) {
        return week.equals(DayOfWeek.SATURDAY) || week.equals(DayOfWeek.SUNDAY);
    }

    public static DayOfWeek getDayOfWeekFrom(String name) {
        if (!weekMap.containsKey(name)) {
            throw new InvalidInputException();
        }

        return weekMap.get(name);
    }
}

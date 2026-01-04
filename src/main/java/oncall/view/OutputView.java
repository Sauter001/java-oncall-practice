package oncall.view;

import oncall.domain.dto.ScheduleDto;
import oncall.util.DateUtil;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.util.List;

public class OutputView {
    private static final String EMPTY_CONTENT = "";

    public void displayError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void displaySchedules(List<ScheduleDto> scheduleDtos) {
        System.out.println();
        for (ScheduleDto dto : scheduleDtos) {
            System.out.printf("%d월 %d일 %s%s %s\n",
                    dto.month(),
                    dto.day(),
                    dto.dayName(),
                    getPublicHolidayContent(dto),
                    dto.staffName());
        }
    }

    private String getPublicHolidayContent(ScheduleDto dto) {
        MonthDay monthDay = MonthDay.of(dto.month(), dto.day());
        DayOfWeek dayOfWeek = DateUtil.getDayOfWeekFrom(dto.dayName());
        if (DateUtil.isPublicHoliday(monthDay, dayOfWeek)) {
            return "(휴일)";
        }

        return EMPTY_CONTENT;
    }
}

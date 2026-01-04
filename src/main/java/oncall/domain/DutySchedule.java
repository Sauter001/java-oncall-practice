package oncall.domain;

import oncall.domain.dto.ScheduleDto;
import oncall.util.DateUtil;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DutySchedule {
    private final Map<MonthDay, Duty> scheduleMap;

    public DutySchedule(Map<MonthDay, Duty> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    public List<ScheduleDto> toDtos() {
        Map<MonthDay, Duty> sortedMap = new TreeMap<>(scheduleMap);
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (MonthDay monthDay : sortedMap.keySet()) {
            Duty duty = scheduleMap.get(monthDay);
            ScheduleDto scheduleDto = makeScheduleDto(monthDay, duty);
            scheduleDtos.add(scheduleDto);
        }
        return scheduleDtos;
    }

    private static ScheduleDto makeScheduleDto(MonthDay monthDay, Duty duty) {
        return new ScheduleDto(
                monthDay.getMonthValue(),
                monthDay.getDayOfMonth(),
                DateUtil.getDayNameFrom(duty.getDayOfWeek()),
                duty.getStaffName()
        );
    }
}

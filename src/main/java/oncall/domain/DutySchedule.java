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
    private final Map<MonthDay, Duty> scheduleMap = new TreeMap<>();

    public void putSchedule(MonthDay monthDay, DayOfWeek dayOfWeek, Staff staff) {
        Duty duty = new Duty(dayOfWeek, staff);
        scheduleMap.put(monthDay, duty);
    }

    public List<ScheduleDto> toDtos() {
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (MonthDay monthDay : scheduleMap.keySet()) {
            Duty duty = scheduleMap.get(monthDay);
            ScheduleDto scheduleDto = new ScheduleDto(
                    monthDay.getMonthValue(),
                    monthDay.getDayOfMonth(),
                    DateUtil.getDayNameFrom(duty.getDayOfWeek()),
                    duty.getStaffName()
            );
            scheduleDtos.add(scheduleDto);
        }
        return scheduleDtos;
    }
}

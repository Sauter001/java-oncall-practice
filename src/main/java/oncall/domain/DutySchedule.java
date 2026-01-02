package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DutySchedule {
    private final Map<WorkingDate, Staff> schedule;

    public DutySchedule(Map<WorkingDate, Staff> schedule) {
        this.schedule = schedule;
    }

    public List<ScheduleDto> toScheduleDtos() {
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        Map<WorkingDate, Staff> sortedSchedule = new TreeMap<>(schedule);
        for (WorkingDate workingDate : sortedSchedule.keySet()) {
            Staff staff = sortedSchedule.get(workingDate);
            scheduleDtos.add(new ScheduleDto(workingDate, staff.getName()));
        }
        return scheduleDtos;
    }
}

package oncall.service;

import oncall.domain.*;
import oncall.error.OnCallException;

import java.util.Map;

public class ScheduleManager {
    private final WorkingDate workingDate;
    private final Staffs weekdaySchedule;
    private final Staffs holidaySchedule;

    public ScheduleManager(WorkingDate workingDate, Staffs weekdaySchedule, Staffs holidaySchedule) {
        this.workingDate = workingDate;
        validateStaffIntegrity(weekdaySchedule, holidaySchedule);
        this.weekdaySchedule = weekdaySchedule;
        this.holidaySchedule = holidaySchedule;
    }

    private void validateStaffIntegrity(Staffs weekdaySchedule, Staffs holidaySchedule) {
        StaffSet weekdayStaffSet = weekdaySchedule.toSet();
        StaffSet holidayStaffSet = holidaySchedule.toSet();
        for (Staff staff : weekdayStaffSet) {
            if (!holidayStaffSet.hasStaff(staff)) {
                throw new OnCallException("근무자가 평일, 휴일에 모두 할당되지 않았습니다.");
            }
        }
    }

    public DutySchedule scheduleDuty() {
        return new DutySchedule(Map.of());
    }
}

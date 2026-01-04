package oncall.service;

import oncall.domain.DutySchedule;
import oncall.domain.StaffList;
import oncall.domain.dto.DateForm;

public class DutyScheduler {
    private final DateForm dateForm;
    private final StaffList weekdayStaffList;
    private final StaffList holidayStaffList;

    public DutyScheduler(DateForm dateForm, StaffList weekdayStaffList, StaffList holidayStaffList) {
        this.dateForm = dateForm;
        this.weekdayStaffList = weekdayStaffList;
        this.holidayStaffList = holidayStaffList;
    }

    public DutySchedule schedule() {

        return null;
    }
}

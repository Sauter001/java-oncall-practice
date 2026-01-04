package oncall.service;

import oncall.domain.Duty;
import oncall.domain.DutySchedule;
import oncall.domain.Staff;
import oncall.domain.StaffList;
import oncall.domain.dto.DateForm;
import oncall.domain.dto.ScheduleDto;
import oncall.util.DateUtil;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DutyScheduler {
    private static final int FIRST_DAY = 1;

    private final DateForm dateForm;
    private final StaffManager weekdayManager;
    private final StaffManager holidayManager;
    private final Map<MonthDay, Duty> scheduleMap = new TreeMap<>();

    public DutyScheduler(DateForm dateForm, StaffList weekdayStaffList, StaffList holidayStaffList) {
        this.dateForm = dateForm;
        this.weekdayManager = new StaffManager(weekdayStaffList);
        this.holidayManager = new StaffManager(holidayStaffList);
    }

    public List<ScheduleDto> schedule() {
        DutySchedule dutySchedule = putDutiesOfStaffs();
        return dutySchedule.toDtos();
    }

    private DutySchedule putDutiesOfStaffs() {
        Month currentMonth = dateForm.month();
        boolean leapYear = false;

        for (int day = FIRST_DAY; day <= currentMonth.length(leapYear); day++) {
            if (isFirst(day)) {
                putFirstDuty();
                continue;
            }
            putNextDuty(day);
        }
        return new DutySchedule(this.scheduleMap);
    }

    private void putNextDuty(int currentDay) {
        Staff staff = pickStaff(currentDay);
        DayOfWeek currentDayOfWeek = dateForm.dayOfWeek().plus(currentDay - 1);
        putSchedule(dateForm.createMonthDayFrom(currentDay), currentDayOfWeek, staff);
    }

    private void putFirstDuty() {
        MonthDay monthDay = dateForm.createMonthDayFrom(FIRST_DAY);
        DayOfWeek dayOfWeek = dateForm.dayOfWeek();
        if (DateUtil.isHoliday(monthDay, dayOfWeek)) {
            putSchedule(monthDay, dayOfWeek, holidayManager.popFirst());
            return;
        }
        putSchedule(monthDay, dayOfWeek, weekdayManager.popFirst());
    }

    private boolean isFirst(int index) {
        return index == FIRST_DAY;
    }

    private Staff pickStaff(int currentDay) {
        MonthDay currentMonthDay = dateForm.createMonthDayFrom(currentDay);
        MonthDay prevMonthDay = dateForm.createMonthDayFrom(currentDay - 1);
        DayOfWeek currentDayOfWeek = dateForm.dayOfWeek().plus(currentDay - 1);
        Duty prevDuty = this.scheduleMap.get(prevMonthDay);
        boolean isHoliday = DateUtil.isHoliday(currentMonthDay, currentDayOfWeek);

        if (isHoliday) {
            return determineNextWorker(this.holidayManager, prevDuty);
        }
        return determineNextWorker(this.weekdayManager, prevDuty);
    }

    private Staff determineNextWorker(StaffManager manager, Duty prevDuty) {
        Staff staff = manager.peekFirst();
        if (prevDuty.hasSameStaff(staff)) {
            return manager.popNext();
        }
        return manager.popFirst();
    }

    private void putSchedule(MonthDay monthDay, DayOfWeek dayOfWeek, Staff staff) {
        Duty duty = new Duty(dayOfWeek, staff);
        this.scheduleMap.put(monthDay, duty);
    }
}

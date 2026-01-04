package oncall.controller;

import oncall.domain.DutySchedule;
import oncall.domain.Staff;
import oncall.domain.StaffList;
import oncall.domain.StaffSet;
import oncall.domain.dto.DateForm;
import oncall.exception.InvalidInputException;
import oncall.exception.OnCallException;
import oncall.service.DutyScheduler;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.function.Supplier;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        DateForm dateForm = inputView.readDateForm();
        retry(() -> {
            StaffList weekdayStaffList = inputView.readWeekdayStaffs();
            StaffList holidayStaffList = inputView.readHolidayStaffs();
            checkStaffListIntegrity(weekdayStaffList, holidayStaffList);
            processScheduling(dateForm, weekdayStaffList, holidayStaffList);
        });
    }

    private void processScheduling(DateForm dateForm, StaffList weekdayStaffList, StaffList holidayStaffList) {
        DutyScheduler dutyScheduler = new DutyScheduler(dateForm, weekdayStaffList, holidayStaffList);
        DutySchedule schedule = dutyScheduler.schedule();
        outputView.displaySchedules(schedule.toDtos());
    }

    private void checkStaffListIntegrity(StaffList weekdayStaffList, StaffList holidayStaffList) {
        StaffSet staffSet = weekdayStaffList.toSet();
        for (Staff staff : staffSet) {
            if (!holidayStaffList.contains(staff)) {
                throw new InvalidInputException();
            }
        }
    }

    private void retry(Runnable task) {
        while (true) {
            try {
                task.run();
                return;
            } catch (OnCallException | IllegalStateException e) {
                outputView.displayError(e);
            }
        }
    }

    private <T> T retry(Supplier<T> task) {
        while (true) {
            try {
                return task.get();
            } catch (OnCallException | IllegalStateException e) {
                outputView.displayError(e);
            }
        }
    }
}

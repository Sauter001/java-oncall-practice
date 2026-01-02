package oncall.controller;

import oncall.domain.DutySchedule;
import oncall.domain.Staffs;
import oncall.domain.WorkingDate;
import oncall.error.OnCallException;
import oncall.service.ScheduleManager;
import oncall.view.ConsoleView;

import java.util.function.Supplier;

public class MainController {
    private final ConsoleView view;

    public MainController(ConsoleView view) {
        this.view = view;
    }

    public void run() {
        WorkingDate workingDate = view.readWorkingDate();
        DutySchedule dutySchedule = retry(() -> {
            Staffs weekdaySchedule = view.readWeekdayStaffs();
            Staffs holidaySchedule = view.readHolidayStaffs();
            ScheduleManager manager = new ScheduleManager(workingDate, weekdaySchedule, holidaySchedule);
            return manager.scheduleDuty();
        });
        view.displaySchedule(dutySchedule.toScheduleDtos());
    }

    private void retry(Runnable task) {
        while (true) {
            try {
                task.run();
                return;
            } catch (OnCallException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private <T> T retry(Supplier<T> task) {
        while (true) {
            try {
                return task.get();
            } catch (OnCallException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

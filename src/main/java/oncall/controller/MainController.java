package oncall.controller;

import oncall.domain.WorkingDate;
import oncall.error.OnCallException;
import oncall.view.ConsoleView;

import java.util.function.Supplier;

public class MainController {
    private final ConsoleView view;

    public MainController(ConsoleView view) {
        this.view = view;
    }

    public void run() {
        WorkingDate workingDate = view.readWorkingDate();

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

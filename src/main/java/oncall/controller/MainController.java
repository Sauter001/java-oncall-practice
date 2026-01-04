package oncall.controller;

import oncall.domain.dto.DateForm;
import oncall.exception.OnCallException;
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

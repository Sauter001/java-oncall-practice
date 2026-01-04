package oncall;

import oncall.controller.MainController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        MainController mainController = new MainController(inputView, outputView);
        mainController.run();
    }
}

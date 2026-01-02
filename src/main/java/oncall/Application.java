package oncall;

import oncall.controller.MainController;
import oncall.view.ConsoleView;

public class Application {
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView();
        MainController mainController = new MainController(consoleView);

        mainController.run();
    }
}

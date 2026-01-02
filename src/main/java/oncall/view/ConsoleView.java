package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.domain.WorkingDate;
import oncall.error.OnCallException;
import oncall.parser.Parser;
import oncall.parser.WorkingDateParser;

public class ConsoleView {
    public WorkingDate readWorkingDate() {
        String prompt = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
        return readWithRetry(prompt, new WorkingDateParser());
    }

    private <T> T readWithRetry(String prompt, Parser<T> parser) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = Console.readLine();
                return parser.parse(input);
            } catch (OnCallException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }
}

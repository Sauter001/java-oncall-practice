package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.domain.dto.DateForm;
import oncall.exception.OnCallException;
import oncall.parser.DateFormParser;
import oncall.parser.Parser;

public class InputView {
    public DateForm readDateForm() {
        String prompt = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
        return readWithRetry(prompt, new DateFormParser());
    }

    private <T> T readWithRetry(String prompt, Parser<T> parser) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = Console.readLine();
                return parser.parse(input);
            } catch (OnCallException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

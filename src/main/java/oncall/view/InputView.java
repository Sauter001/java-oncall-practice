package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.domain.StaffList;
import oncall.domain.dto.DateForm;
import oncall.exception.OnCallException;
import oncall.parser.DateFormParser;
import oncall.parser.Parser;
import oncall.parser.StaffListParser;

public class InputView {
    public DateForm readDateForm() {
        String prompt = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
        return readWithRetry(prompt, new DateFormParser());
    }

    public StaffList readWeekdayStaffs() {
        return readStaffs("평일");
    }

    public StaffList readHolidayStaffs() {
        return readStaffs("휴일");
    }

    private StaffList readStaffs(String dayType) {
        String prompt = dayType + " 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
        return readInput(prompt, new StaffListParser());
    }

    private <T> T readWithRetry(String prompt, Parser<T> parser) {
        while (true) {
            try {
                return readInput(prompt, parser);
            } catch (OnCallException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static <T> T readInput(String prompt, Parser<T> parser) {
        System.out.print(prompt);
        String input = Console.readLine();
        return parser.parse(input);
    }
}

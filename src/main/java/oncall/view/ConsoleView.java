package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.domain.ScheduleDto;
import oncall.domain.Staffs;
import oncall.domain.WorkingDate;
import oncall.error.OnCallException;
import oncall.parser.Parser;
import oncall.parser.StaffsParser;
import oncall.parser.WorkingDateParser;
import oncall.util.DateUtil;

import java.time.MonthDay;
import java.util.List;

public class ConsoleView {

    private static final String EMPTY_STRING = "";

    public WorkingDate readWorkingDate() {
        String prompt = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
        return readWithRetry(prompt, new WorkingDateParser());
    }

    public Staffs readWeekdayStaffs() {
        return readStaffs("평일");
    }

    public Staffs readHolidayStaffs() {
        return readStaffs("휴일");
    }

    public void displaySchedule(List<ScheduleDto> scheduleDtos) {
        for (ScheduleDto schedule : scheduleDtos) {
            System.out.printf("%d월 %d일 %s%s %s\n", 
                    schedule.month(),
                    schedule.day(),
                    schedule.weekday().getName(),
                    getHolidayContent(schedule.monthDay()),
                    schedule.staffName());
        }
    }

    private String getHolidayContent(MonthDay monthDay) {
        if (DateUtil.isHoliday(monthDay)) {
            return "(휴일)";
        }
        return EMPTY_STRING;
    }

    private Staffs readStaffs(String dayType) {
        String prompt = String.format("%s 비상 근무 순번대로 사원 닉네임을 입력하세요> ", dayType);
        return readWithRetry(prompt, new StaffsParser());
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

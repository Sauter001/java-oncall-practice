package oncall.parser;

import oncall.domain.dto.DateForm;
import oncall.exception.InvalidInputException;
import oncall.util.DateUtil;

import java.time.DateTimeException;
import java.time.Month;
import java.util.List;
import java.util.stream.Stream;

public class DateFormParser implements Parser<DateForm> {
    private static final String DELIMITER = ",";
    private static final int FORM_LENGTH = 2;

    @Override
    public DateForm parse(String input) {
        List<String> tokens = Stream.of(input.split(DELIMITER))
                .map(String::strip)
                .toList();
        if (tokens.size() != FORM_LENGTH) {
            throw new InvalidInputException();
        }

        return createDateForm(tokens);
    }

    private static DateForm createDateForm(List<String> tokens) {
        try {
            int month = Integer.parseInt(tokens.get(0));
            String dayExpr = tokens.get(1);
            return new DateForm(Month.of(month), DateUtil.getDayOfWeekFrom(dayExpr));
        } catch (DateTimeException | NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}

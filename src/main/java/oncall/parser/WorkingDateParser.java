package oncall.parser;

import oncall.domain.Month;
import oncall.domain.Weekday;
import oncall.domain.WorkingDate;
import oncall.error.InvalidInputException;
import oncall.error.OnCallException;

import java.util.List;
import java.util.stream.Stream;

public class WorkingDateParser implements Parser<WorkingDate> {

    private static final int TOKEN_LENGTH = 2;

    @Override
    public WorkingDate parse(String input) {
        try {
            List<String> tokens = Stream.of(input.split(","))
                    .map(String::strip)
                    .toList();
            validateTokenLength(tokens);
            return assembleTokens(tokens);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException();
        }
    }

    private static WorkingDate assembleTokens(List<String> tokens) {
        int month = Integer.parseInt(tokens.get(0));
        Weekday weekday = Weekday.from(tokens.get(1));
        return new WorkingDate(new Month(month), weekday);
    }

    private void validateTokenLength(List<String> tokens) {
        if (tokens.size() != TOKEN_LENGTH) {
            throw new InvalidInputException();
        }
    }
}

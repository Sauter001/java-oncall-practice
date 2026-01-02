package oncall.parser;

import oncall.domain.Staff;
import oncall.domain.Staffs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StaffsParser implements Parser<Staffs> {

    private static final String DELIMITER = ",";

    @Override
    public Staffs parse(String input) {
        List<String> staffNames = Stream.of(input.split(DELIMITER))
                .map(String::strip)
                .toList();
        return new Staffs(staffNames.stream().map(Staff::new).toList());
    }
}

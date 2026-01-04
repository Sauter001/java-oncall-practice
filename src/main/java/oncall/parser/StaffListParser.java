package oncall.parser;

import oncall.domain.Staff;
import oncall.domain.StaffList;

import java.util.List;
import java.util.stream.Stream;

public class StaffListParser implements Parser<StaffList> {
    private static final String DELIMITER = ",";

    @Override
    public StaffList parse(String input) {
        List<String> tokens = Stream.of(input.split(DELIMITER))
                .map(String::strip)
                .toList();
        List<Staff> staffList = tokens.stream().map(Staff::new).toList();
        return new StaffList(staffList);
    }
}

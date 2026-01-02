package oncall.domain;

import oncall.error.OnCallException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Staffs implements Iterable<Staff> {
    private static final int MIN_MEMBERS = 5;
    private static final int MAX_MEMBERS = 35;
    private final List<Staff> staffs;

    public Staffs(List<Staff> staffs) {
        validateStaffs(staffs);
        this.staffs = staffs;
    }

    private void validateStaffs(List<Staff> staffs) {
        validateIfNameDuplicated(staffs);
        validateNumOfMembers(staffs);
    }

    private void validateIfNameDuplicated(List<Staff> staffs) {
        Map<Staff, Integer> staffCounter = new HashMap<>();
        for (Staff staff : staffs) {
            staffCounter.put(staff, staffCounter.getOrDefault(staff, 0) + 1);
        }

        boolean existDuplicatedName = staffCounter.entrySet().stream()
                .anyMatch(entry -> entry.getValue() > 1);
        if (existDuplicatedName) {
            throw new OnCallException("중복되는 이름의 근무자가 존재합니다.");
        }
    }

    private void validateNumOfMembers(List<Staff> staffs) {
        int size = staffs.size();
        if (size < MIN_MEMBERS || size > MAX_MEMBERS) {
            throw new OnCallException(String.format("근무자 수는 %d명 이상, %d명 이하여야 합니다.", MIN_MEMBERS, MAX_MEMBERS));
        }
    }

    @Override
    public Iterator<Staff> iterator() {
        return this.staffs.iterator();
    }

    public StaffSet toSet() {
        return new StaffSet(this.staffs);
    }
}

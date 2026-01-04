package oncall.domain;

import oncall.exception.InvalidInputException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class StaffList implements Iterable<Staff> {
    private static final int MAX_STAFF_LENGTH = 35;
    private static final int MIN_STAFF_LENGTH = 5;
    private final List<Staff> staffList;

    public StaffList(List<Staff> staffList) {
        validate(staffList);
        this.staffList = staffList;
    }

    private void validate(List<Staff> staffList) {
        validateDuplicatedName(staffList);
        validateRange(staffList);
    }

    private void validateRange(List<Staff> staffList) {
        int size = staffList.size();
        if (size < MIN_STAFF_LENGTH || size > MAX_STAFF_LENGTH) {
            throw new InvalidInputException();
        }
    }

    private void validateDuplicatedName(List<Staff> staffList) {
        Set<Staff> staffSet = new HashSet<>(staffList);
        if (staffSet.size() != staffList.size()) {
            throw new InvalidInputException();
        }
    }

    public StaffSet toSet() {
        Set<Staff> staffSet = new HashSet<>(staffList);
        return new StaffSet(staffSet);
    }

    public boolean contains(Staff staff) {
        return this.staffList.contains(staff);
    }

    @Override
    public Iterator<Staff> iterator() {
        return this.staffList.iterator();
    }

    public List<Staff> getStaffList() {
        return List.copyOf(this.staffList);
    }
}

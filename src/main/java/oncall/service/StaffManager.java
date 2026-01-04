package oncall.service;

import oncall.domain.Staff;
import oncall.domain.StaffList;

import java.util.ArrayList;
import java.util.List;

public class StaffManager {
    private final List<Staff> originalStaffList;
    private List<Staff> copiedStaffList;

    public StaffManager(StaffList staffList) {
        this.originalStaffList = staffList.getStaffList();
        this.copiedStaffList = new ArrayList<>(this.originalStaffList);
    }

    public boolean isEmpty() {
        return this.copiedStaffList.isEmpty();
    }

    public boolean hasOneStaff() {
        return this.copiedStaffList.size() == 1;
    }

    public Staff peekFirst() {
        if (isEmpty()) {
            refill();
        }

        return this.copiedStaffList.get(0);
    }

    public Staff popFirst() {
        if (isEmpty()) {
            refill();
        }

        Staff staff = this.copiedStaffList.get(0);
        this.copiedStaffList.remove(0);
        return staff;
    }

    public Staff peekNext() {
        if (hasOneStaff()) {
            refill();
        }

        return this.copiedStaffList.get(1);
    }

    public Staff popNext() {
        if (hasOneStaff()) {
            refill();
        }

        Staff staff = this.copiedStaffList.get(1);
        this.copiedStaffList.remove(1);
        return staff;
    }

    private void refill() {
        this.copiedStaffList.addAll(this.originalStaffList);
    }
}

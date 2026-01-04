package oncall.domain;

import java.time.DayOfWeek;

public class Duty {
    private final DayOfWeek dayOfWeek;
    private final Staff staff;

    public Duty(DayOfWeek dayOfWeek, Staff staff) {
        this.dayOfWeek = dayOfWeek;
        this.staff = staff;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

   public boolean hasSameStaff(Staff staff) {
        return this.staff.equals(staff);
   }

    public String getStaffName() {
        return this.staff.getName();
    }
}

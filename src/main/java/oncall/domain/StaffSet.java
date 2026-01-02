package oncall.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StaffSet implements Iterable<Staff> {
    private final Set<Staff> staffSet;

    public StaffSet(Collection<Staff> staffIterable) {
        this.staffSet = new HashSet<>(staffIterable);
    }

    @Override
    public Iterator<Staff> iterator() {
        return staffSet.iterator();
    }

    public boolean hasStaff(Staff staff) {
        return this.staffSet.contains(staff);
    }
}

package oncall.domain;

import java.util.Iterator;
import java.util.Set;

public class StaffSet implements Iterable<Staff>{
    private final Set<Staff> staffSet;

    public StaffSet(Set<Staff> staffSet) {
        this.staffSet = staffSet;
    }

    public int size() {
        return this.staffSet.size();
    }

    @Override
    public Iterator<Staff> iterator() {
        return this.staffSet.iterator();
    }
}

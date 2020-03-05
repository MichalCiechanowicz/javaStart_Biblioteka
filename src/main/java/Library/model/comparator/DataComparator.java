package Library.model.comparator;

import Library.model.Publication;

import java.time.Year;
import java.util.Comparator;

public class DataComparator implements Comparator<Publication> {
    @Override
    public int compare(Publication p1, Publication p2) {
        if (p1 == null && p2 == null) {
            return 0;
        } else if (p1 == null) {
            return 1;
        } else if (p2 == null) {
            return -1;
        }
        Year i1 = p1.getDataWydania();
        Year i2 = p2.getDataWydania();

        return -i1.compareTo(i2);
    }
}

package org.example.domain.comparator;

import java.time.ZonedDateTime;
import java.util.Comparator;

public class ZonedDateTimeDateOnlyComparator implements Comparator<ZonedDateTime> {
    @Override
    public int compare(ZonedDateTime o1, ZonedDateTime o2) {
        int y1 = o1.getYear();
        int y2 = o2.getYear();
        int m1 = o1.getMonthValue();
        int m2 = o2.getMonthValue();
        int d1 = o1.getDayOfMonth();
        int d2 = o2.getDayOfMonth();
        if(y1 == y2) {
            if(m1 == m2) {
                if (d1 == d2) {
                    return 0;
                }
                return d1-d2;
            }
            return m1-m2;
        }
        return y1-y2;
    }
}

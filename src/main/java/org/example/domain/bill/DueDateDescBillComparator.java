package org.example.domain.bill;

import java.util.Comparator;

public class DueDateDescBillComparator implements Comparator<BillEntity> {
    @Override
    public int compare(BillEntity o1, BillEntity o2) {
        return o1.getDueDate().compareTo(o2.getDueDate());
    }
}

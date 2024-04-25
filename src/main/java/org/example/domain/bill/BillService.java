package org.example.domain.bill;

import java.util.List;

public interface BillService {
    void addBill(BillEntity bill);
    boolean deleteBill(long billId);
    boolean updateBill(long billId, BillEntity bill);
    List<BillEntity> findBillSortDueDateDesc(long clientId, String provider);
    List<BillEntity> findBillByClientIdSortDueDateDesc(long clientId);
}

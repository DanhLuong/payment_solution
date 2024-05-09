package org.example.domain.service;

import org.example.domain.entity.BillEntity;

import java.util.List;

public interface BillService {
    void addBill(BillEntity bill);
    boolean deleteBill(long billId);
    boolean updateBill(long billId, BillEntity bill);
    List<BillEntity> findBillSortDueDateDesc(long clientId, String provider);
    List<BillEntity> findBillByClientIdSortDueDateDesc(long clientId);
    List<BillEntity> findByClientIdSortByDueDateDesc(long clientId, boolean isPaid);
    List<BillEntity> findByClientIdAndProviderSortByDueDateDesc(long clientId, String provider, boolean isPaid);
}

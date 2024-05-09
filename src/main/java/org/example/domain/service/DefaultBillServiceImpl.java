package org.example.domain.service;

import org.example.domain.DAO.BillDAO;
import org.example.domain.entity.BillEntity;

import java.util.List;
//All method is in transaction in case real DB (wrap by a transactional proxy)
public class DefaultBillServiceImpl implements BillService{
    private final BillDAO billDAO;
    public DefaultBillServiceImpl(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    @Override
    public void addBill(BillEntity bill) {
        billDAO.create(bill);
    }

    @Override
    public boolean deleteBill(long billId) {
        return billDAO.delete(billId);
    }

    @Override
    public boolean updateBill(long billId, BillEntity bill) {
        return billDAO.update(billId, bill);
    }

    @Override
    public List<BillEntity> findBillSortDueDateDesc(long clientId, String provider) {
        return billDAO.findByClientIdAndProviderSortByDueDateDesc(clientId, provider);
    }

    @Override
    public List<BillEntity> findBillByClientIdSortDueDateDesc(long clientId) {
        return billDAO.findByClientIdSortByDueDateDesc(clientId);
    }

    @Override
    public List<BillEntity> findByClientIdSortByDueDateDesc(long clientId, boolean isPaid) {
        return billDAO.findByClientIdSortByDueDateDesc(clientId, isPaid);
    }

    @Override
    public List<BillEntity> findByClientIdAndProviderSortByDueDateDesc(long clientId, String provider, boolean isPaid) {
        return billDAO.findByClientIdAndProviderSortByDueDateDesc(clientId, provider, isPaid);
    }
}

package org.example.domain.bill;

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
}

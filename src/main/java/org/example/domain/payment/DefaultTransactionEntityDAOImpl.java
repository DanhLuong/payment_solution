package org.example.domain.payment;

import org.example.domain.common.DefaultLongIDEntityDAO;

import java.util.ArrayList;
import java.util.List;

public class DefaultTransactionEntityDAOImpl extends DefaultLongIDEntityDAO<TransactionEntity> implements TransactionDAO {

    @Override
    protected void initSampleData() {
    }

    @Override
    public List<TransactionEntity> findByClientIdSortByPayDateDesc(long clientId) {
        List<TransactionEntity> result = new ArrayList<>();
        for(TransactionEntity tran : data.values()) {
            if(tran.getClientId() == clientId) {
                result.add(tran);
            }
        }
        result.sort(new PayDateDescTransactionComparator());
        return result;
    }

    @Override
    public TransactionEntity findByClientIdAndBillId(long clientId, long billId) {
        for(TransactionEntity tran : data.values()) {
            if(tran.getClientId() == clientId && tran.getBillId() == billId) {
                return tran;
            }
        }
        return null;
    }
}

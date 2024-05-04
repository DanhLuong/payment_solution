package org.example.domain.payment;

import org.example.exception.BusinessException;

import java.util.List;

public interface TransactionService {
    void payBillWithBillId(long clientId, long billId) throws BusinessException;
    void payBillWithBillIds(long clientId, List<Long> billIds);
    List<TransactionEntity> findTranByClientIdSortByPayDateDesc(long clientId);
    List<TransactionEntity> findTranByClientIdAndProviderSortByPayDateDesc(long clientId, String provider);
}

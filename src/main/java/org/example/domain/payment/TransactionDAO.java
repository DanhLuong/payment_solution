package org.example.domain.payment;

import org.example.domain.common.EntityDAO;

import java.util.List;

public interface TransactionDAO extends EntityDAO<TransactionEntity, Long> {
    List<TransactionEntity> findByClientIdSortByPayDateDesc(long clientId);
    TransactionEntity findByClientIdAndBillId(long clientId, long billId);
}

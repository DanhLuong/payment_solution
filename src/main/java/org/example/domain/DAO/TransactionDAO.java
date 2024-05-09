package org.example.domain.DAO;

import org.example.domain.entity.TransactionEntity;

import java.util.List;

public interface TransactionDAO extends EntityDAO<TransactionEntity, Long> {
    List<TransactionEntity> findByClientIdSortByPayDateDesc(long clientId);
    TransactionEntity findByClientIdAndBillId(long clientId, long billId);
}

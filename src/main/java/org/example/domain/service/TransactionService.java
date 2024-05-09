package org.example.domain.service;

import org.example.domain.entity.TransactionEntity;
import org.example.domain.entity.TransactionJoinBillTemplate;
import org.example.domain.exception.BusinessException;

import java.util.List;

public interface TransactionService {
    void payBillWithBillId(long clientId, long billId) throws BusinessException;
    void payBillWithBillIds(long clientId, List<Long> billIds) throws BusinessException;
    List<TransactionJoinBillTemplate> findTranByClientIdSortByPayDateDesc(long clientId);
    List<TransactionEntity> findTranByClientIdAndProviderSortByPayDateDesc(long clientId, String provider);
}

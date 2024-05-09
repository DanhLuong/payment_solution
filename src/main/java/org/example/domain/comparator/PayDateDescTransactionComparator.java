package org.example.domain.comparator;

import org.example.domain.entity.TransactionEntity;

import java.util.Comparator;

public class PayDateDescTransactionComparator implements Comparator<TransactionEntity> {
    @Override
    public int compare(TransactionEntity o1, TransactionEntity o2) {
        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
    }
}

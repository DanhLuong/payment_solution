package org.example.domain.entity;

import java.math.BigDecimal;

public class TransactionEntity extends BaseEntity<Long> {
    private long clientId;
    private long billId;
    private BigDecimal amount;

    public TransactionEntity(long clientId, long billId, BigDecimal amount) {
        this.clientId = clientId;
        this.billId = billId;
        this.amount = amount;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

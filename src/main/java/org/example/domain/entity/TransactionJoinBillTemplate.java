package org.example.domain.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;

public class TransactionJoinBillTemplate {
    //bill part
    private String provider;
    private String serviceType;
    private boolean isPaid;
    private Date dueDate;

    //transaction part
    private long id;
    private long clientId;
    private long billId;
    private BigDecimal amount;
    private ZonedDateTime createdAt;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

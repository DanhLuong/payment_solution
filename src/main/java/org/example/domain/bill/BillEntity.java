package org.example.domain.bill;

import org.example.domain.common.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class BillEntity extends BaseEntity<Long> {
    private long clientId;
    private String provider;
    private BigDecimal amount;
    private String serviceType;
    private boolean isPaid;
    private Date dueDate;

    public BillEntity(long clientId, String provider, BigDecimal amount, String serviceType, boolean isPaid, Date dueDate) {
        this.clientId = clientId;
        this.provider = provider;
        this.amount = amount;
        this.serviceType = serviceType;
        this.isPaid = isPaid;
        this.dueDate = dueDate;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public boolean isPaid() {
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
}

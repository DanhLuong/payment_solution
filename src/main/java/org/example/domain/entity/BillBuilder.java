package org.example.domain.entity;


import java.math.BigDecimal;
import java.util.Date;

public class BillBuilder {
    private long clientId;
    private String provider="_";
    private BigDecimal amount=BigDecimal.ZERO;
    private String serviceType = "_";
    private boolean isPaid = false;
    private Date dueDate = null;

    public BillBuilder() {
    }

    public BillBuilder forClientId(long clientId) {
        this.clientId= clientId;
        return this;
    }

    public BillBuilder provider(String provider) {
        this.provider = provider;
        return this;
    }

    public BillBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BillBuilder serviceType(String serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public BillBuilder dueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public BillEntity build() {
        if(this.clientId == 0L) {
            throw new IllegalArgumentException("client ID is mandatory for each bill");
        }
        if(this.provider.isEmpty()) {
            throw new IllegalArgumentException("provider is mandatory for each bill");
        }
        if(this.dueDate == null) {
            throw new IllegalArgumentException("due date is mandatory for each bill");
        }
        return new BillEntity(clientId, provider, amount, serviceType, isPaid, dueDate);
    }
}


package org.example.domain.entity;

public class ClientBillIDTemplate {
    private Long clientID;
    private Long billID;

    public ClientBillIDTemplate(Long clientID, Long billID) {
        this.clientID = clientID;
        this.billID = billID;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public Long getBillID() {
        return billID;
    }

    public void setBillID(Long billID) {
        this.billID = billID;
    }
}

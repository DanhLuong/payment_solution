package org.example.domain.entity;

import java.time.ZonedDateTime;

public class ScheduleTransactionEntity extends BaseEntity<Long> {
    private Long clientID;
    private Long billID;
    private ZonedDateTime scheduleDate;
    private boolean done;

    public ScheduleTransactionEntity(Long clientID, Long billID, ZonedDateTime scheduleTime) {
        this.clientID = clientID;
        this.billID = billID;
        this.scheduleDate = scheduleTime;
        this.done = false;
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

    public ZonedDateTime getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(ZonedDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

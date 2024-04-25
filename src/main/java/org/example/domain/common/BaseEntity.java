package org.example.domain.common;

import java.sql.Date;
import java.time.ZonedDateTime;

public abstract class BaseEntity implements SoftDeletable{
    protected ZonedDateTime createdAt;
    protected ZonedDateTime modifiedAt;
    private boolean deleted = false;
    @Override
    public boolean isDeleted() {
        return deleted;
    }
    @Override
    public void setDeleted() {
        this.deleted = true;
    }
    @Override
    public void recoverDeleted(){
        this.deleted = false;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(ZonedDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

}

package org.example.domain.entity;

import java.time.ZonedDateTime;

public abstract class BaseEntity<ID> implements SoftDeletable{
    protected ID id;
    protected ZonedDateTime createdAt;
    protected ZonedDateTime modifiedAt;
    private boolean deleted = false;
    public ID getId(){return this.id;}
    public void setId(ID id){this.id = id;}
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

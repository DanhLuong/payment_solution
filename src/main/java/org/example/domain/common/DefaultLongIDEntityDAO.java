package org.example.domain.common;

public abstract class DefaultLongIDEntityDAO<T extends BaseEntity<Long>> extends EntityBaseDAODemo<T, Long> {
    protected void createBeginID() {
        this.currentID = 0L;
    }

    protected Long getNextID() {
        this.currentID++;
        return this.currentID;
    }
}

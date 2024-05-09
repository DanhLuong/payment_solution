package org.example.domain.DAO;

import org.example.domain.entity.BaseEntity;

public abstract class DefaultLongIDEntityDAO<T extends BaseEntity<Long>> extends EntityBaseDAODemo<T, Long> {
    protected void createBeginID() {
        this.currentID = 0L;
    }

    protected Long getNextID() {
        return ++this.currentID;
    }
}

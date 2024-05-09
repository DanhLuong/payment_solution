package org.example.domain.DAO;

import org.example.domain.entity.BaseEntity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EntityBaseDAODemo<T extends BaseEntity<ID>, ID> {
    protected final Map<ID, T> data = new HashMap<>();
    protected ID currentID;
    abstract protected void createBeginID();
    abstract protected ID getNextID();
    abstract protected void initSampleData();
    {
        createBeginID();
        initSampleData();
    }
    public T findByID(ID id) {
        T found =  data.getOrDefault(id, null);
        if ( found == null) {
            return null;
        }
        return found.isDeleted() ? null : found;
    }
    public ID create(T entity) {
        ID newID = getNextID();
        ZonedDateTime now = ZonedDateTime.now();
        entity.setCreatedAt(now);
        entity.setModifiedAt(now);
        entity.setId(newID);
        data.put(newID, entity);
        return newID;
    }
    public boolean update(ID id, T entity) {
        if(findByID(id) == null) {
            return false;
        }
        entity.setModifiedAt(ZonedDateTime.now());
        data.put(id, entity);
        return true;
    }
    public boolean delete(ID id) {
        if(findByID(id) == null) {
            return false;
        }
        data.get(id).setDeleted();
        return true;
    }

    public List<T> getAll(){
        return new ArrayList<>(data.values());
    }

}

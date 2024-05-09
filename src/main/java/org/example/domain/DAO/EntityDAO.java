package org.example.domain.DAO;

import java.util.List;

public interface EntityDAO<T, ID> {
    T findByID(ID id);
    ID create(T entity);
    boolean update(ID id, T entity);
    boolean delete(ID id);
    List<T> getAll();
}

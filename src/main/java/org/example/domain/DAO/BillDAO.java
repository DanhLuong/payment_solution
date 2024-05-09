package org.example.domain.DAO;


import org.example.domain.entity.BillEntity;

import java.util.List;

public interface BillDAO extends EntityDAO<BillEntity, Long> {
    List<BillEntity> findByClientIdSortByDueDateDesc(long clientId);
    List<BillEntity> findByClientIdAndProviderSortByDueDateDesc(long clientId, String provider);
    List<BillEntity> findByClientIdSortByDueDateDesc(long clientId, boolean isPaid);
    List<BillEntity> findByClientIdAndProviderSortByDueDateDesc(long clientId, String provider, boolean isPaid);
}

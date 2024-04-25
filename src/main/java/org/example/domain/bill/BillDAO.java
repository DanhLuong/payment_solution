package org.example.domain.bill;


import org.example.domain.common.EntityDAO;

import java.util.List;

public interface BillDAO extends EntityDAO<BillEntity, Long> {
    List<BillEntity> findByClientIdSortByDueDateDesc(long clientId);
    List<BillEntity> findByClientIdAndProviderSortByDueDateDesc(long clientId, String provider);
}

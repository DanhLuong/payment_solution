package org.example.domain.DAO;

import org.example.domain.entity.ScheduleTransactionEntity;

import java.time.ZonedDateTime;
import java.util.List;

public interface ScheduleTransactionDAO extends EntityDAO<ScheduleTransactionEntity, Long> {
    List<ScheduleTransactionEntity> findBillIdFromScheduleTranByDateNotDone(ZonedDateTime date);
}

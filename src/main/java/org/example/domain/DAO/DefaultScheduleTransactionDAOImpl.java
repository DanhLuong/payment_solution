package org.example.domain.DAO;

import org.example.domain.comparator.ZonedDateTimeDateOnlyComparator;
import org.example.domain.entity.ScheduleTransactionEntity;
import org.example.utils.common.Utils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DefaultScheduleTransactionDAOImpl extends DefaultLongIDEntityDAO<ScheduleTransactionEntity> implements ScheduleTransactionDAO{
    @Override
    protected void initSampleData() {
        create(new ScheduleTransactionEntity(1L,3L, Utils.convertZonedDateTimeFromString("10/05/2024", "dd/MM/yyyy")));
    }

    @Override
    public List<ScheduleTransactionEntity> findBillIdFromScheduleTranByDateNotDone(ZonedDateTime date) {
        List<ScheduleTransactionEntity> scheduleTrans = new ArrayList<>();
        Comparator<ZonedDateTime> dateOnlyComparator = new ZonedDateTimeDateOnlyComparator();
        for(ScheduleTransactionEntity scheduleTran : data.values()) {
            if(!scheduleTran.getDone() &&
                !scheduleTran.isDeleted() &&
                dateOnlyComparator.compare(date, scheduleTran.getScheduleDate())==0) {
                scheduleTrans.add(scheduleTran);
            }
        }
        return scheduleTrans;
    }

}

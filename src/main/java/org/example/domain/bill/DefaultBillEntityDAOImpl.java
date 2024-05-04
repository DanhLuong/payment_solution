package org.example.domain.bill;

import org.example.domain.common.DefaultLongIDEntityDAO;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultBillEntityDAOImpl extends DefaultLongIDEntityDAO<BillEntity> implements BillDAO {
    @Override
    protected void initSampleData() {
        create(new BillBuilder()
                .forClientId(1L)
                .provider("EVN")
                .serviceType("Electric")
                .amount(new BigDecimal("182000"))
                .dueDate(new Date(2024, Calendar.APRIL,2,0,0))
                .build());

        create(new BillBuilder()
                .forClientId(1L)
                .provider("SAWACO")
                .serviceType("Water")
                .amount(new BigDecimal("95000"))
                .dueDate(new Date(2024, Calendar.APRIL,2,0,0))
                .build());

        create(new BillBuilder()
                .forClientId(1L)
                .provider("FOODIE")
                .serviceType("Food")
                .amount(new BigDecimal("362000"))
                .dueDate(new Date(2024, Calendar.APRIL,2,0,0))
                .build());
    }

    @Override
    public List<BillEntity> findByClientIdSortByDueDateDesc(long clientId) {
        List<BillEntity> result = new ArrayList<>();
        for(BillEntity bill : data.values()) {
            if(bill.getClientId() == clientId) {
                result.add(bill);
            }
        }
        result.sort(new DueDateDescBillComparator());
        return result;
    }

    @Override
    public List<BillEntity> findByClientIdAndProviderSortByDueDateDesc(long clientId, String provider) {
        List<BillEntity> result = new ArrayList<>();
        for(BillEntity bill : data.values()) {
            if(bill.getClientId() == clientId && bill.getProvider().equals(provider.trim())) {
                result.add(bill);
            }
        }
        result.sort(new DueDateDescBillComparator());
        return result;
    }

    @Override
    public List<BillEntity> findByClientIdSortByDueDateDesc(long clientId, boolean isPaid) {
        return findByClientIdSortByDueDateDesc(clientId)
                .stream()
                .filter(bill -> bill.isPaid() == isPaid)
                .collect(Collectors.toList());
    }

    @Override
    public List<BillEntity> findByClientIdAndProviderSortByDueDateDesc(long clientId, String provider, boolean isPaid) {
        return findByClientIdAndProviderSortByDueDateDesc(clientId, provider)
                .stream()
                .filter(bill -> bill.isPaid() == isPaid)
                .collect(Collectors.toList());
    }
}

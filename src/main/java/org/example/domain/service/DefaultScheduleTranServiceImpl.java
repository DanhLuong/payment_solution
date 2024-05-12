package org.example.domain.service;

import org.example.domain.DAO.ScheduleTransactionDAO;
import org.example.domain.entity.ScheduleTransactionEntity;
import org.example.domain.exception.BusinessException;

import java.time.ZonedDateTime;
import java.util.List;

public class DefaultScheduleTranServiceImpl implements ScheduleTransactionService{
    private final TransactionService transactionService;
    private final ScheduleTransactionDAO scheduleTransactionDAO;

    public DefaultScheduleTranServiceImpl(TransactionService transactionService, ScheduleTransactionDAO scheduleTransactionDAO) {
        this.transactionService = transactionService;
        this.scheduleTransactionDAO = scheduleTransactionDAO;
    }
    //Transactional nested
    @Override
    public void processScheduleTransactionAtDate(ZonedDateTime date) {
        List<ScheduleTransactionEntity> scheduleTrans =  scheduleTransactionDAO.findBillIdFromScheduleTranByDateNotDone(date);
        for(ScheduleTransactionEntity scheduleTran : scheduleTrans) {
            try {
                transactionService.payBillWithBillId(scheduleTran.getClientID(), scheduleTran.getBillID());
                scheduleTran.setDone(true);
                scheduleTransactionDAO.update(scheduleTran.getId(), scheduleTran);
            } catch(BusinessException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //Transactional
    @Override
    public void registerSchedule(Long clientID, Long billID, ZonedDateTime date) {
        scheduleTransactionDAO.create(new ScheduleTransactionEntity(clientID, billID, date));
    }

}

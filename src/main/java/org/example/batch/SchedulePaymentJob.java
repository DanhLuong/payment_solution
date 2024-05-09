package org.example.batch;

import org.example.domain.exception.BusinessException;
import org.example.domain.service.ScheduleTransactionService;
import org.example.domain.service.TransactionService;

import java.time.ZonedDateTime;
import java.util.concurrent.ScheduledExecutorService;

public class SchedulePaymentJob extends AbstractScheduledJob {
    private final ScheduleTransactionService scheduleTransactionService;
    public SchedulePaymentJob(ScheduledExecutorService scheduler, int exeHour, TransactionService transactionService, ScheduleTransactionService scheduleTransactionService) {
        this.scheduler = scheduler;
        if(exeHour < 0 || exeHour > 23) {
            throw new IllegalArgumentException("exeHour is between 0 and 23 inclusively");
        }
        this.exeHour = exeHour;
        this.scheduleTransactionService = scheduleTransactionService;
    }

    @Override
    public void run() {
        System.out.println("<<== Start payment schedule job ==>>");
        try {
            scheduleTransactionService.processScheduleTransactionAtDate(ZonedDateTime.now().plusMinutes(5));
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }
}

package org.example.domain.service;

import org.example.domain.exception.BusinessException;

import java.time.ZonedDateTime;

public interface ScheduleTransactionService {
    void processScheduleTransactionAtDate(ZonedDateTime date) throws BusinessException;
}

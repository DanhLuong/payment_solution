package org.example.batch;

import org.example.utils.common.Utils;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

abstract class AbstractScheduledJob implements Job, Runnable {
    protected ScheduledExecutorService scheduler;
    protected int exeHour;
    //abstract void work();
    @Override
    public void start() {
        this.scheduler.scheduleAtFixedRate(this, calculateInitialDelay(), 24*60*60, TimeUnit.SECONDS);
    }

    private long calculateInitialDelay() {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime scheduledTime = Utils.convertToStandardHour(ZonedDateTime.now(), exeHour);
        // Schedule for the next day if current time is after exeTime
        if (now.compareTo(scheduledTime) >= 0) {
            scheduledTime = scheduledTime.plusDays(1);
        }
        return Duration.between(now, scheduledTime).toSeconds();
    }
}

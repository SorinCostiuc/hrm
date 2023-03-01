package com.easycompany.hrm.utils;

import com.easycompany.hrm.model.Status;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StatusChangeScheduler {
    private Status currentStatus;
    private LocalDate statusChangeDate;
    private final ScheduledExecutorService schedulerChangeStatusToFired = Executors.newSingleThreadScheduledExecutor();

    public StatusChangeScheduler(Status initialStatus, LocalDate statusChangeDate) {
        this.currentStatus = initialStatus;
        this.statusChangeDate = statusChangeDate;
    }

    public void startSchedulerChangeStatusToFired() {
        Duration duration = Duration.between(LocalDate.now().atStartOfDay(), statusChangeDate.atStartOfDay());
        long delay = duration.toDays();

        schedulerChangeStatusToFired.schedule(() -> {
            if (currentStatus == Status.EMPLOYEE) {
                setStatus(Status.FIRED);
            }
        }, delay, TimeUnit.DAYS);
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setStatus(Status status) {
        this.currentStatus = status;
    }

    public LocalDate getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(LocalDate statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }
}
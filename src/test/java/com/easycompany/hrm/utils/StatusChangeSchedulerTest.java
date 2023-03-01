package com.easycompany.hrm.utils;


import com.easycompany.hrm.model.Contract;
import com.easycompany.hrm.model.Personnel;
import com.easycompany.hrm.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.Duration;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class StatusChangeSchedulerTest {

    @InjectMocks
    private StatusChangeScheduler scheduler;

    @BeforeEach
    public void setUp() {
        scheduler.setStatusChangeDate(LocalDate.of(2023, 2, 26));
    }

    @Test
    public void testStartSchedulerChangeStatusToFired() throws InterruptedException {
        Personnel personnel = new Personnel();
        personnel.setId(1);
        personnel.setStatus(Status.EMPLOYEE);
        Contract contract = new Contract();
        contract.setEndDate(LocalDate.of(2023, 2, 28));
        personnel.setContract(contract);

        Duration duration = Duration.between(LocalDate.now().atStartOfDay(), contract.getEndDate().atStartOfDay());
        long sleepTime = duration.toMillis();

        scheduler.startSchedulerChangeStatusToFired();

        Thread.sleep(sleepTime);

        assertEquals(Status.FIRED, personnel.getStatus());
    }

    @Test
    public void testStartScheduler_ContractEndInFuture() {
        Personnel personnel = new Personnel();
        personnel.setId(1);
        personnel.setStatus(Status.EMPLOYEE);
        Contract contract = new Contract();
        contract.setEndDate(LocalDate.of(2023, 3, 31));
        personnel.setContract(contract);

        scheduler.startSchedulerChangeStatusToFired();

        assertEquals(Status.EMPLOYEE, personnel.getStatus());
    }

    @Test
    public void testStartSchedulerChangeStatusToFired_ContractEndInFuture() throws InterruptedException {
        Personnel personnel = new Personnel();
        personnel.setId(1);
        personnel.setStatus(Status.EMPLOYEE);
        Contract contract = new Contract();
        contract.setEndDate(LocalDate.of(2023, 3, 31));
        personnel.setContract(contract);

        scheduler.startSchedulerChangeStatusToFired();

        Thread.sleep(1000);

//        assertEquals(Status.FIRED, personnel.getStatus());
        assertEquals(Status.EMPLOYEE, personnel.getStatus());
    }

    @Test
    public void testStartSchedulerChangeStatusToFired_PersonnelNotFound() {

        scheduler.startSchedulerChangeStatusToFired();
    }

    @Test
    public void testStartSchedulerChangeStatusToFired_ContractEndNotInfinite() {
        Personnel personnel = new Personnel();
        personnel.setId(1);
        Contract contract = new Contract();
        contract.setEndDate(LocalDate.now().plusDays(1));
        personnel.setContract(contract);

        scheduler.startSchedulerChangeStatusToFired();
    }

    @Test
    public void testStartSchedulerChangeStatusToFired_ContractEndSameAsEndDate() {
        Personnel personnel = new Personnel();
        personnel.setId(1);
        Contract contract = new Contract();
        contract.setEndDate(LocalDate.now());
        personnel.setContract(contract);

        scheduler.startSchedulerChangeStatusToFired();
    }

}
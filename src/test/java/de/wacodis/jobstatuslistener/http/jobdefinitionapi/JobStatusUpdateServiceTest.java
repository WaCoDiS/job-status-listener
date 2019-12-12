/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.http.jobdefinitionapi;

import de.wacodis.jobstatuslistener.exception.JobStatusUpdateExeception;
import de.wacodis.jobstatuslistener.model.WacodisJobDefinition;
import de.wacodis.jobstatuslistener.model.WacodisJobStatus;
import de.wacodis.jobstatuslistener.model.WacodisJobStatusUpdate;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Arne
 */
public class JobStatusUpdateServiceTest {

    public JobStatusUpdateServiceTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    @org.junit.jupiter.api.Test
    public void testUpdateStatus_NullStatus() throws Exception {
        JobStatusUpdateService updateService = new JobStatusUpdateService();
        WacodisJobStatusUpdate newJobStatus = new WacodisJobStatusUpdate();
        newJobStatus.setWacodisJobIdentifier(UUID.fromString("61d324be-27ff-46c7-9c07-bb22ef30661b"));
        newJobStatus.setNewStatus(null);

        assertThrows(JobStatusUpdateExeception.class, () -> updateService.updateStatus(newJobStatus));
    }

    @org.junit.jupiter.api.Test
    public void testUpdateStatus_NullIdentifier() throws Exception {
        JobStatusUpdateService updateService = new JobStatusUpdateService();
        WacodisJobStatusUpdate newJobStatus = new WacodisJobStatusUpdate();
        newJobStatus.setNewStatus(WacodisJobStatus.WAITING);
        newJobStatus.setWacodisJobIdentifier(null);

        assertThrows(JobStatusUpdateExeception.class, () -> updateService.updateStatus(newJobStatus));
    }

}

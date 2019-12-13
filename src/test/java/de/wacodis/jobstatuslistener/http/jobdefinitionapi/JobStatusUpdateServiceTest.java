/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.http.jobdefinitionapi;

import de.wacodis.jobstatuslistener.configuration.JobDefinitionAPIConfig;
import de.wacodis.jobstatuslistener.exception.JobStatusUpdateExeception;
import de.wacodis.jobstatuslistener.model.WacodisJobStatus;
import de.wacodis.jobstatuslistener.model.WacodisJobStatusUpdate;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Arne
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {JobStatusUpdateService.class, JobDefinitionAPIConfig.class})
public class JobStatusUpdateServiceTest {

    @Autowired
    JobStatusUpdateService updateService;
    
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
        WacodisJobStatusUpdate newJobStatus = new WacodisJobStatusUpdate();
        newJobStatus.setWacodisJobIdentifier(UUID.fromString("61d324be-27ff-46c7-9c07-bb22ef30661b"));
        newJobStatus.setNewStatus(null);

        assertThrows(JobStatusUpdateExeception.class, () -> this.updateService.updateStatus(newJobStatus));
    }

    @org.junit.jupiter.api.Test
    public void testUpdateStatus_NullIdentifier() throws Exception {
        WacodisJobStatusUpdate newJobStatus = new WacodisJobStatusUpdate();
        newJobStatus.setNewStatus(WacodisJobStatus.WAITING);
        newJobStatus.setWacodisJobIdentifier(null);

        assertThrows(JobStatusUpdateExeception.class, () -> this.updateService.updateStatus(newJobStatus));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.messaging.listener;

import de.wacodis.jobstatuslistener.exception.JobStatusUpdateExeception;
import de.wacodis.jobstatuslistener.http.jobdefinitionapi.JobStatusUpdateService;
import de.wacodis.jobstatuslistener.model.WacodisJobDefinition;
import de.wacodis.jobstatuslistener.model.WacodisJobExecution;
import de.wacodis.jobstatuslistener.model.WacodisJobStatus;
import de.wacodis.jobstatuslistener.model.WacodisJobStatusUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 *
 * @author Arne
 */
@EnableBinding(JobExecutionMessageListener.class)
public class ToolExecutionStartedHandler implements MessageHandler<WacodisJobExecution> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolExecutionStartedHandler.class);

    @Autowired
    JobStatusUpdateService statusUpdateService;

    @Override
    @StreamListener(JobExecutionMessageListener.TOOLS_EXECTUTE)
    public void handleMessage(WacodisJobExecution msg) {
        LOGGER.debug("received job execution started message:" + msg.toString());
        LOGGER.info("update status of WacodisJobDefintion with id {} from WacodisJobExecution message", msg.getJobIdentifier());
        WacodisJobStatusUpdate newJobSatus = buildNewJobStatus(msg);
        try {
           WacodisJobDefinition updatedJob =  this.statusUpdateService.updateStatus(newJobSatus);
           LOGGER.info("status for WacodisJobDefinition {} successfully updated. Updated job data: {}", msg.getJobIdentifier(), updatedJob);
        } catch (JobStatusUpdateExeception ex) {
            LOGGER.error("error occured while updating status of WacodisJobDefinition " + msg.getJobIdentifier(), ex);
        } 
    }

    @Override
    public Class<WacodisJobExecution> supportedMessageType() {
        return WacodisJobExecution.class;
    }

    private WacodisJobStatusUpdate buildNewJobStatus(WacodisJobExecution jobExc) {
        WacodisJobStatusUpdate newStatusJobDef = new WacodisJobStatusUpdate();
        newStatusJobDef.setWacodisJobIdentifier(jobExc.getJobIdentifier());
        newStatusJobDef.setNewStatus(WacodisJobStatus.RUNNING); //started, now running

        return newStatusJobDef;
    }
}

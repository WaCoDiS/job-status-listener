/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.messaging.listener;

import de.wacodis.jobstatuslistener.exception.JobStatusUpdateExeception;
import de.wacodis.jobstatuslistener.http.jobdefinitionapi.JobStatusUpdateService;
import de.wacodis.jobstatuslistener.model.WacodisJobDefinition;
import de.wacodis.jobstatuslistener.model.WacodisJobFailed;
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
public class ToolExecutionFailedHandler implements MessageHandler<WacodisJobFailed> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolExecutionFailedHandler.class);

    @Autowired
    JobStatusUpdateService statusUpdateService;

    @Override
    @StreamListener(JobExecutionMessageListener.TOOLS_FAILURE)
    public void handleMessage(WacodisJobFailed msg) {
        LOGGER.debug("received new job execution failed message: " + msg.toString());
        LOGGER.info("update status of WacodisJobDefintion with id {} from WacodisJobFailed message", msg.getWacodisJobIdentifier());
        WacodisJobStatusUpdate newJobSatus = buildNewJobStatus(msg);
        try {
            WacodisJobDefinition updatedJob = this.statusUpdateService.updateStatus(newJobSatus);
            LOGGER.info("status for WacodisJobDefinition {} successfully updated. Updated job data: {}", msg.getWacodisJobIdentifier(), updatedJob);
        } catch (JobStatusUpdateExeception ex) {
            LOGGER.error("error occured while updating status of WacodisJobDefinition " + msg.getWacodisJobIdentifier(), ex);
        }
    }

    private WacodisJobStatusUpdate buildNewJobStatus(WacodisJobFailed jobFail) {
        WacodisJobStatus newJobStatus;
        
        WacodisJobStatusUpdate newStatusJobDef = new WacodisJobStatusUpdate();
        newStatusJobDef.setWacodisJobIdentifier(jobFail.getWacodisJobIdentifier());
        //only set status to waiting/finished if last sub process of wacodis job
        if (jobFail.getFinalJobProcess()) {
            //set waiting after succesful execution or finished if single execution job
            newJobStatus = (jobFail.getSingleExecutionJob()) ? WacodisJobStatus.FINISHED : WacodisJobStatus.WAITING;
        } else {
            newJobStatus = WacodisJobStatus.RUNNING;
        }
        newStatusJobDef.setNewStatus(newJobStatus);

        return newStatusJobDef;
    }

    @Override
    public Class<WacodisJobFailed> supportedMessageType() {
        return WacodisJobFailed.class;
    }

}

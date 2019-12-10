/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.messaging.listener;

import de.wacodis.jobstatuslistener.exception.JobStatusUpdateExeception;
import de.wacodis.jobstatuslistener.http.jobdefinitionapi.JobStatusUpdateService;
import de.wacodis.jobstatuslistener.model.ProductDescription;
import de.wacodis.jobstatuslistener.model.WacodisJobDefinition;
import de.wacodis.jobstatuslistener.model.WacodisJobStatus;
import de.wacodis.jobstatuslistener.model.WacodisJobStatusUpdate;
import org.joda.time.DateTime;
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
public class ToolExecutionFinishedHandler implements MessageHandler<ProductDescription>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolExecutionFinishedHandler.class);
    
    @Autowired
    JobStatusUpdateService statusUpdateService;
    
    @Override
    @StreamListener(JobExecutionMessageListener.TOOLS_FINISHED)
    public void handleMessage(ProductDescription msg) {
        LOGGER.debug("received tool finished message: " + msg.toString());
        LOGGER.info("update status of WacodisJobDefintion with id {} from ProductDescription message", msg.getWacodisJobIdentifier());
        WacodisJobStatusUpdate newJobSatus = buildNewJobStatus(msg);
        try {
           WacodisJobDefinition updatedJob =  this.statusUpdateService.updateStatus(newJobSatus);
           LOGGER.info("status for WacodisJobDefinition {} successfully updated. Updated job data: {}", msg.getWacodisJobIdentifier(), updatedJob);
        } catch (JobStatusUpdateExeception ex) {
            LOGGER.error("error occured while updating status of WacodisJobDefinition " + msg.getWacodisJobIdentifier(), ex);
        } 
    }

    @Override
    public Class<ProductDescription> supportedMessageType() {
        return ProductDescription.class;
    }
    
    
    private WacodisJobStatusUpdate buildNewJobStatus(ProductDescription prodDesc){
        WacodisJobStatusUpdate newStatusJobDef = new WacodisJobStatusUpdate();
        newStatusJobDef.setWacodisJobIdentifier(prodDesc.getWacodisJobIdentifier());
        newStatusJobDef.setNewStatus(WacodisJobStatus.WAITING); //set waiting after succesful execution
        newStatusJobDef.setExecutionFinished(prodDesc.getExecutionFinished());
        
        return newStatusJobDef;
    }
}

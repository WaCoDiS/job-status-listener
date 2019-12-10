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
        WacodisJobDefinition newJobSatus = buildNewJobStatus(msg);
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
    
    
    private WacodisJobDefinition buildNewJobStatus(ProductDescription prodDesc){
        WacodisJobDefinition newStatusJobDef = new WacodisJobDefinition();
        newStatusJobDef.setId(prodDesc.getWacodisJobIdentifier());
        newStatusJobDef.setStatus(WacodisJobDefinition.StatusEnum.WAITING); //set waiting after succesful execution
        newStatusJobDef.setLastFinishedExecution(prodDesc.getExecutionFinished());
        
        return newStatusJobDef;
    }
}

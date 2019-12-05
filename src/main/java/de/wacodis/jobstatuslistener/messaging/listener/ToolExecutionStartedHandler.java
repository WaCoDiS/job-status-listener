/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.messaging.listener;

import de.wacodis.jobstatuslistener.model.WacodisJobDefinition;
import de.wacodis.jobstatuslistener.model.WacodisJobExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 *
 * @author Arne
 */
@EnableBinding(JobExecutionMessageListener.class)
public class ToolExecutionStartedHandler implements MessageHandler<WacodisJobExecution>{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolExecutionStartedHandler.class);

    @Override
    @StreamListener(JobExecutionMessageListener.TOOLS_EXECTUTE)
    public void handleMessage(WacodisJobExecution msg) {
        //TODO add JobDefinition to WacodisJobExecution or add Wacodis Job Id and get Definition from Wacodis Job Definition API)
        //WacodisJobDefinition job = msg.getJobDefinition();
        //TODO set job status and submit to Wacodis Job Definition API
        //job.setStatus(WacodisJobDefinition.StatusEnum.RUNNING);
        LOGGER.info("received job execution started message \n" + msg.toString());  
    }

    @Override
    public Class<WacodisJobExecution> supportedMessageType() {
        return WacodisJobExecution.class;
    }
}

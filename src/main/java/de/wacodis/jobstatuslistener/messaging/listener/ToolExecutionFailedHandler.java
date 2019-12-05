/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.messaging.listener;

import de.wacodis.jobstatuslistener.model.WacodisJobFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 *
 * @author Arne
 */
public class ToolExecutionFailedHandler implements MessageHandler<WacodisJobFailed>{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolExecutionFailedHandler.class);
    

    @Override
    @StreamListener(JobExecutionMessageListener.TOOLS_FAILURE)
    public void handleMessage(WacodisJobFailed msg) {
        LOGGER.info("received new job execution failed message \n" + msg.toString());
    }

    @Override
    public Class<WacodisJobFailed> supportedMessageType() {
        return WacodisJobFailed.class;
    }
    
}

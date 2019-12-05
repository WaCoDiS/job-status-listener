/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.messaging.listener;

import de.wacodis.jobstatuslistener.model.ProductDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 *
 * @author Arne
 */
@EnableBinding(JobExecutionMessageListener.class)
public class ToolExecutionFinishedHandler implements MessageHandler<ProductDescription>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolExecutionFinishedHandler.class);
    
    @Override
    @StreamListener(JobExecutionMessageListener.TOOLS_FINISHED)
    public void handleMessage(ProductDescription msg) {
        //ToDO add WacodisJobIdentifier to ProductDescription
        // set job status to waiting
        //ToDO add Timestamp to ProductDesciption
        // set lastFinisedExecution
        LOGGER.info("received tool finished message /n" + msg.toString());
    }

    @Override
    public Class<ProductDescription> supportedMessageType() {
        return ProductDescription.class;
    }
    
}

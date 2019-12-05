/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.messaging.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 *
 * @author Arne
 */
public interface JobExecutionMessageListener {
    
    String TOOLS_EXECTUTE =  "tools-execute"; //tool execution started
    String TOOLS_FINISHED = "tools-finished"; //tool execution finished successfully
    String TOOLS_FAILURE = "tools-failure"; //tool execution failed
    
    @Input(TOOLS_EXECTUTE)
    SubscribableChannel toolExecutionStarted();
    
    @Input(TOOLS_FINISHED)
    SubscribableChannel toolExecutionFinished();
    
    @Input(TOOLS_FAILURE)
    SubscribableChannel toolExecutionFailed(); 
}

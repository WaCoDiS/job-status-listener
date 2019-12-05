/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.messaging.listener;

/**
 *
 * @author Arne
 * @param <T>
 */
public interface MessageHandler<T> {
    
    void handleMessage(T msg);
    
    Class<T> supportedMessageType();
}

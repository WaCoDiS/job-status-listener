/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.exception;

/**
 *
 * @author Arne
 */
public class JobStatusUpdateExeception extends Exception {

    public JobStatusUpdateExeception() {
    }

    public JobStatusUpdateExeception(String message) {
        super(message);
    }

    public JobStatusUpdateExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public JobStatusUpdateExeception(Throwable cause) {
        super(cause);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.http.jobdefinitionapi;

import de.wacodis.jobstatuslistener.exception.JobStatusUpdateExeception;
import de.wacodis.jobstatuslistener.model.WacodisJobDefinition;
import de.wacodis.jobstatuslistener.model.WacodisJobStatusUpdate;

/**
 *
 * @author Arne
 */
public interface JobStatusUpdater {


    /**
     * @param newJobStatus job with updated status
     * @return job data after update
     * @throws de.wacodis.jobstatuslistener.exception.JobStatusUpdateExeception
     */
    WacodisJobDefinition updateStatus(WacodisJobStatusUpdate newJobStatus) throws JobStatusUpdateExeception;

}

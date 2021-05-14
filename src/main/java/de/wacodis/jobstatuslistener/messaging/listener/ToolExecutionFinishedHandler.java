/*
 * Copyright 2018-2021 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.wacodis.jobstatuslistener.messaging.listener;

import de.wacodis.jobstatuslistener.exception.JobStatusUpdateExeception;
import de.wacodis.jobstatuslistener.http.jobdefinitionapi.JobStatusUpdateService;
import de.wacodis.jobstatuslistener.model.WacodisJobDefinition;
import de.wacodis.jobstatuslistener.model.WacodisJobFinished;
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
public class ToolExecutionFinishedHandler implements MessageHandler<WacodisJobFinished> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolExecutionFinishedHandler.class);

    @Autowired
    JobStatusUpdateService statusUpdateService;

    @Override
    @StreamListener(JobExecutionMessageListener.TOOLS_FINISHED)
    public void handleMessage(WacodisJobFinished msg) {
        LOGGER.debug("received tool finished message: " + msg.toString());
        LOGGER.info("update status of WacodisJobDefintion with id {} from ProductDescription message", msg.getWacodisJobIdentifier());
        WacodisJobStatusUpdate newJobSatus = buildNewJobStatus(msg);
        try {
            WacodisJobDefinition updatedJob = this.statusUpdateService.updateStatus(newJobSatus);
            LOGGER.info("status for WacodisJobDefinition {} successfully updated. Updated job data: {}", msg.getWacodisJobIdentifier(), updatedJob);
        } catch (JobStatusUpdateExeception ex) {
            LOGGER.error("error occured while updating status of WacodisJobDefinition " + msg.getWacodisJobIdentifier(), ex);
        }
    }

    @Override
    public Class<WacodisJobFinished> supportedMessageType() {
        return WacodisJobFinished.class;
    }

    private WacodisJobStatusUpdate buildNewJobStatus(WacodisJobFinished jobFinishedInfo) {
        WacodisJobStatus newJobStatus;

        WacodisJobStatusUpdate newStatusJobDef = new WacodisJobStatusUpdate();
        newStatusJobDef.setWacodisJobIdentifier(jobFinishedInfo.getWacodisJobIdentifier());
        newStatusJobDef.setExecutionFinished(jobFinishedInfo.getExecutionFinished());
        //only set status to waiting/finished if last sub process of wacodis job
        if (jobFinishedInfo.getFinalJobProcess()) {
            //set waiting after succesful execution or finished if single execution job
            newJobStatus = (jobFinishedInfo.getSingleExecutionJob()) ? WacodisJobStatus.FINISHED : WacodisJobStatus.WAITING;
        } else {
            newJobStatus = WacodisJobStatus.RUNNING;
        }
        newStatusJobDef.setNewStatus(newJobStatus);

        return newStatusJobDef;
    }
}

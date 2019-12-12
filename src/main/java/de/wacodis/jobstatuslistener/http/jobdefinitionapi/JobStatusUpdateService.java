/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.http.jobdefinitionapi;

import de.wacodis.jobstatuslistener.configuration.JobDefinitionAPIConfig;
import de.wacodis.jobstatuslistener.exception.JobStatusUpdateExeception;
import de.wacodis.jobstatuslistener.model.WacodisJobDefinition;
import de.wacodis.jobstatuslistener.model.WacodisJobStatusUpdate;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Arne
 */
@Service
public class JobStatusUpdateService implements JobStatusUpdater {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JobStatusUpdateService.class);
    
    private static final MediaType REQUESTMEDIATYPE = MediaType.APPLICATION_JSON;

    @Autowired
    JobDefinitionAPIConfig config;

    RestTemplate apiConnector;

    public JobStatusUpdateService() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        apiConnector = new RestTemplate();
        apiConnector.setRequestFactory(requestFactory); //needed to support HTTP PATCH
    }

    @Override
    public WacodisJobDefinition updateStatus(WacodisJobStatusUpdate newJobStatus) throws JobStatusUpdateExeception {
        if (newJobStatus.getWacodisJobIdentifier() == null) {
            throw new JobStatusUpdateExeception("job identifier missing, unable to update status for WacodisJobDefinition: " + newJobStatus.toString());
        }
        if (newJobStatus.getNewStatus() == null) {
            throw new JobStatusUpdateExeception("new status is null, unable to update status for WacodisJobDefinition: " + newJobStatus.toString());
        }

        try {
            LOGGER.info("update status for WacodisJobDefintion {}, new status {}", newJobStatus.getWacodisJobIdentifier(), newJobStatus.getNewStatus());
            HttpEntity<WacodisJobStatusUpdate> body = new HttpEntity<>(newJobStatus, getHeaders());
            ResponseEntity<WacodisJobDefinition> statusUpdateResponse = apiConnector.exchange(concatUrl().toURI(), HttpMethod.resolve(config.getHttpMethod()), body, WacodisJobDefinition.class);
            LOGGER.info("successfully updated status for WacodisJobDefintion {}", newJobStatus.getWacodisJobIdentifier());

            return statusUpdateResponse.getBody();
        } catch (Exception e) {
            throw new JobStatusUpdateExeception("unable to update status for WacodisJobDefinition with id " + newJobStatus.getWacodisJobIdentifier(), e);
        }
    }

    private URL concatUrl() throws MalformedURLException {
        URL base = new URL(config.getBaseUrl());
        URL url = new URL(base, config.getApiEndpoint());

        return url;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(REQUESTMEDIATYPE));

        return headers;
    }

}

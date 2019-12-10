/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Arne
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("spring.jobdefinitionapi")
public class JobDefinitionAPIConfig {

    @Value("${spring.jobdefinitionapi.baseUrl:http://localhost:8081}")
    private String baseUrl;
    @Value("${spring.jobdefinitionapi.apiEndpoint:/jobDefinitions/jobstatus/}")
    private String apiEndpoint;
    @Value("${spring.jobdefinitionapi.httpmethod:PATCH}")
    private String httpMethod;

    public String getHttpMethod() {
        return httpMethod;
    }

    public JobDefinitionAPIConfig() {
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    @Override
    public String toString() {
        return "JobDefinitionAPIConfig{" + "baseUrl=" + baseUrl + ", apiEndpoint=" + apiEndpoint + ", httpMethod=" + httpMethod + '}';
    }
}

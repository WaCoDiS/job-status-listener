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

# WaCoDiS Job Status Listener
![Java CI](https://github.com/WaCoDiS/metadata-connector/workflows/Java%20CI/badge.svg)
  
The **WaCoDiS Job Status Listener** interacts with the WaCoDiS Job Management API in order to update the status information (e.g. running, waiting, ...) of currently registered WaCoDiS Jobs.

**Table of Content**  

1. [WaCoDiS Project Information](#wacodis-project-information)
  * [Architecture Overview](#architecture-overview)
2. [Overview](#overview)
  * [Core Data Types](#core-data-types)
  * [Modules](#modules)
  * [Utilized Technologies](#utilized-technologies)
3. [Installation / Building Information](#installation--building-information)
  * [Build from Source](#build-from-source)
  * [Build using Docker](#build-using-docker)
  * [Configuration](#configuration)
  * [Deployment](#deployment)
    * [Preconditions](#preconditions)
4. [User Guide](#user-guide)
5. [Contribution - Developer Information](#contribution---developer-information)
  * [Branching](#branching) 
  * [License and Third Party Lib POM Plugins](#license-and-third-party-lib-pom-plugins)
6. [Contact](#contact)
7. [Credits and Contributing Organizations](#credits-and-contributing-organizations)


## WaCoDiS Project Information
<p align="center">
  <img src="https://raw.githubusercontent.com/WaCoDiS/apis-and-workflows/master/misc/logos/wacodis.png" width="200">
</p>
Climate changes and the ongoing intensification of agriculture effect in increased material inputs in watercourses and dams.
Thus, water industry associations, suppliers and municipalities face new challenges. To ensure an efficient and environmentally
friendly water supply for the future, adjustments on changing conditions are necessary. Hence, the research project WaCoDiS
aims to geo-locate and quantify material outputs from agricultural areas and to optimize models for sediment and material
inputs (nutrient contamination) into watercourses and dams. Therefore, approaches for combining heterogeneous data sources,
existing interoperable web based information systems and innovative domain oriented models will be explored.

### Architecture Overview

For a detailed overview about the WaCoDiS system architecture please visit the 
**[WaCoDiS Core Engine](https://github.com/WaCoDiS/core-engine)** repository.  

## Overview  
The **WaCoDiS Job Status Listerner** is an intermediate componenten that subscribes to message about progress of running WaCoDiS processing jobs. The Job Status Listener interacts with **[WaCoDiS Job Manager](https://github.com/WaCoDiS/job-definition-api)**. Information about a job's processing progress are received via the WaCoDiS System's message broker (RabbitMQ). Received information about processing progress are used to update the corresponding job's status by communication with Job Manager's REST API.
### Modules
The WaCoDiS Job Status Listener is a stand-alone Spring Boot application comprisiung only a single module.

## Installation / Building Information
### Build from Source
In order to build the WaCoDiS Job Status Listener from source _Java Development Kit_ (JDK) must be available. Data Access 
is tested with Oracle JDK 8 and OpenJDK 8. Unless stated otherwise later JDK versions can be used.  

Since this is a Maven project, [Apache Maven](https://maven.apache.org/) must be available for building it. Then, you
can build the project by running `mvn clean install` from root directory

### Build using Docker
The project contains a Dockerfile for building a Docker image. Simply run `docker build -t wacodis/job-status-listener:latest .`
in order to build the image. You will find some detailed information about running the Job Status Listener as Docker container
within the [run section](#using-docker) .

### Configuration
Configuration is fetched from [WaCoDiS Config Server](https://github.com/WaCoDiS/config-server). If config server is not
available, configuration values located at *src/main/resources/application.yml*.   
#### Parameters
TODO
Describe configuration parameters


### Deployment
This section describes deployment scenarios, options and preconditions.
#### Preconditions
* (without using Docker) In order to run Data Access Java Runtime Environment (JRE) (version >= 8) must be available. In order to [build Data Access from source](#installation--building-information) Java Development Kit (JDK) version >= 8) must be abailable. Data Access is tested with Oracle JDK 8 and OpenJDK 8.
* A (running) instance of [WaCoDiS Job Manager](https://github.com/WaCoDiS/job-definition-api) must be available 
* In order to receive message a running instance a running inst.ance of [RabbitMQ message broker](https://www.rabbitmq.com/) must be available.  
* When running data access as part of the WaCoDiS system, messages are published by [WaCoDiS Core Engine](https://github.com/WaCoDiS/core-engine). For testing purposes can be used to publish/mock messages about processing progress of a WaCoDiS Job.
  
The server addresses are [configurable](#configuration).  
  
 * If [configuration](#configuration) should be fetched from Configuration Server a running instance of [WaCoDiS Config Server](https://github.com/WaCoDiS/config-server) must be available.

## User Guide
TODO
Describe how to run and use this component

## Contribution - Developer Information
This section contains information for developers.

### Branching
The master branch provides sources for stable builds. The develop branch represents the latest (maybe unstable) state of development.

### License and Third Party Lib POM Plugins
[optional]

## Contact
|    Name   |   Organization    |    Mail    |
| :-------------: |:-------------:| :-----:|
| Sebastian Drost | Bochum University of Applied Sciences | sebastian.drost@hs-bochum.de |
| Arne Vogt | Bochum University of Applied Sciences | arne.vogt@hs-bochum.de |
| Andreas Wytzisk  | Bochum University of Applied Sciences | andreas.wytzisk@hs-bochum.de |
| Matthes Rieke | 52° North GmbH | m.rieke@52north.org |

## Credits and Contributing Organizations
- Department of Geodesy, Bochum University of Applied Sciences, Bochum
- 52° North Initiative for Geospatial Open Source Software GmbH, Münster
- Wupperverband, Wuppertal
- EFTAS Fernerkundung Technologietransfer GmbH, Münster

The research project WaCoDiS is funded by the BMVI as part of the [mFund programme](https://www.bmvi.de/DE/Themen/Digitales/mFund/Ueberblick/ueberblick.html)  
<p align="center">
  <img src="https://raw.githubusercontent.com/WaCoDiS/apis-and-workflows/master/misc/logos/mfund.jpg" height="100">
  <img src="https://raw.githubusercontent.com/WaCoDiS/apis-and-workflows/master/misc/logos/bmvi.jpg" height="100">
</p>


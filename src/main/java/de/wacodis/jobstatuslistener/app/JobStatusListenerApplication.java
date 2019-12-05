/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wacodis.jobstatuslistener.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Arne
 */
@SpringBootApplication
@ComponentScan({
    "de.wacodis.jobstatuslistener.app",
    "de.wacodis.jobstatuslistener.jobdefinitionapi",
    "de.wacodis.jobstatuslistener.messaging.listener"
})
public class JobStatusListenerApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(JobStatusListenerApplication.class, args);
    }
}

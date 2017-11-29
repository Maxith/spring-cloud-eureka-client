package com.maxith.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    @Qualifier("eurekaClient")
    private EurekaClient client;

    @RequestMapping("/say")
    public String say(){
        List<String> registeredApps = new ArrayList<>();
        client.getApplications().getRegisteredApplications().forEach(item -> registeredApps.add(item.getName()));
        return registeredApps.toString();
    }
}

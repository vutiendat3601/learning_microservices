package com.datvutech.discoveryserver.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("configs")
@RestController
public class ConfigController {
    private final Environment env;

    public ConfigController(Environment env) {
        this.env = env;
    }

    @GetMapping
    public ResponseEntity<String> getConfigs() {
        String appVersion = env.getProperty("app.version");
        return ResponseEntity.ok("""
                {
                    "appVersion": "%s"
                }
                """
                .formatted(appVersion));
    }
}

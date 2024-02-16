package com.senior.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@ConfigurationProperties(prefix = "com.senior")
@PropertySource("classpath:application.properties")
@Data
public class ApplicationProperties {
    private String databaseName;
    private String databaseHost;
    private String databasePort;
    private String databaseUsername;
    private String databasePassword;
}

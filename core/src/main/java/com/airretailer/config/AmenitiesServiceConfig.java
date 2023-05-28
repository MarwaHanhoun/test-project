package com.airretailer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "amenities.service")
@Component
@Getter
@Setter
public class AmenitiesServiceConfig {
    private String url;
}

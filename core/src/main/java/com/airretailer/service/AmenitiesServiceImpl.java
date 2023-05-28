package com.airretailer.service;

import com.airretailer.config.AmenitiesServiceConfig;
import com.airretailer.enums.CabinType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
@RestController
class AmenitiesServiceImpl implements AmenitiesService{

//    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    RestTemplate restTemplate;
    private final AmenitiesServiceConfig config;
    @Override
    public ObjectNode findAmenitiesFor(CabinType cabinType) {
       var result =restTemplate.getForEntity(config.getUrl(), JsonNode.class);
        System.out.println(result.getBody().toPrettyString());
       return (ObjectNode) result.getBody().get("ammenities").get(0).get(cabinType.displayName);
    }

}

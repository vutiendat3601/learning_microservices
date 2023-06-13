package com.datvutech.licenseservice.service.client;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.datvutech.licenseservice.dto.OrganizationDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OrganizationDiscoveryClient {

    private final DiscoveryClient discoveryClient;
    
    private final RestTemplate restTemplate;

    public OrganizationDto getOrganization(UUID organizationId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("organization-service");

        if (instances.size() == 0)
            return null;
        String serviceUri = String.format("%s/v1/organizations/%s", instances.get(0).getUri().toString(),
                organizationId);

        ResponseEntity<OrganizationDto> restExchange = restTemplate.exchange(
                serviceUri,
                HttpMethod.GET,
                null, OrganizationDto.class, organizationId);

        return restExchange.getBody();
    }
}

package com.datvutech.licenseservice.service.client;

import java.net.URI;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.datvutech.licenseservice.dto.OrganizationDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class OrganizationRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    @CircuitBreaker(name = "organizationService")
    public OrganizationDto getOrganization(UUID organizationId, HttpServletRequest req) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", req.getHeader("Authorization"));
        // Create request entity with headers
        RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET,
                URI.create("http://organization-service/v1/organizations/" + organizationId));

        /*
         * ResponseEntity<OrganizationDto> restExchange = restTemplate.exchange(
         * "http://organization-service/v1/organizations/{organizationId}",
         * HttpMethod.GET,
         * null, OrganizationDto.class, organizationId);
         */
        ResponseEntity<OrganizationDto> restExchange = restTemplate.exchange(requestEntity, OrganizationDto.class);

        return restExchange.getBody();
    }

}

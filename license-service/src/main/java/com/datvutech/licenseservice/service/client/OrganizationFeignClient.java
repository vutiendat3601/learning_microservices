package com.datvutech.licenseservice.service.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.datvutech.licenseservice.dto.OrganizationDto;

@FeignClient("organization-service")
public interface OrganizationFeignClient {
    @GetMapping(value = "/v1/organizations/{organizationId}", consumes = "application/json")
    OrganizationDto getOrganization(@PathVariable("organizationId") UUID organizationId);
}
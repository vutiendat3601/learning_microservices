package com.datvutech.licenseservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.datvutech.licenseservice.controller.model.request.CreateLicenseRequest;
import com.datvutech.licenseservice.controller.model.request.UpdateLicenseRequest;
import com.datvutech.licenseservice.dto.LicenseDto;
import com.datvutech.licenseservice.dto.OrganizationDto;
import com.datvutech.licenseservice.io.entity.License;
import com.datvutech.licenseservice.io.repository.LicenseRepository;
import com.datvutech.licenseservice.mapper.LicenseMapper;
import com.datvutech.licenseservice.service.LicenseService;
import com.datvutech.licenseservice.service.client.OrganizationDiscoveryClient;
import com.datvutech.licenseservice.service.client.OrganizationFeignClient;
import com.datvutech.licenseservice.service.client.OrganizationRestTemplateClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class LicenseServiceImpl implements LicenseService {
    private final LicenseMapper licenseMapper;

    private final MessageSource messages;

    private final LicenseRepository licenseRepo;

    private final OrganizationFeignClient organizationFeignClient;

    private final OrganizationRestTemplateClient organizationRestClient;

    private final OrganizationDiscoveryClient organizationDiscoveryClient;

    private final HttpServletRequest req;

    @Override
    public LicenseDto getLicense(UUID licenseId, UUID organizationId) {
        License license = new License();
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");
        return licenseMapper.toLicenseDto(license, null);
    }

    @Override
    public LicenseDto createLicense(CreateLicenseRequest licenseReq, UUID organizationId) {
        License license = new License();
        BeanUtils.copyProperties(licenseReq, license);
        license.setLicenseId(UUID.randomUUID());
        OrganizationDto organization = retrieveOrganizationInfo(organizationId, "rest");
        license.setOrganizationId(organizationId);
        license = licenseRepo.save(license);
        return licenseMapper.toLicenseDto(license, organization);
    }

    @Override
    public LicenseDto updateLicense(UUID licenseId, UpdateLicenseRequest licenseReq, UUID organizationId) {
        License license = licenseRepo.findByOrganizationIdAndLicenseId(organizationId, licenseId)
                .orElseThrow(() -> new RuntimeException("License not found!"));
        BeanUtils.copyProperties(licenseReq, license);
        licenseRepo.save(license);
        OrganizationDto organization = retrieveOrganizationInfo(organizationId, "rest");
        return licenseMapper.toLicenseDto(license, organization);
    }

    @Override
    public void deleteLicense(UUID licenseId, UUID organizationId) {
        License license = licenseRepo.findById(licenseId)
                .orElseThrow(() -> new RuntimeException("License not found!"));
        licenseRepo.delete(license);
    }

    @Override
    public LicenseDto getLicense(UUID licenseId, UUID organizationId, String clientType) {
        License license = licenseRepo.findByOrganizationIdAndLicenseId(organizationId, licenseId)
                .orElseThrow(() -> new RuntimeException("License not found!"));
        if (null == license) {
            throw new IllegalArgumentException(String.format(
                    messages.getMessage("license.search.error.message", null, null), licenseId, organizationId));
        }

        OrganizationDto organization = retrieveOrganizationInfo(organizationId, clientType);
        return licenseMapper.toLicenseDto(license, organization);
    }

    private OrganizationDto retrieveOrganizationInfo(UUID organizationId, String clientType) {
        OrganizationDto organization = null;
        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;

            case "rest":
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId, req);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestClient.getOrganization(organizationId, req);
                break;
        }

        return organization;
    }

    @CircuitBreaker(name = "lisenceService", fallbackMethod = "buildFallbackLicenseList")
    /*
     * @Bulkhead(name = "bulkheadLicenseService", fallbackMethod =
     * "buildFallbackLicenseList")
     * 
     * @Retry(name = "retryLicenseService", fallbackMethod =
     * "buildFallbackLicenseList")
     */
    @Override
    public List<License> getLicensesByOrganization(UUID organizationId) {
        randomlyRunLong();
        List<License> licenses = licenseRepo.findByOrganizationId(organizationId);
        return licenses;
    }

    @SuppressWarnings("unused")
    private List<License> buildFallbackLicenseList(UUID organizationId, Throwable t) {
        List<License> fallbackList = new ArrayList<>();
        License license = new License();
        license.setLicenseId(UUID.randomUUID());
        license.setOrganizationId(organizationId);
        license.setProductName("Sorry no licensing information currently available");
        fallbackList.add(license);
        return fallbackList;
    }

    private void randomlyRunLong() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if (randomNum == 3) {
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
            throw new RuntimeException();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}

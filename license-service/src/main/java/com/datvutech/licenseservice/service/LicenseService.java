package com.datvutech.licenseservice.service;

import java.util.List;
import java.util.UUID;

import com.datvutech.licenseservice.controller.model.request.CreateLicenseRequest;
import com.datvutech.licenseservice.controller.model.request.UpdateLicenseRequest;
import com.datvutech.licenseservice.dto.LicenseDto;
import com.datvutech.licenseservice.io.entity.License;

public interface LicenseService {
    LicenseDto getLicense(UUID licenseId, UUID organizationId);

    LicenseDto createLicense(CreateLicenseRequest license, UUID organizationId);

    LicenseDto updateLicense(UUID licenseId, UpdateLicenseRequest license, UUID organizationId);

    void deleteLicense(UUID licenseId, UUID organizationId);

    LicenseDto getLicense(UUID licenseId, UUID organizationId, String clientType);

    List<License> getLicensesByOrganization(UUID organizationId);

}

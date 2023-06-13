package com.datvutech.licenseservice.mapper;


import org.springframework.stereotype.Component;

import com.datvutech.licenseservice.dto.LicenseDto;
import com.datvutech.licenseservice.dto.OrganizationDto;
import com.datvutech.licenseservice.io.entity.License;

@Component
public class LicenseMapper {

    public LicenseDto toLicenseDto(License license, OrganizationDto organization) {
        return new LicenseDto(
                license.getLicenseId(),
                license.getDescription(),
                license.getOrganizationId(),
                license.getProductName(),
                license.getLicenseType(),
                license.getComment(),
                organization);
    };
}

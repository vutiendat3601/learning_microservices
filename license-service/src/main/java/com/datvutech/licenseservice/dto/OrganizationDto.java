package com.datvutech.licenseservice.dto;

import java.util.UUID;

public record OrganizationDto(
        UUID organizationId,
        String name,
        String contactName,
        String contactEmail,
        String contactPhone) {
}

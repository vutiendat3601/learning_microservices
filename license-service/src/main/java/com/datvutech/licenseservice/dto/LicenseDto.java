package com.datvutech.licenseservice.dto;

import java.util.UUID;

public record LicenseDto(
		UUID licenseId,
		String description,
		UUID organizationId,
		String productName,
		String licenseType,
		String comment,
		OrganizationDto organization) {
}

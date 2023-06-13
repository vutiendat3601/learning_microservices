package com.datvutech.licenseservice.controller.model.request;

public record CreateLicenseRequest(
        String description,
        String organizationId,
        String productName,
        String licenseType,
        String comment) {
}

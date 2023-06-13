package com.datvutech.licenseservice.controller.model.request;

public record UpdateLicenseRequest(
        String description,
        String organizationId,
        String productName,
        String licenseType,
        String comment) {

}

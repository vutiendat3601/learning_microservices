package com.datvutech.organizationservice.controller.model.request;

public record UpdateOrganizationRequest(
        String name,
        String contactName,
        String contactEmail,
        String contactPhone) {

}

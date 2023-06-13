package com.datvutech.organizationservice.controller.model.request;

public record CreateOrganizationRequest(
        String name,
        String contactName,
        String contactEmail,
        String contactPhone) {

}

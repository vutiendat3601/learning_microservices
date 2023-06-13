package com.datvutech.organizationservice.service;

import java.util.UUID;

import com.datvutech.organizationservice.controller.model.request.CreateOrganizationRequest;
import com.datvutech.organizationservice.controller.model.request.UpdateOrganizationRequest;
import com.datvutech.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto findById(UUID organizationId);

    OrganizationDto create(CreateOrganizationRequest organizationReq);

    void update(UpdateOrganizationRequest organizationReq);

    void delete(UUID organizationId);
}

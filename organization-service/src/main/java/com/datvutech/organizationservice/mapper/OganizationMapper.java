package com.datvutech.organizationservice.mapper;

import org.springframework.stereotype.Component;

import com.datvutech.organizationservice.dto.OrganizationDto;
import com.datvutech.organizationservice.io.entity.Organization;

@Component
public class OganizationMapper  {

    public OrganizationDto apply(Organization organization) {
        return new OrganizationDto(
                organization.getOrganizationId(),
                organization.getName(),
                organization.getContactName(),
                organization.getContactEmail(),
                organization.getContactPhone());
    }

}

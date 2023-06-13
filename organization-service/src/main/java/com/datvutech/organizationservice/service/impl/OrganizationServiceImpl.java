package com.datvutech.organizationservice.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.datvutech.organizationservice.controller.model.request.CreateOrganizationRequest;
import com.datvutech.organizationservice.controller.model.request.UpdateOrganizationRequest;
import com.datvutech.organizationservice.dto.OrganizationDto;
import com.datvutech.organizationservice.io.entity.Organization;
import com.datvutech.organizationservice.io.repository.OrganizationRepository;
import com.datvutech.organizationservice.mapper.OganizationMapper;
import com.datvutech.organizationservice.service.OrganizationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OganizationMapper organizationDtoMapper;

    private final OrganizationRepository organizationRepo;

    public OrganizationDto findById(UUID organizationId) {
        Organization organization = organizationRepo.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organization not found!"));

        return organizationDtoMapper.apply(organization);
    }

    public OrganizationDto create(CreateOrganizationRequest organizationReq) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationReq, organization);
        organization.setOrganizationId(UUID.randomUUID());
        organization = organizationRepo.save(organization);
        return organizationDtoMapper.apply(organization);

    }

    public void update(UpdateOrganizationRequest organizationReq) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationReq, organization);
        organizationRepo.save(organization);
    }

    public void delete(UUID organizationId) {
        organizationRepo.deleteById(organizationId);
    }
}

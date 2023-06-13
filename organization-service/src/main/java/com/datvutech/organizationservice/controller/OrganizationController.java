package com.datvutech.organizationservice.controller;

import java.util.UUID;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.datvutech.organizationservice.controller.model.request.CreateOrganizationRequest;
import com.datvutech.organizationservice.controller.model.request.UpdateOrganizationRequest;
import com.datvutech.organizationservice.dto.OrganizationDto;
import com.datvutech.organizationservice.service.OrganizationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("v1/organizations")
@RestController
public class OrganizationController {
    private final OrganizationService organizationService;

    @RolesAllowed({ "ADMIN", "USER" })
    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable UUID organizationId) {
        return ResponseEntity.ok(organizationService.findById(organizationId));
    }

    @RolesAllowed({ "ADMIN", "USER" })
    @PutMapping("/{organizationId}")
    public void updateOrganization(@PathVariable UUID organizationId,
            @RequestBody UpdateOrganizationRequest organizationReq) {
        organizationService.update(organizationReq);
    }

    @RolesAllowed({ "ADMIN", "USER" })
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody CreateOrganizationRequest organizationReq) {
        OrganizationDto organization = organizationService.create(organizationReq);
        return ResponseEntity.ok(organization);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{organizationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable UUID organizationId) {
        organizationService.delete(organizationId);
    }
}

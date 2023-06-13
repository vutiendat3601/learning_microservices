package com.datvutech.organizationservice.io.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datvutech.organizationservice.io.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
}

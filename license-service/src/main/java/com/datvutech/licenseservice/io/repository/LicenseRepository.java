package com.datvutech.licenseservice.io.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datvutech.licenseservice.io.entity.License;

public interface LicenseRepository extends JpaRepository<License, UUID> {

    Optional<License> findByOrganizationIdAndLicenseId(UUID organizationId, UUID licenseId);

    List<License> findByOrganizationId(UUID organizationId);
}

package com.datvutech.licenseservice.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datvutech.licenseservice.controller.model.request.CreateLicenseRequest;
import com.datvutech.licenseservice.controller.model.request.UpdateLicenseRequest;
import com.datvutech.licenseservice.dto.LicenseDto;
import com.datvutech.licenseservice.io.entity.License;
import com.datvutech.licenseservice.service.LicenseService;
import com.datvutech.licenseservice.utils.UserContextHolder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
@RestController
@AllArgsConstructor
public class LicenseController {
	private final LicenseService licenseService;

	@RolesAllowed({ "ADMIN", "USER" })
	@GetMapping("/{licenseId}")
	public ResponseEntity<LicenseDto> getLicense(
			@PathVariable UUID organizationId,
			@PathVariable UUID licenseId) {
		LicenseDto license = licenseService
				.getLicense(licenseId, organizationId);

		return ResponseEntity.ok(license);
	}

	@RolesAllowed({ "ADMIN", "USER" })
	@PutMapping("/{licenseId}")
	public ResponseEntity<LicenseDto> updateLicense(
			@PathVariable UUID licenseId,
			@PathVariable UUID organizationId,
			@RequestBody UpdateLicenseRequest request) {

		return ResponseEntity.ok(
				licenseService.updateLicense(
						licenseId,
						request,
						organizationId));
	}

	@RolesAllowed({ "ADMIN", "USER" })
	@PostMapping
	public ResponseEntity<LicenseDto> createLicense(
			@PathVariable UUID organizationId,
			@RequestBody CreateLicenseRequest licenseReq,
			HttpServletRequest req) {
		System.out.println(req.getHeader("Authorization"));
		return ResponseEntity.ok(licenseService.createLicense(licenseReq, organizationId));
	}

	@RolesAllowed({ "ADMIN" })
	@DeleteMapping("/{licenseId}")
	public ResponseEntity<?> deleteLicense(
			@PathVariable UUID organizationId,
			@PathVariable UUID licenseId) {
		licenseService.deleteLicense(licenseId, organizationId);
		return ResponseEntity.ok().build();
	}

	@RolesAllowed({ "ADMIN", "USER" })
	@GetMapping(value = "/{licenseId}/{clientType}")
	public ResponseEntity<LicenseDto> getLicensesWithClient(@PathVariable("organizationId") UUID organizationId,
			@PathVariable("licenseId") UUID licenseId,
			@PathVariable("clientType") String clientType) {
		LicenseDto license = licenseService.getLicense(licenseId, organizationId, clientType);
		return ResponseEntity.ok(license);
	}

	@RolesAllowed({ "ADMIN", "USER" })
	@GetMapping("/")
	public List<License> getLicenses(@PathVariable("organizationId") UUID organizationId) {
		log.debug("LicenseServiceController Correlation id: {}",
				UserContextHolder.getContext().getCorrelationId());
		return licenseService.getLicensesByOrganization(organizationId);
	}
}

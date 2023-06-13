package com.datvutech.licenseservice.io.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "licenses")
public class License {
	@Id
	@Column(name = "license_id")
	private UUID licenseId;

	private String description;

	@Column(name = "organization_id")
	private UUID organizationId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "license_type")
	private String licenseType;

	@Column(name = "comment")
	private String comment;
}

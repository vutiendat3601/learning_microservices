package com.datvutech.licenseservice.exception.model;

import static java.util.Arrays.asList;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RestErrorList extends ArrayList<ErrorMessage> {

	private static final long serialVersionUID = -721424777198115589L;
	private HttpStatus status;

	public RestErrorList(HttpStatus status, ErrorMessage... errors) {
		this(status.value(), errors);
	}

	public RestErrorList(int status, ErrorMessage... errors) {
		super();
		this.status = HttpStatus.valueOf(status);
		addAll(asList(errors));
	}

}
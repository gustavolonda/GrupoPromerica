package com.pfc2.weather.commons.api.applications.model;

import lombok.Getter;

@Getter
public enum StatusDomain {
	DELETE("D"), ACTIVE("A");
	private String value;
	
	private StatusDomain(String value) {
		this.value = value;
	}
}

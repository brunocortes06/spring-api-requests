package com.bruno.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {
	@JsonProperty("status")
	private Code code;

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}
}

package com.liferay.blade.samples.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * @author lawrence
 */
public class User implements Serializable {

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private static final long serialVersionUID = 1113488483222411111L;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

}
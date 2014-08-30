package com.gurps.cascadingvalidation;

import javax.validation.constraints.NotNull;

public class Person {

	@NotNull
	private String name;

	public Person(String name) {
		super();
		this.name = name;
	}
}

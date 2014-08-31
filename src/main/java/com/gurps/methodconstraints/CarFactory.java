package com.gurps.methodconstraints;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CarFactory {

	public Car createCar(@NotNull String make, 
			             @NotNull String model, 
			             @Pattern (regexp="^\\d{4}$") String year){
		System.out.println("Creating car...");
		return new Car();
	}
	
	
}

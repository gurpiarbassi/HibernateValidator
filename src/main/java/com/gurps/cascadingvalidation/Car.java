package com.gurps.cascadingvalidation;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Car {

	@NotNull
	private String manufacturer;
	
	@NotNull
	@Size(min = 2, max = 14)
	private String licensePlate;
	
	@Min(2)
	private int seatCount;
	
	@NotNull
	@Valid
	private Person driver;
	
	@NotNull
	@Valid
	private Collection<Person> passengers;
	
	public Car(String manufacturer, String licencePlate, int seatCount) {
		this.manufacturer = manufacturer;
		this.licensePlate = licencePlate;
		this.seatCount = seatCount;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public Person getDriver() {
		return driver;
	}

	public void setDriver(Person driver) {
		this.driver = driver;
	}

	public Collection<Person> getPassengers() {
		return passengers;
	}

	public void setPassengers(Collection<Person> passengers) {
		this.passengers = passengers;
	}
}

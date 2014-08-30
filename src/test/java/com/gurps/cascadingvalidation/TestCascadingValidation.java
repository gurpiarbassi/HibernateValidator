package com.gurps.cascadingvalidation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests cascading features of validation. Object graphs can easily be validated.
 * The other cool feature is that you can apply the validation to each element of a collection.
 * @author gurps
 *
 */
public class TestCascadingValidation {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		//validator is threadsafe so can be used multiple times.
	}

	@Test
	public void driverIsNull() {
		Car car = new Car("Ford", "DD-AB-123", 4);
		Set<ConstraintViolation<Car>> constraintViolations = validator
				.validate(car);
		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next()
				.getMessage());
	}
	
	@Test
	public void driverIsInvalid() {
		Car car = new Car("Ford", "DD-AB-123", 4);
		//drivers name cannot be null. The validation should cascade down to the Person object.
		car.setDriver(new Person(null));
		Set<ConstraintViolation<Car>> constraintViolations = validator
				.validate(car);
		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next()
				.getMessage());
	}
	
	@Test
	public void passengersAreInvalid() {
		Car car = new Car("Ford", "DD-AB-123", 4);
		//Passengers must have a name
		car.setDriver(new Person("Gurps"));
		Collection<Person> passengers = new ArrayList<Person>();
		passengers.add(new Person(null));
		car.setPassengers(passengers);
		Set<ConstraintViolation<Car>> constraintViolations = validator
				.validate(car);
		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next()
				.getMessage());
	}


}

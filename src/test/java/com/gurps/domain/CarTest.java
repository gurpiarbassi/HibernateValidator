package com.gurps.domain;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Note how only classes from javax.validation are used and no classes
 * from hibernator validator are used therefore providing portability.
 * @author gurps
 *
 */
public class CarTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		//validator is threadsafe so can be used multiple times.
	}

	@Test
	public void manufacturerIsNull() {
		Car car = new Car(null, "DD-AB-123", 4);
		Set<ConstraintViolation<Car>> constraintViolations = validator
				.validate(car);
		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next()
				.getMessage());
	}

	@Test
	public void licensePlateTooShort() {
		Car car = new Car("Morris", "D", 4);
		Set<ConstraintViolation<Car>> constraintViolations = validator
				.validate(car);
		assertEquals(1, constraintViolations.size());
		assertEquals("size must be between 2 and 14", constraintViolations
				.iterator().next().getMessage());
	}
}

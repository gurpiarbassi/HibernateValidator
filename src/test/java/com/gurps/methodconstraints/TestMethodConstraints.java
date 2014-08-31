package com.gurps.methodconstraints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
import javax.validation.executable.ExecutableValidator;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestMethodConstraints {
	
	private static ExecutableValidator executableValidator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		executableValidator = factory.getValidator().forExecutables();
	}
	
	@Test
	public void testMethodConstraint() {
		CarFactory factory = new CarFactory();
		Method method = null;
		try {
			method = CarFactory.class.getMethod( "createCar", String.class, String.class, String.class );
		} catch (NoSuchMethodException | SecurityException e) {
			fail("Incorrect method referenced");
		}
		Object[] parameterValues = { "ford", "focus", "abcd" };
		Set<ConstraintViolation<CarFactory>> violations = executableValidator.validateParameters(
		factory, method, parameterValues);
		
		assertEquals( 1, violations.size() );
		Class<? extends Annotation> constraintType = violations.iterator().next()
																		  .getConstraintDescriptor()
																		  .getAnnotation()
																		  .annotationType();
		assertEquals( Pattern.class, constraintType );
	}
}

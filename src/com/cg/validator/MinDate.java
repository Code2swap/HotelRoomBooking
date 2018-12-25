package com.cg.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = MinDateValidator.class)
public @interface MinDate {
	String message() default "must be greater than or equal to {value}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

	String value();
	
}
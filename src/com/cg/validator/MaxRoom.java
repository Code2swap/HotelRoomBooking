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
@Constraint(validatedBy = MaxRoomValidator.class)
public @interface MaxRoom {

	String message() default "not enough available rooms";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}

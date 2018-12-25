package com.cg.validator;

import java.sql.Date;
import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class MinDateValidator implements ConstraintValidator<MinDate, Date>{

	protected String minDate;
	
	@Override
	public void initialize(MinDate minDate) {
		this.minDate = minDate.value();
	}

	@Override
	public boolean isValid(Date date, ConstraintValidatorContext ctx) {
		//int value = date.compareTo(Date.valueOf(LocalDate.now()));
		int value = date.compareTo(Date.valueOf(this.minDate));
		System.out.println("Date Comparison: " + value);
		return value >= 0;
	}

}

package com.cg.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cg.controller.HotelController;

public class MaxRoomValidator implements ConstraintValidator<MaxRoom, Integer> {

	@Override
	public void initialize(MaxRoom arg0) {
		
	}

	@Override
	public boolean isValid(Integer noOfRooms, ConstraintValidatorContext ctx) {
		return noOfRooms < HotelController.availableRooms;
	}

}

package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.bean.BookingDetail;
import com.cg.bean.Hotel;
import com.cg.exception.HotelException;
import com.cg.service.BookingService;

// main controller
@Controller
public class HotelController {

	@Autowired
	private BookingService bookingService;
	public static int availableRooms;
	// home page index.jsp
	@RequestMapping("/homePage")
	public String goHome(Model model) {
		System.out.println("Returning to Home page...");
		return "index";
	}
	
	// showing all the hotels from database
	@RequestMapping("/selectHotel")
	public String showHotel(Model model) {
		System.out.println("Selecting Hotel...");
		List<Hotel> hotelList = bookingService.getHotelList();
		hotelList.forEach(System.out::println);
		model.addAttribute("hotelList", hotelList);
		
		return "HotelDetails";
	}
	
	// submit hotel after selecting
	@RequestMapping("/submitHotel")
	public String submitHotel(@RequestParam("hotelId") int id,
			@RequestParam("hotelName") String name,
			@RequestParam("hotelRating") String rating,
			@RequestParam("hotelRate") double rate,
			@RequestParam("availableRooms") int rooms, Model model) {
		
		Hotel hotel = new Hotel(id, name, rating, rate, rooms);
		System.out.println("Selected Hotel is: " + hotel);
		availableRooms = rooms;
		
		model.addAttribute("hotel", hotel);
		model.addAttribute("bookingDetail", new BookingDetail());
		
		return "HotelBooking";
		
	}

	// enter all the data to book a hotel
	@RequestMapping("/bookHotel")
	public String register(@Valid @ModelAttribute("bookingDetail") BookingDetail detail,
			BindingResult bindingResult, Model model) {
		
		boolean success = false;
		System.out.println("Booking Hotel...");
		boolean hasError = bindingResult.hasErrors();
		System.out.println("Has Error: " + hasError);
		
		Hotel hotel = bookingService.getHotel(detail.getHotelId());
		model.addAttribute("hotel", hotel);
		System.out.println(detail);
		if(hasError) {
			// if validation failed, go to same page
			return "HotelBooking";
		}
		else {	
			
			try {
				success = bookingService.addBookingDetails(detail);
			} catch (HotelException e) {
				System.out.println(e.getMessage());
			}
			if (success) {
				System.out.println("Success...");
				System.out.println(hotel);
				model.addAttribute("details", detail);
				return "BookingConfirmation";
			}
		}
		
		return "BookingFailure";
	}
	
	// internal exceptional handler
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("err", e);
		mav.setViewName("error");
		return mav;
	}
}

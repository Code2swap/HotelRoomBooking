package com.cg.dao;

import java.util.List;

import com.cg.bean.BookingDetail;
import com.cg.bean.Hotel;
import com.cg.exception.HotelException;

public interface BookingDao {

	List<Hotel> getHotelList();
	
	boolean addBookingDetails(BookingDetail detail) throws HotelException;
	
	Hotel getHotel(int hotelId);
}

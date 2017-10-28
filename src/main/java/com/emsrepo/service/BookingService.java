package com.emsrepo.service;

import java.util.List;

import com.emsrepo.domain.Booking;

public interface BookingService {

	public boolean registerBooking(Booking booking);

	public boolean isExistingBooking(Booking booking);
	
	public Booking retrieveBooking(int bookingId);

	public Booking retrieveBooking(int uid, int eventId);

	public List<Booking> retrieveBookings(int uid);

	public void cancelBooking(int bid);

}

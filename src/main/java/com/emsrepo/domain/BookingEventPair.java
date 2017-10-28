package com.emsrepo.domain;

public class BookingEventPair {
	private Booking booking;
	private Event event;

	public BookingEventPair(Booking booking, Event event) {
		super();
		this.booking = booking;
		this.event = event;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}

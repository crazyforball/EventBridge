package junit.test;

import java.util.Date;

import com.emsrepo.domain.Booking;

import junit.framework.TestCase;


public class BookingTest extends TestCase{


	private Booking booking;

	protected void setUp() throws Exception {
		booking = new Booking();
	}

	
	public void testSetAndGetUid() {
		int testUid = 1;
		assertEquals(0, 0, 0);
		booking.setUid(1);;
		assertEquals(testUid, booking.getUid(), 0);
	}
	
	
	public void testSetAndGetEid() {
		int testEid = 1;
		assertEquals(0, 0, 0);
		booking.setEid(1);;
		assertEquals(testEid, booking.getEid(), 0);
	}
	
		public void testSetAndGetBookingDate() {
		Date testBookingDate = new Date();
		assertNull(booking.getBookingDate());
		booking.setBookingDate(testBookingDate);
		assertEquals(testBookingDate, booking.getBookingDate());
	}
	
	public void testSetAndGetBid() {
		int testBid = 1;
		assertEquals(0, 0, 0);
		booking.setBid(1);;
		assertEquals(testBid, booking.getBid(), 0);
	}
	
}

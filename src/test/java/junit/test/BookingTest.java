package junit.test;

import com.emsrepo.domain.Booking;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

import junit.framework.TestCase;

public class BookingTest extends TestCase {

	private Booking booking;

	protected void setUp() throws Exception {
		booking = new Booking();
	}

	public void testSetAndGetCreator() {
		User testCreator = new User();
		assertNull(booking.getCreator());
		booking.setCreator(testCreator);
		assertEquals(testCreator, booking.getCreator());
	}

	public void testSetAndGetEvent() {
		Event testEvent = new Event();
		assertNull(booking.getEvent());
		booking.setEvent(testEvent);
		assertEquals(testEvent, booking.getEvent());
	}

	public void testSetAndGetBookingDate() {
		String testBookingDate = "aTestBookingDate";
		// assertNull(booking.getBookingDate());
		booking.setBookingDate(testBookingDate);
		assertEquals(testBookingDate, booking.getBookingDate());
	}

	public void testSetAndGetBid() {
		int testBid = 1;
		assertEquals(0, 0, 0);
		booking.setBid(1);
		;
		assertEquals(testBid, booking.getBid(), 0);
	}

}

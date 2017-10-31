package junit.test;

import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

import junit.framework.TestCase;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:/WEB-INF/spring/*.xml","classpath*:/WEB-INF/spring/appServlet/servlet-context.xml"})
public class EventTest extends TestCase {

	// @Autowired
	// private EventDao eventDao;
	//
	// @Test
	// public void test() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testGetEvent() {
	// System.out.println(eventDao.getAllEventList().get(0).toString());
	// }

	private Event event;

	protected void setUp() throws Exception {
		event = new Event();
	}

	public void testSetAndGetEventName() {
		String testEventName = "aEventName";
		assertNull(event.getEventName());
		event.setEventName(testEventName);
		assertEquals(testEventName, event.getEventName());
	}

	public void testSetAndGetCreator() {
		User testCreator = new User();
		assertNull(event.getCreator());
		event.setCreator(testCreator);
		assertEquals(testCreator, event.getCreator());
	}

	// public void testSetAndGetCreator() {
	// String testCreator = "aCreator";
	// assertNull(event.getCreator());
	// event.setCreator(testCreator);
	// assertEquals(testCreator, event.getCreator());
	// }

	// public void testSetAndGetCreateDate() {
	// Date testCreateDate = new Date();
	// assertNull(event.getCreateDate());
	// event.setCreateDate(testCreateDate);
	// assertEquals(testCreateDate, event.getCreateDate());
	// }

	public void testSetAndGetCreateDate() {
		String testCreateDate = "aTestCreateDate";
		// assertNull(event.getCreateDate());
		event.setCreateDate(testCreateDate);
		assertEquals(testCreateDate, event.getCreateDate());
	}

	public void testSetAndGetDescription() {
		String testDescription = "aDescription";
		assertNull(event.getDescription());
		event.setDescription(testDescription);
		assertEquals(testDescription, event.getDescription());
	}

	public void testSetAndGetLocation() {
		String testLocation = "aLocation";
		assertNull(event.getLocation());
		event.setLocation(testLocation);
		assertEquals(testLocation, event.getLocation());
	}

	public void testSetAndGetStartDate() {
		String testStartDate = "aStartDate";
		assertNull(event.getStartDate());
		event.setStartDate(testStartDate);
		assertEquals(testStartDate, event.getStartDate());
	}

	public void testSetAndGetEndDate() {
		String testEndDate = "aEndDate";
		assertNull(event.getEndDate());
		event.setEndDate(testEndDate);
		assertEquals(testEndDate, event.getEndDate());
	}

	public void testSetAndGetCapacity() {
		int testCapacity = 1;
		assertEquals(0, 0, 0);
		event.setCapacity(1);
		assertEquals(testCapacity, event.getCapacity(), 0);
	}

	public void testSetAndGetFees() {
		double testFees = 100.00;
		assertEquals(0, 0, 0);
		event.setFees(100.00);
		assertEquals(testFees, event.getFees(), 0);
	}

	public void testSetAndGetCategory() {
		String testCategory = "aCategory";
		assertNull(event.getCategory());
		event.setCategory(testCategory);
		assertEquals(testCategory, event.getCategory());
	}

	public void testSetAndGetStatus() {
		String testStatus = "aStatus";
		// assertNull(event.getStatus());
		event.setStatus(testStatus);
		assertEquals(testStatus, event.getStatus());
	}

}

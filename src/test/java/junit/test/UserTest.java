package junit.test;

import com.emsrepo.domain.User;

import junit.framework.TestCase;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:/WEB-INF/spring/*.xml",
//		"classpath*:/WEB-INF/spring/appServlet/servlet-context.xml" })
public class UserTest extends TestCase {

	// @Autowired
	// private UserDao userDao;
	//
	// @Test
	// public void test() {
	// System.out.println("My Test");
	// }
	//
	// @Test
	// public void testGetUserVOList() {
	//
	// System.out.println(userDao.getUserById(1));
	// }

	private User user;

	protected void setUp() throws Exception {
		user = new User();
	}

	public void testSetAndGetUsername() {
		String testUsername = "aUsername";
		assertNull(user.getUsername());
		user.setUsername(testUsername);
		assertEquals(testUsername, user.getUsername());
	}

	public void testSetAndGetPassword() {
		String testPassword = "aPassword";
		assertNull(user.getPassword());
		user.setPassword(testPassword);
		assertEquals(testPassword, user.getPassword());
	}

	public void testSetAndGetGender() {
		int testGender = 1;
		assertEquals(0, 0, 0);
		user.setGender(1);
		assertEquals(testGender, user.getGender(), 0);
	}

	public void testSetAndGetUtype() {
		int testUtype = 1;
		assertEquals(0, 0, 0);
		user.setUtype(1);
		assertEquals(testUtype, user.getUtype(), 0);
	}

	public void testSetAndGetFirstName() {
		String testFirstName = "aFirstName";
		assertNull(user.getFirstName());
		user.setFirstName(testFirstName);
		assertEquals(testFirstName, user.getFirstName());
	}

	public void testSetAndGetLastName() {
		String testLastName = "aLastName";
		assertNull(user.getLastName());
		user.setLastName(testLastName);
		assertEquals(testLastName, user.getLastName());
	}

	public void testSetAndGetDOB() {
		String testDOB = "aDOB";
		assertNull(user.getDOB());
		user.setDOB(testDOB);
		assertEquals(testDOB, user.getDOB());
	}

	public void testSetAndGetPhoneNum() {
		String testPhoneNum = "aPhoneNum";
		assertNull(user.getPhoneNum());
		user.setPhoneNum(testPhoneNum);
		assertEquals(testPhoneNum, user.getPhoneNum());
	}

	public void testSetAndGetEmail() {
		String testEmail = "aEmail";
		assertNull(user.getEmail());
		user.setEmail(testEmail);
		assertEquals(testEmail, user.getEmail());
	}

	public void testSetAndGetPassport() {
		String testPassport = "aPassport";
		assertNull(user.getPassport());
		user.setPassport(testPassport);
		assertEquals(testPassport, user.getPassport());
	}

	public void testSetAndGetDriverLicense() {
		String testDriverLicense = "aDriverLicense";
		assertNull(user.getDriverLicense());
		user.setDriverLicense(testDriverLicense);
		assertEquals(testDriverLicense, user.getDriverLicense());
	}

	public void testSetAndGetStatus() {
		String testStatus = "aStatus";
		assertNull(user.getStatus());
		user.setStatus(testStatus);
		assertEquals(testStatus, user.getStatus());
	}
}

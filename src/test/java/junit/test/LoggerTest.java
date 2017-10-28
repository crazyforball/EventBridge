package junit.test;

import com.emsrepo.domain.Logger;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

import junit.framework.TestCase;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:/WEB-INF/spring/*.xml","classpath*:/WEB-INF/spring/appServlet/servlet-context.xml"})
public class LoggerTest extends TestCase{
	
	private Logger logger;

	protected void setUp() throws Exception {
		logger = new Logger();
	}

	public void testSetAndGetLogType() {
		String testLogType = "aLogType";
		assertNull(logger.getLogType());
		logger.setLogType(testLogType);
		;
		assertEquals(testLogType, logger.getLogType());
	}
	
	public void testSetAndGetAdminId() {
		int testAdminId = 1;
		assertEquals(0, 0, 0);
		logger.setAdminId(1);
		assertEquals(testAdminId, logger.getAdminId(), 0);
	}
	
	public void testSetAndGetEid() {
		String testEid = "aEid";
		assertNull(logger.getEid());
		logger.setEid(testEid);
		;
		assertEquals(testEid, logger.getEid());
	}

	public void testSetAndGetUid() {
		String testUid = "aUid";
		assertNull(logger.getUid());
		logger.setUid(testUid);
		;
		assertEquals(testUid, logger.getUid());
	}
	
	
	public void testSetAndGetOpt() {
		String testOpt = "aOpt";
		assertNull(logger.getOpt());
		logger.setOpt(testOpt);
		;
		assertEquals(testOpt, logger.getOpt());
	}

	public void testSetAndGetLogDate() {
		String testLogDate = "aLogDate";
		assertNull(logger.getLogDate());
		logger.setLogDate(testLogDate);
		;
		assertEquals(testLogDate, logger.getLogDate());
	}

}

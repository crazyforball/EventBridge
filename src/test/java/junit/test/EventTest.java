package junit.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emsrepo.dao.EventDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/WEB-INF/spring/*.xml","classpath*:/WEB-INF/spring/appServlet/servlet-context.xml"})
public class EventTest {

	@Autowired
	private EventDao eventDao;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEvent() {
		System.out.println(eventDao.getAllEventList().get(0).toString());
	}
}

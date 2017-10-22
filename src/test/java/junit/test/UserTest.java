package junit.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emsrepo.dao.UserDao;
import com.emsrepo.service.UserService;
import com.emsrepo.utils.CollectionUtils;
import com.emsrepo.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/WEB-INF/spring/*.xml","classpath*:/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void test() {
		System.out.println("My Test");
	}

	@Test
	public void testGetUserVOList() {
		
		System.out.println(userDao.getUserById(1));
	}
}

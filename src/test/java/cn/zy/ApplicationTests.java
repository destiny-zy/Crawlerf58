package cn.zy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zy.entity.ZJW_user;
import cn.zy.repository.ZJW_userDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

	@Autowired
	ZJW_userDao userDao;

	@Test
	public void contextLoads() {
		ZJW_user user = userDao.findOne(2L);
		System.out.println(user);
	}

}

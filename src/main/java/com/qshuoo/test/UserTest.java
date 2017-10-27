package com.qshuoo.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qshuoo.pojo.User;

public class UserTest {

	SqlSession session = null;
	
	@Before
	public void init() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("mybatis_config.xml");

		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

		session = factory.openSession();
	}

	/**
	 * 查询成功，返回userlist
	 */
	@Test
	public void listUsers() {
		
		List<User> users = session.selectList("com.qshuoo.pojo.User.listUsers");
		System.out.println(users);
		
	}
	
	
	@After
	public void destory() {
		session.commit();
		session.close();
	}

}

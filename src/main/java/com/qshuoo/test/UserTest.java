package com.qshuoo.test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jcp.xml.dsig.internal.MacOutputStream;
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
	 * 查询所有用户 查询成功，返回userlist
	 */
	@Test
	public void listUsers() {

		List<User> users = session.selectList("com.qshuoo.pojo.User.listUsers");
		System.out.println(users);

	}

	/**
	 * 根据id查询用户，查询成功
	 */
	@Test
	public void getUserById() {
		User user = session.selectOne("com.qshuoo.pojo.User.getUserById", 1);
		System.out.println(user);
	}
	
	/**
	 * 插入用户，插入成功
	 */
	@Test
	public void saveUser() {
		User user = new User();
		user.setName("ghm");
		user.setPassword("12138");
		int res = session.insert("com.qshuoo.pojo.User.saveUser", user);
		
		assertEquals(1, res);
	}
	
	/**
	 * 根据用户名和密码删除用户，删除成功
	 */
	
	@Test
	public void deleteUserByNameAndPwd() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "ghm");
		map.put("password", "12138");
		
		int res = session.delete("com.qshuoo.pojo.User.deleteUserByNameAndPwd", map);
		
		assertEquals(2, res);
	}
	
	/**
	 * 测试更新用户，测试通过
	 */
	@Test
	public void updateUserNameById() {
		User user = new User();
		user.setId(1);
		user.setName("lgt");
		int res = session.update("com.qshuoo.pojo.User.updateUserNameById", user);
		
		assertEquals(1, res);
	}
	
	@After
	public void destory() {
		session.commit();
		session.close();
	}

}

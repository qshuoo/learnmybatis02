package com.qshuoo.test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qshuoo.dao.TeacherDao;
import com.qshuoo.pojo.Teacher;

public class TeacherTest {

	SqlSession session = null;

	@Before
	public void init() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("mybatis_config.xml");

		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

		session = factory.openSession();
	}
	
	@Test
	public void saveTeacher() {
		Teacher teacher = new Teacher();
		teacher.setName("wxx");
		teacher.setAge(108);
		TeacherDao td = session.getMapper(TeacherDao.class);
		int cols = td.saveTeacher(teacher);
		assertEquals(1, cols);
		
	}
	
	@Test
	public void listTeachers() {
		TeacherDao td = session.getMapper(TeacherDao.class);
		List<Teacher> teachers = td.listTeachers();
		System.out.println(teachers);
	}
	
	@After
	public void destory() {
		session.commit();
		session.close();
	}

}

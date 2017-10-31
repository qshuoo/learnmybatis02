package com.qshuoo.test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
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
		teacher.setName("lf");
		teacher.setAge(18);
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
	
	@Test
	public void getTeacherByNameAndAge() {
		Teacher teacher = new Teacher();
		TeacherDao td = session.getMapper(TeacherDao.class);
		
		teacher.setName("wxx");
		teacher.setAge(108);
		
		List<Teacher> res = td.getTeacherByNameAndAge(teacher);
		System.out.println(res);
		
	}
	
	@Test
	public void getTeacherByNameOrAge() {
		Teacher teacher = new Teacher();
		TeacherDao td = session.getMapper(TeacherDao.class);
		
		teacher.setName("wxx");
		teacher.setAge(18);
		
		List<Teacher> res = td.getTeacherByNameOrAge(teacher);
		System.out.println(res);
	}
	
	@Test
	public void updateTeacher() {
		Teacher teacher = new Teacher();
		TeacherDao tDao = session.getMapper(TeacherDao.class);
		
		teacher.setId(4);
		teacher.setName("lgt");
		teacher.setAge(222);
		
		int rows = tDao.updateTeacher(teacher);
		
		assertEquals(1, rows);
	}
	
	@Test
	public void listTeachersByAge() {
		TeacherDao td = session.getMapper(TeacherDao.class);
		List<Integer> list = new ArrayList<Integer>();
		list.add(108);
		list.add(88);
		List<Teacher> teachers = td.listTeachersByAge(list);
		
		System.out.println(teachers);
		
	}
	
	@After
	public void destory() {
		session.commit();
		session.close();
	}

}

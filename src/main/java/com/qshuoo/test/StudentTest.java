package com.qshuoo.test;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qshuoo.pojo.Student;
import com.qshuoo.pojo.StudentInterface;

public class StudentTest {
	SqlSession session = null;

	@Before
	public void init() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("mybatis_config.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		session = factory.openSession();
	}
	

	@Test
	public void listStudents() {
		StudentInterface si = session.getMapper(StudentInterface.class);
		List<Student> students = si.liststudents();
		System.out.println(students);
	}
	
	@Test
	public void getStudentById() {
		StudentInterface si = session.getMapper(StudentInterface.class);
		Student student = si.getStudentById(1);
		System.out.println(student);
	}
	
	@Test
	public void saveStudent() {
		StudentInterface si = session.getMapper(StudentInterface.class);
		Student student = new Student();
		student.setName("ghnm");
		student.setAge(18);
		int rows = si.saveStudent(student);
		
		assertEquals(1, rows);
		
	}
	
	@After
	public void destory() {
		session.commit();
		session.close();
	}
	
}

package com.qshuoo.pojo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StudentInterface {
	/**
	 * 查询所有学生
	 * @return
	 */
	@Select("select * from student")
	List<Student> liststudents();
	
	/**
	 * 根据id查询学生
	 * @param id
	 * @return
	 */
	@Select("select * from student where id = #{id}")
	Student getStudentById(int id);
	
	/**
	 * 增加学生
	 * @param student
	 * @return
	 */
	@Insert("insert into student values (#{id}, #{name}, #{age})")
	int saveStudent(Student student);

}

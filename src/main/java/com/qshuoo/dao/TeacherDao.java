package com.qshuoo.dao;

import java.util.List;

import com.qshuoo.pojo.Teacher;

public interface TeacherDao {
	
	/**
	 * 返回所有教师
	 * @return
	 */
	List<Teacher> listTeachers();
	
	/**
	 * 保存教师
	 * @param teacher
	 * @return
	 */
	int saveTeacher(Teacher teacher);

}

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
	/**
	 * 通过名字 和 年龄查询教师
	 * @param teacher
	 * @return
	 */
	List<Teacher> getTeacherByNameAndAge(Teacher teacher);
	
	/**
	 * 通过名字 或 年龄查询老师
	 * @param teacher
	 * @return
	 */
	List<Teacher> getTeacherByNameOrAge(Teacher teacher);
	
	/**
	 * 更新老师
	 * @param teacher
	 * @return
	 */
	int updateTeacher(Teacher teacher);
	
	/**
	 * 根据年龄查询老师
	 * @param ages
	 * @return
	 */
	List<Teacher> listTeachersByAge(List<Integer> ages);

}

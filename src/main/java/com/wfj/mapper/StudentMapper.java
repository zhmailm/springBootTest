package com.wfj.mapper;

import java.util.List;

import com.wfj.bean.Student;
import com.wfj.bean.Syj;
//@Mapper
public interface StudentMapper {
	Syj getSyj(String code);
	/*
	 * 
	 * 增加
	 */
	public void savestu(Student stu);
	/*
	 * 
	 * 删除
	 */
	public void deletestu(Integer id);
	/*
	 * 
	 * 修改
	 */
	public void updatestu(Student stu);
	/*
	 * 
	 * 查找
	 */
	public List<Student> selectstu();
	/*
	 * 
	 * 查找一个
	 */
	public Student selectStuOne(Integer id);
	
}

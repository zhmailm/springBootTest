package com.wfj.service.inft;

import java.util.List;

import com.wfj.bean.Student;
import com.wfj.bean.Syj;
public interface IStudentService {
	public Syj getSyj(String code);
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
	
	public String sendSimpleMail(String sendFrom,String sendTo, String titel, String content);
	
	public String rollbackTest(Integer id);
}

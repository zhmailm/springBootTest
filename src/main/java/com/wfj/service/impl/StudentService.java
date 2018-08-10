package com.wfj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wfj.bean.Student;
import com.wfj.bean.Syj;
import com.wfj.mapper.StudentMapper;
import com.wfj.service.inft.IStudentService;
import com.wfj.util.DynamicTargetDataSource;

@Service
public class StudentService implements IStudentService {
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	@DynamicTargetDataSource("ds2")
	public Syj getSyj(String code) {
		System.out.println("6000库");
		return studentMapper.getSyj(code);
	}

	@Override
	public void savestu(Student stu) {
		studentMapper.savestu(stu);

	}

	@Override
	public void deletestu(Integer id) {
		studentMapper.deletestu(id);

	}

	@Override
	public void updatestu(Student stu) {

		studentMapper.updatestu(stu);

	}

	@Override
	@DynamicTargetDataSource("ds1")
	public List<Student> selectstu() {
		System.out.println("ceshi库");
		List<Student> list = studentMapper.selectstu();
		return list;
	}

	@Override
	public Student selectStuOne(Integer id) {
		System.out.println("test库");
		Student student = studentMapper.selectStuOne(id);
		return student;
	}

	@Override
	public String sendSimpleMail(String sendFrom,String sendTo, String titel, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sendFrom);
		message.setTo(sendTo);
		message.setSubject(titel);
		message.setText(content);
		try {
			mailSender.send(message);
		} catch (MailException e) {			
			e.printStackTrace();
			return "发送失败      "+e.getLocalizedMessage();
		}
		return "发送成功";
	}
	@Override
	@Transactional//数据库中表的类型必须是InnoDB类型
	public String rollbackTest(Integer id) {
		String res="正常查询";
		try {
			Student student1 = studentMapper.selectStuOne(id);
			student1.setSlikes("555");
		    System.out.println(student1.toString());
			studentMapper.updatestu(student1);		
		} catch (Exception e) {
			res="已会滚";	
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回操作
		}
		return res;
	}

}

package com.wfj.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.wfj.bean.Student;
import com.wfj.bean.Syj;
import com.wfj.service.inft.IStudentService;
import com.wfj.util.PropertiesListenerConfig;
import com.wfj.util.Task;

@RestController
@RequestMapping("stu")
public class StudnentController {
	@Autowired
	private IStudentService studentService;
	@Autowired
	private Task task;
	@Autowired
	private Environment environment;
	public static final Logger logger = LoggerFactory.getLogger(StudnentController.class.toString());

	public static Object readRequest(HttpServletRequest request) {
		String str = "";
		String readerStr = "";
		try {
			InputStreamReader in = new InputStreamReader(
					request.getInputStream(), "utf8");// getInputStream方法返回一个代表实体内容的输入流对象
			BufferedReader reader = new BufferedReader(in);
			while ((str = reader.readLine()) != null) {
				readerStr = readerStr + str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("传入的接口为 :" + request.getRequestURL());
		logger.info("传入的参数为 :" + readerStr);
		return readerStr;
	}		
	/**
	 * 返回参数properties中的信息
	 */
	@RequestMapping("/getProperties")
	public String getProperties(Map<String,Object> map){
		String hjUrl=environment.getProperty("hjUrl");
		return "本服务环境为="+hjUrl;
	}
	// 获取syj接口
	@RequestMapping(value = "/getSyj", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public String getSyj(HttpServletRequest request) {
		String readerStr = (String) readRequest(request);
		JSONObject jsonObj = JSONObject.fromObject(readerStr);
		String code = jsonObj.getString("code");
		Syj syj = studentService.getSyj(code);
		return JSONSerializer.toJSON(syj).toString();

	}

	// 测试事物的手动回滚
	@RequestMapping(value = "/rollbackTest", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public String rollbackTest(HttpServletRequest request) {
		String readerStr = (String) readRequest(request);
		JSONObject jsonObj = JSONObject.fromObject(readerStr);
		String id = jsonObj.getString("id");
		String s=studentService.rollbackTest(Integer.valueOf(id));
		return s;

	}

	/*
	 * 
	 * 查询所有学生信息
	 */
	@ApiOperation(value = "获取所有学生列表", notes = "")
	@RequestMapping("/querystu")
	public List<Student> querystu(HttpServletRequest request) {
		PageHelper.startPage(1, 2);// 第1页，每页数量为2
		List<Student> stulist = studentService.selectstu();
		return stulist;
	}

	/*
	 * (以Json形式POST接收) 根据ID查询学生信息
	 */
	@ApiOperation(value = "根据ID查询学生信息", notes = "")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping("/selectStu")
	public Student selectStu(HttpServletRequest request) {
		String readerStr = (String) readRequest(request);
		JSONObject jsonObj = JSONObject.fromObject(readerStr);
		String id = jsonObj.getString("id");
		Student stu = studentService.selectStuOne(Integer.valueOf(id));
		return stu;
	}

	/*
	 * (以Parameter形式get接收) 根据ID查询学生信息
	 */
	@RequestMapping(value = "/selectStu1")
	public Student selectStu1(HttpServletRequest request) {
		String id = request.getParameter("id");
		Student stu = studentService.selectStuOne(Integer.valueOf(id));
		return stu;
	}

	/*
	 * 
	 * 发送邮件
	 */
	@RequestMapping(value = "/getTestDemoEmail")
	public String getTestDemoEmail(HttpServletRequest request) {
		String sendFrom = "18317893263@163.com";
		String sendTo = "zhmailm@163.com";
		String titel = "测试邮件标题";
		String content = "测试邮件内容";
		return studentService.sendSimpleMail(sendFrom, sendTo, titel, content);
	}

	@RequestMapping("/hello")
	String home() {
		return "Hello World!";
	}
	@RequestMapping("/listener")
    public Map<String, Object> listener() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(PropertiesListenerConfig.getAllProperty());
        return map;
    }

	@RequestMapping("/hello/{myName}")
	String index(@PathVariable String myName) {
		logger.debug("访问index");
		return "Hello " + myName + "!!!";
	}

	@RequestMapping("/zeroException")
	public int zeroException() {
		return 100 / 0;
	}

	@RequestMapping("/task")
	public String task() throws Exception {
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
		System.out.println("异步程序执行成功");
		return "task";
	}
}

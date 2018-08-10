package com.wfj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 模板测试.
 * @author Administrator
 *
 */
@Controller
public class TemplateController {
	/**
	 * 返回html模板.
	 */
	@RequestMapping("/helloHtml")
	public String helloHtml(Map<String,Object> map){
		map.put("hello","from TemplateController999.helloHtml");
		return "helloHtml";
	}
	/**
	 * 返回html模板.
	 */
	@RequestMapping("/miaoHtml")
	public String miaoHtml(){
		return "miao";
	}
	/**
	 * 返回html简历模板.
	 */
	@RequestMapping("/miao")
	public ModelAndView jianliHtml(){
		ModelAndView mv = new ModelAndView("miao");  
		return mv;
	}
	/**
	 * 返回html模板.
	 */
	@RequestMapping("/helloHtml1")
	public ModelAndView  helloHtml1(){
		ModelAndView mv = new ModelAndView("helloHtml");  
		mv.addObject("hello","from TemplateController.helloHtml1");
		return mv;
	}
	
	
	/**
	 * 返回JSP模板.
	 */
	@RequestMapping("/helloJsp")
	public String helloFtl(Map<String,Object> map){
		map.put("hello","from TemplateController.helloFtl");
		return "helloJsp";
	}
}

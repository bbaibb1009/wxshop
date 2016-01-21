package com.wxshop.test;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController 
{
	@RequestMapping("/test")
	public String test(HttpServletResponse response, HttpSession session, Model model)
	{
		return "/test/test" ;
	}
	
	
	
	
}

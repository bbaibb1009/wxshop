package com.wxshop.my;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxshop.match.WcMacthActivity;

@Controller
@RequestMapping("/my")
public class MyController {
	
	
	/**
	 * 
	 * from : 1 从平台发布的 2 企业号应用发布的
	 * */
	@RequestMapping(value = "/toAddActivity/{from}")
	public String toAddActivity(@ModelAttribute("command") WcMacthActivity activity,@PathVariable String from,	 Model model)
	{
		
		return "/my/addActivity";
	}
	
	@RequestMapping(value = "/toIcon")
	public String toIcon( Model model)
	{
		
		return "/my/icon";
	}
}

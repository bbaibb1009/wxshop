package com.wxshop.my;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyController {
	
	@RequestMapping(value = "/activity")
	public String activity(Model model)
	{
		return "/my/activityList";
	}
}

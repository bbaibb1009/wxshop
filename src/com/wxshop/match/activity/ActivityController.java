package com.wxshop.match.activity;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@RequestMapping(value = "/activity")
	public String activity(Model model)
	{
		
		return "/my/activityList";
	}
}

package com.wxshop.match.activity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxshop.match.WcMacthActivity;
import com.wxshop.util.SysConstant;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired 
	private IActivityService activityService;
	
	@RequestMapping(value = "/queryActivity")
	public String queryActivity(@ModelAttribute("command") WcMacthActivity activity ,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,activityService.queryActivity(activity) );
		return "/activity/queryActivity";
	}
}

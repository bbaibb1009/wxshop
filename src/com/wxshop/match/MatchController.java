package com.wxshop.match;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxshop.util.SysConstant;
import com.wxshop.weichat.fuwuhao.IWeiFuwuhaoService;

@Controller
@RequestMapping("/match")
public class MatchController 
{
	@Autowired
	private IWeiFuwuhaoService fuwuhaoService;
	
	@Autowired
	private IMatchService matchService;
	
	@RequestMapping("/")
	public String index(@ModelAttribute("command") WcMatch match, HttpSession session, Model model)
	{
		return "/match/index" ;
	}
	
	@RequestMapping("/queryMatch")
	public String queryMatch(@ModelAttribute("command") WcMatch match, HttpSession session, Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,matchService.queryMatch(match));
		return "/match/queryMatch";
	}
	
	
	
}

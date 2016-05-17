package com.wxshop.weixin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxshop.util.SysConstant;

@Controller
@RequestMapping("/fuwuhao")
public class FuwuhaoController 
{
	@Autowired
	private IWeiFuwuhaoService fuwuhaoService;
	
	@RequestMapping("/queryFuwuhao")
	public String queryFuwuhao(@ModelAttribute("command") WcWeiFuwuhao fuwuhao, HttpSession session, Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,fuwuhaoService.queryFuwuhao(fuwuhao));
		return "/fuwuhao/queryFuwuhao" ;
	}
	
	
	
	
}

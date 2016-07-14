package com.wxshop.weixin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxshop.util.StringUtil;
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
	
	
	
	
	/**
	 * @功能介绍 跳转至修改菜单
	 * 
	 * */
	@RequestMapping(value ="/toUpdFuwuhao",method = RequestMethod.POST)
	public String toUpdFuwuhao(WcWeiFuwuhao fuwuhao_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcWeiFuwuhao fuwuhao = fuwuhaoService.getFuwuhaoById(fuwuhao_Q.getFwhId());
		StringUtil.copyProperties(fuwuhao_Q, fuwuhao);
		model.addAttribute("command", fuwuhao);
		return "/fuwuhao/updFuwuhao";
	} 
	
	
	/**
	 * @throws IOException 
	 * @throws JsonGenerationException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @功能介绍 修改菜单保存
	 * */
	@RequestMapping(value="/updFuwuhao",method = RequestMethod.POST)
	public String updFuwuhao(WcWeiFuwuhao fuwuhao_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
	{
		fuwuhaoService.updFuwuhao(fuwuhao_Q);
		redirectAttributes.addFlashAttribute("success", "公众号修改成功!");
		return "redirect:/fuwuhao/queryFuwuhao";
	}
}

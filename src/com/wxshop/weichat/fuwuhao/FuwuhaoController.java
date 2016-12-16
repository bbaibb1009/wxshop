package com.wxshop.weichat.fuwuhao;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxshop.sys.WcShopAdmin;
import com.wxshop.util.DateUtil;
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
	 * @���ܽ��� ��ת���޸Ĳ˵�
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
	 * @���ܽ��� �޸Ĳ˵�����
	 * */
	@RequestMapping(value="/updFuwuhao",method = RequestMethod.POST)
	public String updFuwuhao(WcWeiFuwuhao fuwuhao_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
	{
		fuwuhaoService.updFuwuhao(fuwuhao_Q);
		redirectAttributes.addFlashAttribute("success", "���ں��޸ĳɹ�!");
		return "redirect:/fuwuhao/queryFuwuhao";
	}
	
	
	
	@RequestMapping("/toAddFuwuhao")
	public String toAddFuwuhao(@ModelAttribute("command") WcWeiFuwuhao fuwuhao, Model model)
	{
		return "/fuwuhao/addFuwuhao";
	}
	
	@RequestMapping(value="/addFuwuhao",method = RequestMethod.POST)
	public String addFuwuhao(WcWeiFuwuhao fuwuhao_Q, HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes)throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
	{
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		fuwuhao_Q.setFwhStatus("1000");
		fuwuhao_Q.setFwhRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		fuwuhao_Q.setFwhRegistor(admin.getWsaId());
		fuwuhaoService.addFuWuhao(fuwuhao_Q);
		redirectAttributes.addFlashAttribute("success", "���ں���ӳɹ�!");
		return "redirect:/fuwuhao/queryFuwuhao";
	}
	
	
}

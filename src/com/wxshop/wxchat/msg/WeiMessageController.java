package com.wxshop.wxchat.msg;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.wxshop.common.IMemcachedService;
import com.wxshop.sys.WcShopAdmin;
import com.wxshop.util.DateUtil;
import com.wxshop.util.StringUtil;
import com.wxshop.util.SysConstant;
import com.wxshop.weixin.IWeiFuwuhaoService;
import com.wxshop.weixin.IWeixinMessageService;
import com.wxshop.weixin.WcWeiFuwuhao;

@Controller
@RequestMapping("/wxmsg")
public class WeiMessageController 
{
	@Autowired
	private IWeixinMessageService weiMessageService;
	
	@Autowired 
	private IMemcachedService memcachedservice;
	
	@Autowired
	private IWeiFuwuhaoService weixinFuwuhaoService;
	
	
	@RequestMapping("/queryWcWeiMessage")
	public String queryWcWeiMessage(@ModelAttribute("command") WcWeiMessage msg, HttpServletResponse response, HttpSession session, Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,weiMessageService.queryWcWeiMsg(msg));
		return "/wxmsg/queryWcWeiMessage";
	}
	
	@RequestMapping("/queryWcWeiMessage/{appId}")
	public String queryWcWeiMessageById(@ModelAttribute("command") WcWeiMessage msg, @PathVariable String  appId,HttpSession session, Model model)
	{
		WcWeiFuwuhao fuwuhao = weixinFuwuhaoService.getWeiFwhByAppId(appId);
		msg.setWmgAppId_Q(appId);
		model.addAttribute(SysConstant.PAGE_RESULT,weiMessageService.queryWcWeiMsg(msg));
		model.addAttribute("command",msg);
		model.addAttribute("fuwuhao", fuwuhao);
		return "/wxmsg/queryWcWeiMessage" ;
	}
	
	/**
	 * @���ܽ��� ��ת���޸Ĳ˵�
	 * 
	 * */
	@RequestMapping(value ="/toUpdWxMsg",method = RequestMethod.POST)
	public String toUpdWxMsg(WcWeiMessage wxmsg_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcWeiMessage wxmsg = weiMessageService.getWcWeiMessageById(wxmsg_Q.getWmgId());
		StringUtil.copyProperties(wxmsg_Q, wxmsg);
		model.addAttribute("command", wxmsg);
		return "/wxmsg/updWxMsg";
	} 
	
	/**
	 * @throws IOException 
	 * @throws JsonGenerationException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @���ܽ��� �޸Ĳ˵�����
	 * */
	@RequestMapping(value="/updWxMsg",method = RequestMethod.POST)
	public String updWxMsg(WcWeiMessage wxmsg_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
	{
		weiMessageService.updWxMsg(wxmsg_Q);
		redirectAttributes.addFlashAttribute("success", "�ظ���Ϣ�޸ĳɹ�!");
		return "redirect:/wxmsg/queryWcWeiMessage/"+wxmsg_Q.getWmgAppId_Q();
	}

	/**
	 * @���ܽ��� ��ת���޸Ĳ˵�
	 * 
	 * */
	@RequestMapping(value ="/toAddWxMsg",method = RequestMethod.POST)
	public String toAddWxMsg(@ModelAttribute("command") WcWeiMessage msg , Model model) throws IllegalArgumentException, IllegalAccessException
	{  
		if(msg.getWmgAppId_Q()!=null)
		{
			msg.setWmgAppId(msg.getWmgAppId_Q());
		}
		return "/wxmsg/addWxMsg";
	} 
	
	@RequestMapping(value ="/addWxMsg", method = RequestMethod.POST)
	public String addWxMsg(WcWeiMessage msg, HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
		WcShopAdmin adminReg = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		msg.setWmgRegistor(adminReg.getWsaId());
		msg.setWmgRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		msg.setWmgStatus("1000");
		weiMessageService.addWcWeiMessage(msg);
		redirectAttributes.addFlashAttribute("success", "�ظ���Ϣ��ӳɹ�!");
		return "redirect:/wxmsg/queryWcWeiMessage/"+msg.getWmgAppId_Q();
	}
	
	
}

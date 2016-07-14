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
import com.wxshop.util.SysConstant;
import com.wxshop.weixin.IWeixinMessageService;
import com.wxshop.weixin.IWeixinService;
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
	private IWeixinService weixinFuwuhaoService;
	
	
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
		return "/wxmsg/queryWcWeiMessage" ;
	}
	
//	/**
//	 * @���ܽ��� ��ת���޸Ĳ˵�
//	 * 
//	 * */
//	@RequestMapping(value ="/toUpdShopAdmin",method = RequestMethod.POST)
//	public String toUpdShopAdmin(WcWeiMessage admin_Q, Model model) throws IllegalArgumentException, IllegalAccessException
//	{
//		WcShopAdmin shopadmin = adminService.getShopAdminById(admin_Q.getWsaId());
//		StringUtil.copyProperties(admin_Q, shopadmin);
//		model.addAttribute("command", shopadmin);
//		model.addAttribute("roleList1", roleService.queryShopRoleForAdminUpd2(admin_Q.getWsaId()));
//		return "/admin/updShopAdmin";
//	} 
//	
//	/**
//	 * @throws IOException 
//	 * @throws JsonGenerationException 
//	 * @throws JsonMappingException 
//	 * @throws JsonParseException 
//	 * @���ܽ��� �޸Ĳ˵�����
//	 * */
//	@RequestMapping(value="/updShopAdmin",method = RequestMethod.POST)
//	public String updShopAdmin(WcShopAdmin admin_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
//	{
//		adminService.updShopAdmin(admin_Q);
//		redirectAttributes.addFlashAttribute("success", "�˺��޸ĳɹ�!");
//		return "redirect:/shop/admin/queryShopAdmin";
//	}
//
	/**
	 * @���ܽ��� ��ת���޸Ĳ˵�
	 * 
	 * */
	@RequestMapping(value ="/toAddWxMsg",method = RequestMethod.POST)
	public String toAddWxMsg(Model model) throws IllegalArgumentException, IllegalAccessException
	{  
		WcWeiMessage msg = new WcWeiMessage();
		model.addAttribute("command", msg);
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
		return "redirect:/wxmsg/queryWcWeiMessage";
	}
	
	
}

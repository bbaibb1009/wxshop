package com.wxshop.weichat.msg.manage;

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
import com.wxshop.weichat.fuwuhao.IWeiFuwuhaoService;
import com.wxshop.weichat.fuwuhao.WcWeiFuwuhao;
import com.wxshop.weichat.msg.request.IWeixinMessageService;
import com.wxshop.wxchat.msg.WcWeiMessage;

@Controller
@RequestMapping("/wxmsg")
public class WeiMsgManageController 
{
	@Autowired
	private IWeixinMsgManageService weiMsgManageService;
	
	@Autowired 
	private IMemcachedService memcachedservice;
	
	@Autowired
	private IWeiFuwuhaoService weixinFuwuhaoService;
	
	
	@RequestMapping("/queryWcWeiMessage")
	public String queryWcWeiMessage(@ModelAttribute("command") WcWeiMessage msg, HttpServletResponse response, HttpSession session, Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,weiMsgManageService.queryWcWeiMsg(msg));
		return "/wxmsg/queryWcWeiMessage";
	}
	
	@RequestMapping("/queryWcWeiMessage/{appId}")
	public String queryWcWeiMessageById(@ModelAttribute("command") WcWeiMessage msg, @PathVariable String  appId,HttpSession session, Model model)
	{
		WcWeiFuwuhao fuwuhao = weixinFuwuhaoService.getWeiFwhByAppId(appId);
		msg.setWmgAppId_Q(appId);
		model.addAttribute(SysConstant.PAGE_RESULT,weiMsgManageService.queryKeyWordMsgByAppId(msg));
		model.addAttribute("subscribeMsg", weiMsgManageService.getSubScribeMsgByApp(appId));
		model.addAttribute("command",msg);
		model.addAttribute("fuwuhao", fuwuhao);
		return "/wxmsg/queryWcWeiMessage" ;
	}
	
	/**
	 * @功能介绍 跳转至修改菜单
	 * 
	 * */
	@RequestMapping(value ="/toUpdWxMsg",method = RequestMethod.POST)
	public String toUpdWxMsg(WcWeiMessage wxmsg_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcWeiMessage wxmsg = weiMsgManageService.getWcWeiMessageById(wxmsg_Q.getWmgId());
		StringUtil.copyProperties(wxmsg_Q, wxmsg);
		model.addAttribute("command", wxmsg);
		return "/wxmsg/updWxMsg";
	} 
	
	/**
	 * @throws IOException 
	 * @throws JsonGenerationException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @功能介绍 修改菜单保存
	 * */
	@RequestMapping(value="/updWxMsg",method = RequestMethod.POST)
	public String updWxMsg(WcWeiMessage wxmsg_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
	{
		weiMsgManageService.updWxMsg(wxmsg_Q);
		redirectAttributes.addFlashAttribute("success", "回复消息修改成功!");
		return "redirect:/wxmsg/queryWcWeiMessage/"+wxmsg_Q.getWmgAppId_Q();
	}

	/**
	 * @功能介绍 跳转至修改菜单
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
		weiMsgManageService.addWcWeiMessage(msg);
		redirectAttributes.addFlashAttribute("success", "回复消息添加成功!");
		return "redirect:/wxmsg/queryWcWeiMessage/"+msg.getWmgAppId_Q();
	}
	
	
	/**
	 * @功能介绍 跳转至添加关键字菜单
	 * 
	 * */
	@RequestMapping(value ="/toAddWxkeyWordMsg/{appId}",method = RequestMethod.POST)
	public String toAddWxkeyWordMsg(@ModelAttribute("command") WcWeiMessage msg ,@PathVariable String appId, Model model) throws IllegalArgumentException, IllegalAccessException
	{  
		WcWeiFuwuhao fuwuhao = weixinFuwuhaoService.getWeiFwhByAppId(appId);
		msg.setWmgAppId(appId);
		msg.setWmgReplyType("1");//关键字回复 固定类型
		model.addAttribute("fuwuhao", fuwuhao);
		return "/wxmsg/addWxKeywordMsg";
	} 
	
	
	@RequestMapping(value ="/addWxKeywordMsg", method = RequestMethod.POST)
	public String addWxKeywordMsg(WcWeiMessage msg, HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
		String keyWord =  msg.getWmgKeyWord();
		if(keyWord==null || keyWord.trim().length()==0)
		{
			redirectAttributes.addFlashAttribute("error", "关键字不能为空!");
			return "redirect:/wxmsg/queryWcWeiMessage/"+msg.getWmgAppId_Q();
		}
		
		//先添加一条消息
		WcShopAdmin adminReg = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		msg.setWmgRegistor(adminReg.getWsaId());
		msg.setWmgRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		msg.setWmgStatus("1000");
		weiMsgManageService.addWcWeiMessage(msg);
		//添加完 然后再勾连关系
		String[] keyWordArray = keyWord.split(",");
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		weiMsgManageService.addWcKeywordMessage(msg,keyWordArray,admin.getWsaId());
		
		redirectAttributes.addFlashAttribute("success", "关键字回复消息添加成功!");
		return "redirect:/wxmsg/queryWcWeiMessage/"+msg.getWmgAppId();
	}
}

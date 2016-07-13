package com.wxshop.wxchat.msg;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxshop.common.IMemcachedService;
import com.wxshop.util.SysConstant;
import com.wxshop.weixin.IWeixinMessageService;

@Controller
@RequestMapping("/wxmsg")
public class WeiMessageController 
{
	@Autowired
	private IWeixinMessageService weiMessageService;
	
	@Autowired 
	private IMemcachedService memcachedservice;
	

		
	@RequestMapping("/queryWcWeiMessage")
	public String queryWcWeiMessage(@ModelAttribute("command") WcWeiMessage msg, HttpServletResponse response, HttpSession session, Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,weiMessageService.queryWcWeiMsg(msg));
		return "/wxmsg/queryWcWeiMessage";
	}
//	/**
//	 * @功能介绍 跳转至修改菜单
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
//	 * @功能介绍 修改菜单保存
//	 * */
//	@RequestMapping(value="/updShopAdmin",method = RequestMethod.POST)
//	public String updShopAdmin(WcShopAdmin admin_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
//	{
//		adminService.updShopAdmin(admin_Q);
//		redirectAttributes.addFlashAttribute("success", "账号修改成功!");
//		return "redirect:/shop/admin/queryShopAdmin";
//	}
//
//	/**
//	 * @功能介绍 跳转至修改菜单
//	 * 
//	 * */
//	@RequestMapping(value ="/toAddShopAdmin",method = RequestMethod.POST)
//	public String toAddShopAdmin(Model model) throws IllegalArgumentException, IllegalAccessException
//	{  
//		WcShopAdmin admin = new WcShopAdmin();
//		model.addAttribute("command", admin);
//		model.addAttribute("roleList", roleService.queryShopRoleForAdminAdd());
//		return "/admin/addShopAdmin";
//	} 
//	
//	@RequestMapping(value ="/addAdmin", method = RequestMethod.POST)
//	public String addAdmin(WcShopAdmin admin, HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes)
//	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException
//	{
//		WcShopAdmin adminReg = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
//		admin.setWsaRegistor(adminReg.getWsaId());
//		admin.setWsaLogindate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		admin.setWsaStatus("1");
//		adminService.addShopAdmin(admin);
//		redirectAttributes.addFlashAttribute("success", "账号添加成功!");
//		return "redirect:/shop/admin/queryShopAdmin";
//	}
	
	
}

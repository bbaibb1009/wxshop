package com.wxshop.shop.admin;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxshop.common.IMemcachedService;
import com.wxshop.sys.IShopAdminService;
import com.wxshop.sys.IShopRoleService;
import com.wxshop.sys.WcShopAdmin;
import com.wxshop.util.DateUtil;
import com.wxshop.util.StringUtil;
import com.wxshop.util.SysConstant;

@Controller
@RequestMapping("/shop/admin")
public class ShopAdminController 
{
	@Autowired
	private IShopAdminService adminService;
	
	@Autowired 
	private IMemcachedService memcachedservice;
	
	@Autowired
	private IShopRoleService roleService;
		
	@RequestMapping("/queryShopAdmin")
	public String queryShopAdmin(@ModelAttribute("command") WcShopAdmin admin, HttpServletResponse response, HttpSession session, Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,adminService.queryShopAdmin(admin));
		return "/admin/queryShopAdmin";
	}
	/**
	 * @���ܽ��� ��ת���޸Ĳ˵�
	 * 
	 * */
	@RequestMapping(value ="/toUpdShopAdmin",method = RequestMethod.POST)
	public String toUpdShopAdmin(WcShopAdmin admin_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcShopAdmin shopadmin = adminService.getShopAdminById(admin_Q.getWsaId());
		StringUtil.copyProperties(admin_Q, shopadmin);
		model.addAttribute("command", shopadmin);
		model.addAttribute("roleList1", roleService.queryShopRoleForAdminUpd2(admin_Q.getWsaId()));
		return "/admin/updShopAdmin";
	} 
	
	/**
	 * @throws IOException 
	 * @throws JsonGenerationException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @���ܽ��� �޸Ĳ˵�����
	 * */
	@RequestMapping(value="/updShopAdmin",method = RequestMethod.POST)
	public String updShopAdmin(WcShopAdmin admin_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
	{
		adminService.updShopAdmin(admin_Q);
		redirectAttributes.addFlashAttribute("success", "�˺��޸ĳɹ�!");
		return "redirect:/shop/admin/queryShopAdmin";
	}

	/**
	 * @���ܽ��� ��ת���޸Ĳ˵�
	 * 
	 * */
	@RequestMapping(value ="/toAddShopAdmin",method = RequestMethod.POST)
	public String toAddShopAdmin(Model model) throws IllegalArgumentException, IllegalAccessException
	{  
		WcShopAdmin admin = new WcShopAdmin();
		model.addAttribute("command", admin);
		model.addAttribute("roleList", roleService.queryShopRoleForAdminAdd());
		return "/admin/addShopAdmin";
	} 
	
	@RequestMapping(value ="/addAdmin", method = RequestMethod.POST)
	public String addAdmin(WcShopAdmin admin, HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
		WcShopAdmin adminReg = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		admin.setWsaRegistor(adminReg.getWsaId());
		admin.setWsaLogindate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		admin.setWsaStatus("1");
		adminService.addShopAdmin(admin);
		redirectAttributes.addFlashAttribute("success", "�˺���ӳɹ�!");
		return "redirect:/shop/admin/queryShopAdmin";
	}
	
	
}

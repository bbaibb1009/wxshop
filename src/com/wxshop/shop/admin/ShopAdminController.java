package com.wxshop.shop.admin;

import java.io.IOException;

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
import com.wxshop.sys.WcShopRole;
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
	 * @功能介绍 跳转至修改菜单
	 * 
	 * */
	@RequestMapping(value ="/toUpdShopAdmin",method = RequestMethod.POST)
	public String toUpdShopAdmin(WcShopAdmin admin_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcShopAdmin shopadmin = adminService.getShopAdminById(admin_Q.getWsaId());
		StringUtil.copyProperties(admin_Q, shopadmin);
		model.addAttribute("command", shopadmin);
		model.addAttribute("roleList1", roleService.queryShopRoleForAdminUpd2(admin_Q.getWsaId()));
		return "/admin/updShopAdmin2";
	} 
	
	/**
	 * @throws IOException 
	 * @throws JsonGenerationException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @功能介绍 修改菜单保存
	 * */
	@RequestMapping(value="/updShopAdmin",method = RequestMethod.POST)
	public String updShopAdmin(WcShopAdmin admin_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
	{
		
		adminService.updShopAdmin(admin_Q);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "菜单修改成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shop/admin/queryShopAdmin", admin_Q));
		return "redirect:/admin/toMsg";
	}
	
}

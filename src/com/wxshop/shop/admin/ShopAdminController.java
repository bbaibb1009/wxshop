package com.wxshop.shop.admin;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxshop.common.IMemcachedService;
import com.wxshop.sys.IShopAdminService;
import com.wxshop.sys.WcShopAdmin;
import com.wxshop.util.SysConstant;

@Controller
@RequestMapping("/shop/admin")
public class ShopAdminController 
{
	@Autowired
	private IShopAdminService adminService;
	
	@Autowired 
	private IMemcachedService memcachedservice;
		
	@RequestMapping("/queryShopAdmin")
	public String queryShopAdmin(@ModelAttribute("command") WcShopAdmin admin, HttpServletResponse response, HttpSession session, Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,adminService.queryShopAdmin(admin));
		return "/admin/queryShopAdmin";
	}

	
}

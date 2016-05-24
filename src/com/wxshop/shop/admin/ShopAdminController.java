package com.wxshop.shop.admin;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wxshop.common.IMemcachedService;
import com.wxshop.sys.IShopAdminService;
import com.wxshop.sys.IShopRoleService;
import com.wxshop.sys.WcShopAdmin;
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
		return "/admin/updShopAdmin2";
	} 
}

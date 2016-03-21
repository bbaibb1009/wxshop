package com.wxshop.shop.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxshop.common.IMemcachedService;
import com.wxshop.sys.IShopAdminService;
import com.wxshop.sys.WcShopAdmin;
import com.wxshop.sys.WcShopMenu;
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
		
	@RequestMapping("/queryShopAdmin")
	public String queryShopAdmin(@ModelAttribute("command") WcShopAdmin admin, HttpServletResponse response, HttpSession session, Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,adminService.queryShopAdmin(admin));
		return "/admin/queryShopAdmin";
	}

	
}

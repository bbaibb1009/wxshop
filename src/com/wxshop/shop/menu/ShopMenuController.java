package com.wxshop.shop.menu;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxshop.sys.IShopMenuService;
import com.wxshop.sys.WcShopMenu;
import com.wxshop.util.SysConstant;

@Controller
@RequestMapping("/shop/menu")
public class ShopMenuController 
{
	@Autowired
	private IShopMenuService menuService;
		
	@RequestMapping("/queryMenu")
	public String queryMenu(@ModelAttribute("command") WcShopMenu menu, HttpServletResponse response, HttpSession session, Model model)
	{
		//先把菜单查出来
		model.addAttribute(SysConstant.PAGE_RESULT,menuService.queryShopMenu(menu));
		return "/menu/queryShopMenu";
	}
	
	@RequestMapping("/toAddShopMenu")
	public String toAddShopMenu()
	{
		//跳转到添加菜单
		return "/menu/addShopMenu";
	}
	
	
	
}

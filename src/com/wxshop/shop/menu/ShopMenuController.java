package com.wxshop.shop.menu;

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
import com.wxshop.sys.IShopMenuService;
import com.wxshop.sys.IShopRoleService;
import com.wxshop.sys.WcShopAdmin;
import com.wxshop.sys.WcShopMenu;
import com.wxshop.util.DateUtil;
import com.wxshop.util.StringUtil;
import com.wxshop.util.SysConstant;

@Controller
@RequestMapping("/shop/menu")
public class ShopMenuController 
{
	@Autowired
	private IShopMenuService menuService;
	
	@Autowired
	private IShopRoleService roleService;
	
	@Autowired 
	private IMemcachedService memcachedservice;
		
	@RequestMapping("/queryMenu")
	public String queryMenu(@ModelAttribute("command") WcShopMenu menu, HttpServletResponse response, HttpSession session, Model model)
	{
		//先把菜单查出来
		model.addAttribute(SysConstant.PAGE_RESULT,menuService.queryShopMenu(menu));
		return "/menu/queryShopMenu";
	}
	
	@RequestMapping("/toAddShopMenu")
	public String toAddShopMenu(@ModelAttribute("command") WcShopMenu menu,Model mode)
	{
		//跳转到添加菜单
		return "/menu/addShopMenu";
	}

	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public String addMenu(WcShopMenu menu, HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException
	{
		//跳转到添加菜单
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		menu.setWsmRegistor(admin.getWsaId());
		menu.setWsmRegistDate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		menuService.addMenu(menu);
		redirectAttributes.addFlashAttribute("msgCode",    "2");
		redirectAttributes.addFlashAttribute("alertMsg",   "菜单添加成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/shop/menu/queryMenu"));
		return "redirect:/admin/toMsg";
	}
	
	
	@RequestMapping(value = "/delShopMenu", method = RequestMethod.POST)
	public String delAccount(WcShopMenu menu, HttpServletRequest request,RedirectAttributes redirectAttributes)	throws IllegalArgumentException, IllegalAccessException {
		menuService.delMenu(menu.getWsmIds());
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "菜单删除成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shop/menu/queryMenu", menu));
		return "redirect:/admin/toMsg";
	}
	
	/**
	 * @功能介绍 跳转至修改菜单
	 * 
	 * */
	@RequestMapping(value ="/toUpdShopMenu",method = RequestMethod.POST)
	public String toUpdShopMenu(WcShopMenu menu_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcShopMenu shopmenu = menuService.getShopMenuById(menu_Q.getWsmId());
		StringUtil.copyProperties(menu_Q, shopmenu);
		model.addAttribute("command", shopmenu);
		return "/menu/updShopMenu";
	} 
	
	/**
	 * @功能介绍 修改菜单保存
	 * */
	@RequestMapping(value="/updShopMenu",method = RequestMethod.POST)
	public String updDeviceCompany(WcShopMenu menu_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException 
	{
		menuService.updShopMenu(menu_Q);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "菜单修改成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shop/menu/queryMenu", menu_Q));
		return "redirect:/admin/toMsg";
	}
	
	@RequestMapping("/getMenuTreeForRole/{disabled}/{roleId}")
	@ResponseBody
	public List<Map<String, Object>> getMenuTreeForRole(@PathVariable String disabled, @PathVariable Integer roleId)
	{
		List<String> checkedList = null;
		if( roleId != -1 )
		{
			checkedList = roleService.queryShopRoleMenusById(roleId);
		}
		
		return getMenuTree(disabled, null, checkedList);
	}
	
	
	@RequestMapping("/getMenuTreeForAdmin/{disabled}/{roleIds}/{adminId}")
	@ResponseBody
	public List<Map<String, Object>> getMenuTreeForAdmin(@PathVariable String disabled, 
			@PathVariable String roleIds, @PathVariable Integer adminId)
	{
		List<String> disabledList = null;
		List<String> checkedList = null;
		if( ! roleIds.equals("-1") )
		{
			disabledList = roleService.queryShopRoleMenusForAdmin(roleIds);
		}
		if( ! roleIds.equals("-1") || adminId != -1 )
		{
			checkedList = roleService.queryShopRoleMenusForAdmin(roleIds, adminId);
		}
		
		return getMenuTree(disabled, disabledList, checkedList);
	}
	
	/**
	 * @author wanglei
	 * created on Sep 13, 2013 10:29:32 AM
	 * @param disabled 若是0则无操作，若是1则全部置灰，若是2则disabledList中的置灰
	 * @param disabledList 置灰的菜单id列表
	 * @param checkedList 默认选中的菜单id列表
	 * @return
	 */
	private List<Map<String, Object>> getMenuTree(String disabled, 
		List<String> disabledList, List<String> checkedList)
	{
		List<Map<String, Object>> menuList = memcachedservice.getMenuAll();
		for( Map<String, Object> map : menuList )
		{
			if( disabled.equals("1") )
			{
				map.put("chkDisabled", true);
			}
			else if( disabled.equals("2") && disabledList != null && 
					disabledList.contains(map.get("id").toString()) ) 
			{
				map.put("chkDisabled", true);
			}
			
			if( checkedList != null && checkedList.contains(map.get("id").toString()) )
			{
				map.put("checked", true);
			}
		}
		
		return menuList;
	}
	
	
	/**
	 * @author wanglei
	 * created on Sep 12, 2013 10:45:20 AM
	 * @param menuLevel 二级菜单的上级菜单为一级菜单，三级菜单的上级菜单为二级菜单
	 * @param parentId menu_id为parentId的菜单为选中状态
	 * @return
	 */
	@RequestMapping("/getParentMenuTree/{menuLevel}/{parentId}")
	@ResponseBody
	public List<Map<String, Object>> getParentMenuTree(@PathVariable String menuLevel,@PathVariable String parentId)
	{
		List<Map<String, Object>> menuList = memcachedservice.getMenuAll();
		for( Map<String, Object> map : menuList )
		{
			if( parentId != null && map.get("id").toString().equals(parentId) )
			{
				map.put("checked", true);
			}	
			if( map.get("menuLevel").toString().equals("1") )
			{
				if( menuLevel.equals("3") )
				{	
					map.put("nocheck", true);
				}
			}
			else if( map.get("menuLevel").toString().equals("2") )
			{
				if( menuLevel.equals("2") )
				{	
					map.put("nocheck", true);
				}
			}
			else 
			{
				map.put("nocheck", true);
			}
		}
		
		return menuList;
	}
}

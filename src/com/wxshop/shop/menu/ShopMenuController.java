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
	private IMemcachedService memcachedservice;
		
	@RequestMapping("/queryMenu")
	public String queryMenu(@ModelAttribute("command") WcShopMenu menu, HttpServletResponse response, HttpSession session, Model model)
	{
		//�ȰѲ˵������
		model.addAttribute(SysConstant.PAGE_RESULT,menuService.queryShopMenu(menu));
		return "/menu/queryShopMenu";
	}
	
	@RequestMapping("/toAddShopMenu")
	public String toAddShopMenu(@ModelAttribute("command") WcShopMenu menu,Model mode)
	{
		//��ת����Ӳ˵�
		return "/menu/addShopMenu";
	}

	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public String addMenu(WcShopMenu menu, HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException
	{
		//��ת����Ӳ˵�
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		menu.setWsmRegistor(admin.getWsaId());
		menu.setWsmRegistDate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		menuService.addMenu(menu);
		redirectAttributes.addFlashAttribute("msgCode",    "2");
		redirectAttributes.addFlashAttribute("alertMsg",   "�˵���ӳɹ�");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/shop/menu/queryMenu"));
		return "redirect:/admin/toMsg";
	}
	
	
	@RequestMapping(value = "/delShopMenu", method = RequestMethod.POST)
	public String delAccount(WcShopMenu menu, HttpServletRequest request,RedirectAttributes redirectAttributes)	throws IllegalArgumentException, IllegalAccessException {
		menuService.delMenu(menu.getWsmIds());
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "�˵�ɾ���ɹ�");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shop/menu/queryMenu", menu));
		return "redirect:/admin/toMsg";
	}
	
	/**
	 * @���ܽ��� ��ת���޸Ĳ˵�
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
	 * @���ܽ��� �޸Ĳ˵�����
	 * */
	@RequestMapping(value="/updShopMenu",method = RequestMethod.POST)
	public String updDeviceCompany(WcShopMenu menu_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException 
	{
		menuService.updShopMenu(menu_Q);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "�˵��޸ĳɹ�");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shop/menu/queryMenu", menu_Q));
		return "redirect:/admin/toMsg";
	}
	
	
	/**
	 * @author wanglei
	 * created on Sep 12, 2013 10:45:20 AM
	 * @param menuLevel �����˵����ϼ��˵�Ϊһ���˵��������˵����ϼ��˵�Ϊ�����˵�
	 * @param parentId menu_idΪparentId�Ĳ˵�Ϊѡ��״̬
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

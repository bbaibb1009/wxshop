package com.wxshop.shop.role;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxshop.common.IMemcachedService;
import com.wxshop.sys.IShopRoleService;
import com.wxshop.sys.WcShopAdmin;
import com.wxshop.sys.WcShopRole;
import com.wxshop.util.DateUtil;
import com.wxshop.util.StringUtil;
import com.wxshop.util.SysConstant;

@Controller
@RequestMapping("/shop/role")
public class ShopRoleController 
{
	
	
	@Autowired
	private IShopRoleService roleService;
	
	@Autowired 
	private IMemcachedService memcachedservice;
		
	@RequestMapping("/queryRole")
	public String queryRole(@ModelAttribute("command") WcShopRole role, HttpServletResponse response, HttpSession session, Model model)
	{
		//�ȰѲ˵������
		model.addAttribute(SysConstant.PAGE_RESULT,roleService.queryShopRole(role));
		return "/role/queryShopRole";
	}
	
	@RequestMapping("/toAddShopRole")
	public String toAddShopRole(@ModelAttribute("command") WcShopRole role,Model mode)
	{
		//��ת����Ӳ˵�
		return "/role/addShopRole";
	}

	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public String addRole(WcShopRole role, HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException
	{
		//��ת����Ӳ˵�
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		role.setWsrRegistor(admin.getWsaId());
		role.setWsrRegistDate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		roleService.addShopRole(role);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg","��ɫ��ӳɹ�");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/shop/role/queryRole"));
		return "redirect:/admin/toMsg";
	}
	
	
	@RequestMapping(value = "/delShopRole", method = RequestMethod.POST)
	public String delAccount(WcShopRole role, HttpServletRequest request,RedirectAttributes redirectAttributes)	throws IllegalArgumentException, IllegalAccessException {
		roleService.delShopRole(role.getWsrRoleIds());
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "�˵�ɾ���ɹ�");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shop/role/queryShopRole", role));
		return "redirect:/admin/toMsg";
	}
	
	/**
	 * @���ܽ��� ��ת���޸Ĳ˵�
	 * 
	 * */
	@RequestMapping(value ="/toUpdShopRole",method = RequestMethod.POST)
	public String toUpdShopRole(WcShopRole role_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcShopRole shoprole = roleService.getShopRoleById(role_Q.getWsrRoleId());
		StringUtil.copyProperties(role_Q, shoprole);
		model.addAttribute("command", shoprole);
		return "/menu/updShopRole";
	} 
	
	/**
	 * @���ܽ��� �޸Ĳ˵�����
	 * */
	@RequestMapping(value="/updShopMenu",method = RequestMethod.POST)
	public String updShopRole(WcShopRole role_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException 
	{
		roleService.updShopRole(role_Q);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "�˵��޸ĳɹ�");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shop/menu/queryMenu", role_Q));
		return "redirect:/admin/toMsg";
	}
	
	
}

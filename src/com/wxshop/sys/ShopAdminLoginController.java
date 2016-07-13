package com.wxshop.sys;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxshop.common.IMemcachedService;
import com.wxshop.util.SysConstant;
import com.wxshop.util.ThreeDes;
import com.wxshop.website.IWebsiteService;
import com.wxshop.website.WcWebsite;
import com.wxshop.util.SysConstant;
@Controller
@RequestMapping("/admin")
public class ShopAdminLoginController 
{
	@Autowired
	private IShopAdminService adminService;
	
	@Autowired
	private IShopRoleService roleService;
	
	@Autowired 
	private IMemcachedService memcachedservice;
	
	@Autowired
	private IWebsiteService websiteservice;
	
	@RequestMapping("/adminLogin")
	public String adminLogin(@ModelAttribute("command") WcShopAdmin admin, 
			HttpServletResponse response, HttpSession session, Model model, 
			@CookieValue(value = SysConstant.ADMIN_USERNAME, required = false) String usernameCookie, 
			@CookieValue(value = SysConstant.ADMIN_PWD_MD5, required = false) String pwdMd5Cookie,
			@CookieValue(value = SysConstant.ADMIN_PWD_3DES, required = false) String pwd3desCookie,
			@CookieValue(value = SysConstant.LOGIN_REDIRECT_URI, required = false) String rediUriCookie)
	{
		
		
		//����֤session �ǲ����е�¼ ����е�½ ֱ����ת��ҳ
		WcShopAdmin adminResult = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		if(adminResult!=null)
		{
			return "redirect:/admin/adminLoginSuccess";
		}
		
		
		//����֤��½���Ƿ�Ϸ�
		// cookie��½
		if( usernameCookie != null && pwdMd5Cookie != null && pwd3desCookie != null )
		{
			admin.setWsaUsername(usernameCookie);
			admin.setWsaPwdMd5(pwdMd5Cookie);
			admin.setWsaPwd(ThreeDes.decode(pwd3desCookie));
		}
		else 
		{
			// ��ת��½ҳ��
			if( admin.getWsaUsername() == null )
			{
				return "/login/login";
			}
			// ҳ���½
			else 
			{
				admin.setWsaPwdMd5(DigestUtils.md5DigestAsHex(admin.getWsaPwd().getBytes()));
			}
		}
//		if(!adminService.chkUsernameRandomStr(admin))
//		{
//			model.addAttribute("alertMsg", "�û���������벻ƥ��!");
//			return "/login/login";
//		}
		if( adminService.chkShopUsernameUnique(admin.getWsaUsername()) )
		{
			model.addAttribute("usernameError", "�û���������");
			return "/login/login";
		}
		adminResult = adminService.adminLogin(admin); 
		if( adminResult == null )
		{
			model.addAttribute("alertMsg", "�������");
			return "/login/login";
		}
		
		
		else
		{			
			adminResult.setWsaPwdMd5(adminResult.getWsaPwd());
			adminResult.setWsaPwd(admin.getWsaPwd());
			if( admin.isRemember() )
			{
				Cookie cookieUsername = new Cookie(SysConstant.ADMIN_USERNAME, adminResult.getWsaUsername());
				cookieUsername.setMaxAge(SysConstant.COOKIE_AGE);
				cookieUsername.setPath("/");
				response.addCookie(cookieUsername);
				Cookie cookiePwdMd5 = new Cookie(SysConstant.ADMIN_PWD_MD5, adminResult.getWsaPwdMd5());
				cookiePwdMd5.setMaxAge(SysConstant.COOKIE_AGE);
				cookiePwdMd5.setPath("/");
				response.addCookie(cookiePwdMd5);
				Cookie cookiePwd3des = new Cookie(SysConstant.ADMIN_PWD_3DES, ThreeDes.encode(adminResult.getWsaPwd()));
				cookiePwd3des.setMaxAge(SysConstant.COOKIE_AGE);
				cookiePwd3des.setPath("/");
				response.addCookie(cookiePwd3des);
			}
			List<Map<String, Object>> menuList1 = adminService.queryShopAdminMenus(adminResult.getWsaId(), "1");
			List<Map<String, Object>> menuList2 = adminService.queryShopAdminMenus(adminResult.getWsaId(), "2");
			List<Map<String, Object>> menuList3 = adminService.queryShopAdminMenus(adminResult.getWsaId(), "3");
			WcWebsite webSite = websiteservice.queryMySiteByAdmin(adminResult.getWsaId());
			session.setAttribute(SysConstant.WEBSITE_INFO, webSite);
			session.setAttribute(SysConstant.ADMIN_INFO, adminResult);//session��ŵ�ǰ�Ĺ���Ա��Ϣ
			session.setAttribute(SysConstant.ADMIN_MENU_ID1_WX, menuList1.get(0).get("WSM_ID").toString());
			session.setAttribute(SysConstant.ADMIN_MENU_ID2_WX, -1);
			session.setAttribute(SysConstant.ADMIN_MENU_ID3_WX, -1);
			session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL1_WX, menuList1);
			session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL2_WX, menuList2);
			session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL3_WX, menuList3);
			//���ǰ̨��صĵ�½��Ϣ
			adminService.updShopLoginTime(adminResult.getWsaId());
			if( rediUriCookie == null )
			{
				return "redirect:/admin/adminLoginSuccess";
			}
			else 
			{
				Cookie cookieUri = new Cookie(SysConstant.LOGIN_REDIRECT_URI_WX, null);
				cookieUri.setMaxAge(0);
				cookieUri.setPath("/");
				response.addCookie(cookieUri);
				return "redirect:" + rediUriCookie;
			}
		}
	}
	
	private boolean chkAdminInfo(WcShopAdmin admin)
	{
		boolean result = true;
		
		return result;
	}
	
	
	@RequestMapping("/toMsg")
	public String toMsg()
	{
		return "/common/msg";
	}
	

	@RequestMapping("/chkUsernameUnique/{adminId}/{username}")
	@ResponseBody
	public Map<String, Object> chkUsernameUnique(@PathVariable Integer adminId, @PathVariable String username 	) throws UnsupportedEncodingException
	{
	
		String name = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", adminService.chkShopUsernameUnique(adminId, username));
		map.put("name", name);
		return map;
	}
	
	@RequestMapping("/adminLoginSuccess")
	public String adminLoginSuccess(Model model, HttpSession session)
	{
		WcShopAdmin admin = (WcShopAdmin) session.getAttribute(SysConstant.ADMIN_INFO);
		return "/login/adminLoginSuccess";
	}
	
	/**
	 * @���ܽ��� �˵���ת
	 * @ud_history [20160321][yChoco][ֻ�������˵�]
	 * 
	 * */
	@RequestMapping("/goUrl/{menuId1}/{menuId2}/{url}")
	public String goUrl(@PathVariable Integer menuId1, @PathVariable Integer menuId2,  @PathVariable String url, HttpSession session)
	{
		session.setAttribute(SysConstant.ADMIN_MENU_ID1_WX, menuId1);
		session.setAttribute(SysConstant.ADMIN_MENU_ID2_WX, menuId2);
		return "redirect:" + url.replace("|", "/");
	}
	
	
	
	@RequestMapping("/getShopAdminNameList")
	@ResponseBody
	public List<Map<String, Object>> getShopAdminNameList() 
	{
		return memcachedservice.getAdminNameAll();
	}
	
	
	@RequestMapping("/adminLogout")
	public String adminLogout(HttpServletResponse response, HttpSession session) throws IOException
	{
		session.invalidate();
		return "redirect:/";
	}
	
	
}

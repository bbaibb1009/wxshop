package com.wxshop.weichat.menu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.pudding.weichat.accesstoken.AccessToken;
import cn.pudding.weichat.accesstoken.AccessTokenUtil;

import com.wxshop.util.StringUtil;
import com.wxshop.util.SysConstant;
import com.wxshop.weichat.fuwuhao.IWeiFuwuhaoService;
import com.wxshop.weichat.fuwuhao.WcWeiFuwuhao;

@Controller
@RequestMapping("/weixinmenu")
public class WeixinMenuController {

	@Autowired
	private IWeixinMenuService weixinMenuService;
	
	@Autowired
	private IWeiFuwuhaoService weiFuwuhaoservice;
	
	private static Logger log = Logger.getLogger(WeixinMenuController.class);

	@RequestMapping(value = "/queryMenu")
	public String queryMenu(@ModelAttribute("command") WcWeiMenu menu,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT, weixinMenuService.queryMenu(menu));
		return "/weixinmenu/queryMenu";
	}
	
	@RequestMapping(value = "/toAddMenu")
	public String toAddMenu(@ModelAttribute("command") WcWeiMenu menu,Model model)
	{
		return "/weixin/addMenu";
	}
	
	
	@RequestMapping(value = "/addMenu")
	public String addMenu(@ModelAttribute("command") WcWeiMenu menu, RedirectAttributes redirectattributes)
	{
		String  appId_curent = menu.getWmuAppId();
		String  appSecret_current = menu.getWmuAppSecret();
		String 	jsonMenu = menu.getWmuJson();
		AccessToken at = AccessTokenUtil.getAccessToken(appId_curent,appSecret_current);  
	    if (null != at) {  
	        // 调用接口创建菜单  
	        int result = weixinMenuService.addMenu(menu,at.getAccess_token()); 	
	        // 判断菜单创建结果  
	        if (0 == result)  
	        {
	        	redirectattributes.addFlashAttribute("alertMsg", "菜单创建成功！");	
	        }
	        else
	        {	
	            redirectattributes.addFlashAttribute("alertMsg", "菜单创建失败，错误码：" + result);
	        }
	    }
	    return "redirect:/weixinmenu/queryMenu";
	}

	@RequestMapping(value = "/addMenuByEnter")
	public String addMenuByEnter(@ModelAttribute("command") WcWeiMenu menu, RedirectAttributes redirectattributes)
	{
		String  appId_curent = menu.getWmuAppId();
		String  appSecret_current = menu.getWmuAppSecret();
		String 	jsonMenu = menu.getWmuJson();
		
		AccessToken at = AccessTokenUtil.getAccessToken(appId_curent,appSecret_current);  
	    if (null != at) {  
	        // 调用接口创建菜单  
	        // int result = WeixinUtil.createMenu(getMenu(),at.getWatToken());  
	        int result = weixinMenuService.addMenu(menu,at.getAccess_token()); 	
	        // 判断菜单创建结果  
	        if (0 == result)  
	        {
	        	log.info("菜单创建成功！");
	        	redirectattributes.addFlashAttribute("alertMsg", "菜单创建成功！");	
	        }
	        else
	        {	
	            log.info("菜单创建失败，错误码：" + result);
	            redirectattributes.addFlashAttribute("alertMsg", "菜单创建失败，错误码：" + result);
	        }
	    }
	    return "redirect:/weixin/queryWeixinEnter";
		
	}
	
	
	@RequestMapping(value = "/toUpdMenu" ,method = RequestMethod.POST)
	public String toUpdAdmin(WcWeiMenu menu_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcWeiMenu menu = weixinMenuService.getMenuById(menu_Q.getWmuId());
		StringUtil.copyProperties(menu_Q, menu);
		model.addAttribute("command", menu);
		return "/weixin/updMenu";
	} 
	
	@RequestMapping(value = "/toUpdWeiXinMenu",method = RequestMethod.POST)
	public String toUpdWeiXinMenu(WcWeiFuwuhao fuwuhao_Q, Model model) 
	{
		
		WcWeiFuwuhao fuwuhao = weiFuwuhaoservice.getFuwuhaoById(fuwuhao_Q.getFwhId());
		WcWeiMenu menu = weixinMenuService.getMenuByAppId(fuwuhao.getFwhAppId());
		
		if(menu!=null)
		{
			menu.setWmuWecId(fuwuhao.getFwhId());
			menu.setWmuAppSecret(fuwuhao.getFwhAppSecret());
			model.addAttribute("weiEnter", fuwuhao);
			model.addAttribute("command", menu);
			return "/weixinmenu/updMenuByWeiEnter";
		}
		else
		{
			menu = new WcWeiMenu();
			menu.setWmuAppId(fuwuhao.getFwhAppId());
			menu.setWmuWecId(fuwuhao.getFwhId());
			menu.setWmuAppSecret(fuwuhao.getFwhAppSecret());
			model.addAttribute("weiEnter", fuwuhao);
			model.addAttribute("command", menu);
			return "/weixinmenu/addMenuByWeiEnter";
		}
	}
	
	
	
	
	
}

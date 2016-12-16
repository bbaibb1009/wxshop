//:QiYeHaoController.java
/**
 * @author yChoco
 * 微信企业号第三方应用的回调处理类 
 * 
 * 
 * */
package com.wxshop.weichat.qiyehao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pudding.weichat.mp.aes.AesException;
import cn.pudding.weichat.mp.aes.SHA1;
import cn.pudding.weichat.mp.aes.WXBizMsgCrypt;

import com.wxshop.util.DateUtil;
import com.wxshop.util.SysConstant;



@Controller
@RequestMapping("/weiqiyehao")
public class QiYeHaoController {
	
	@Autowired
	private IQiYeHaoService  qiyehaoservice;
	
	@Autowired
	private ITxlService txlService;
	
	private String token			= "sdlongzhong"; 
	private String corpId 			= "wx8ddea39def1dc0ef";
	private String encodingAESKey 	= "m5e80hvvdf5OkDorH3KbelPxRz8xMR7IKFQkFE9fRSR";
	private static Logger log 		= Logger.getLogger(QiYeHaoController.class);
	
	/***
	 * 回调链接:企业号回调地址验证
	 * upd_author:	yChoco
	 * upd_time:	2015-09-29
	 * upd_history: [150929][yChoco][创建该应用]
	 * */
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callbackGet(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		try
		{
			// 微信加密签名
	        String signature 	= 	request.getParameter("msg_signature");
	        // 随机字符串
	        String echostr 		= 	request.getParameter("echostr");
	        // 时间戳
	        String timestamp 	= 	request.getParameter("timestamp");
	        // 随机数
	        String nonce 		= 	request.getParameter("nonce");
	        String[] str 		= 	{token,timestamp,nonce};
	        Arrays.sort(str); // 字典序排序
	        // SHA1加密
	        String digest = SHA1.getSHA1(token, timestamp, nonce, echostr);
	        // 确认请求来至企业号
	        if (digest.equals(signature)) 
	        {
	            WXBizMsgCrypt wx = new WXBizMsgCrypt(token, encodingAESKey,corpId);
				String sEchoStr = wx.verifyUrl(signature, timestamp, nonce, echostr);
				response.getWriter().print(sEchoStr);
				return null;
	        }
	        else
	        {
	        	return null;
	        }

		}
		catch(AesException ae)
		{
			ae.printStackTrace();
			return null;
		}
		catch(IOException io)
		{
			io.printStackTrace();
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	/***
	 * 回调链接:企业号处理事件+消息响应
	 * upd_author:	yChoco
	 * upd_time:	2015-09-30
	 * upd_history: [150930][yChoco][创建该应用]
	 * */
	@RequestMapping(value = "/callback", method = RequestMethod.POST)
	public String callbackPost(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		//将请求、响应的编码均设置为UTF-8（防止中文乱码）   
		try
		{
			request.setCharacterEncoding("UTF-8");  
	        response.setCharacterEncoding("UTF-8");  
	        // 调用核心业务类接收消息、处理消息   
	        String respMessage = qiyehaoservice.processRequest(request,token,encodingAESKey,corpId,""); 
	        // 响应消息   
	        PrintWriter out = response.getWriter();  
	        out.print(respMessage);  
	        out.close();
	        return null;
		}
        catch(Exception e)
        {
        	return null;
        }
	}
	
	
	/***
	 * 功能介绍:企业号员工绑定情况查询
	 * @author  yChoco
	 * @upd_history [151109][yChoco][查询员工的绑定情况]
	 * 
	 **/
	/*
	@RequestMapping("/queryAdminWeixin")
	public String queryAdminWeixin(@ModelAttribute("command") LzAdminWeixin adminWeixin,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT, adminWeixinService.queryAdminWeixin(adminWeixin));
		return "/weixinqyh/queryAdminWeixin";
	}
	*/
	/***
	 * 功能介绍:批量更新所有在职员工的绑定情况
	 * @author  yChoco
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @upd_history [151109][yChoco][查询员工的绑定情况]
	 * 
	 **/
	/*
	@RequestMapping("/batchUpdAdminWeixin")
	public String batchUpdAdminWeixin(@ModelAttribute("command") LzAdminWeixin adminWeixin,Model model,HttpSession session) throws JsonParseException, JsonMappingException, IOException
	{
		LzAdmin adminRegistor = (LzAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		//查找所有的在职员工
		List<LzAdmin> listAdmin = adminService.getAdminList();
		for(int i=0;i<listAdmin.size();i++)
		{
			LzAdmin admin = listAdmin.get(i);
			if(admin.getDelFlag().equals("1") || admin.getDelFlag().equals("4"))
			{
				Map<String,Object> mapAdmin = txlService.queryUser(admin);
				if(mapAdmin!=null)
				{
					String subscribe = Integer.toString((Integer)mapAdmin.get("status"));
					//添加管理员
					//1、先删除微信端的管理员  再添加回去最新的管理员信息
					adminWeixinService.delAdminWeixin(admin.getUsername());
					LzAdminWeixin adminWei = new LzAdminWeixin();
					adminWei.setAweAdminId(admin.getAdminId());
					adminWei.setAweUserId(admin.getUsername());
					adminWei.setAweStatus(subscribe);
					adminWei.setAweRegistor(adminRegistor.getAdminId());
					adminWei.setAweRegistDate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
					adminWeixinService.addAdminWeixin(adminWei);
				}
			}
		}
		return "forward:/weiqiyehao/queryAdminWeixin";
	}
	*/
	/**
	 * @功能介绍 跳转到绑定企业号的页面
	 * @upd_history [20160331][yChoco][创建函数]
	 * */
	/*
	@RequestMapping("/toBindQiyehao")
	public String toBindQiyehao(@ModelAttribute("command") LzAdmin adminResult,Model model)
	{
		return "/weixinqyh/bindQiyehao";
	}
	*/
	
	/**
	 * @功能介绍 扫描页面后跳转
	 * @upd_history [20160331][yChoco][创建函数]
	 * */
	/*
	@RequestMapping("/isAdminWeixinBind")
	@ResponseBody
	public Map<String,Object> isAdminWeixinBind(HttpServletRequest request)
	{
		String username = (String)request.getParameter("username");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("isbind",adminWeixinService.isQyhBinded(username));
		return map;
	}
	*/
	/*
	@RequestMapping("/toUpdAdminMobile/{adminId}")
	public String toUpdAdminMobile(@ModelAttribute("command") LzAdmin admin, @PathVariable Integer adminId,Model model)
	{
		admin = adminService.getAdminById(adminId);
		model.addAttribute("command",admin);
		return "/weixinqyh/updAdminMobile";
	}
	*/
	/*
	@RequestMapping("/updAdminMobile")
	public String updAdminMobile(@ModelAttribute("command") LzAdmin admin,HttpSession session,Model model)
	{
		if(!adminService.chkAdminMobile(admin.getAdminId(),admin.getMobile()))
		{
			adminService.updAdminMobile(admin);
			//更新session
			admin= adminService.getAdminById(admin.getAdminId());
			session.setAttribute(SysConstant.ADMIN_INFO, admin);
		}
		model.addAttribute("alertMsg", "修改手机号完成!");
		return "/weixinqyh/updAdminMobile";
	}
	
	**/
}

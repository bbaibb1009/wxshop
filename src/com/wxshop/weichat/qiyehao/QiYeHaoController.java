//:QiYeHaoController.java
/**
 * @author yChoco
 * ΢����ҵ�ŵ�����Ӧ�õĻص������� 
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
	 * �ص�����:��ҵ����������Ӧ�õĻص���ַ��֤
	 * upd_author:	yChoco
	 * upd_time:	2015-09-29
	 * upd_history: [150929][yChoco][������Ӧ��]
	 * */
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callbackGet(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		try
		{
			// ΢�ż���ǩ��
	        String signature 	= 	request.getParameter("msg_signature");
	        // ����ַ���
	        String echostr 		= 	request.getParameter("echostr");
	        // ʱ���
	        String timestamp 	= 	request.getParameter("timestamp");
	        // �����
	        String nonce 		= 	request.getParameter("nonce");
	        String[] str 		= 	{token,timestamp,nonce};
	        Arrays.sort(str); // �ֵ�������
	        // SHA1����
	        String digest = SHA1.getSHA1(token, timestamp, nonce, echostr);
	        // ȷ������������ҵ��
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
	 * �ص�����:��ҵ�Ŵ����¼�+��Ϣ��Ӧ
	 * upd_author:	yChoco
	 * upd_time:	2015-09-30
	 * upd_history: [150930][yChoco][������Ӧ��]
	 * */
	@RequestMapping(value = "/callback", method = RequestMethod.POST)
	public String callbackPost(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		//��������Ӧ�ı��������ΪUTF-8����ֹ�������룩   
		try
		{
			request.setCharacterEncoding("UTF-8");  
	        response.setCharacterEncoding("UTF-8");  
	        // ���ú���ҵ���������Ϣ��������Ϣ   
	        String respMessage = qiyehaoservice.processRequest(request,token,encodingAESKey,corpId,""); 
	        // ��Ӧ��Ϣ   
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
	 * ���ܽ���:��ҵ��Ա���������ѯ
	 * @author  yChoco
	 * @upd_history [151109][yChoco][��ѯԱ���İ����]
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
	 * ���ܽ���:��������������ְԱ���İ����
	 * @author  yChoco
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @upd_history [151109][yChoco][��ѯԱ���İ����]
	 * 
	 **/
	/*
	@RequestMapping("/batchUpdAdminWeixin")
	public String batchUpdAdminWeixin(@ModelAttribute("command") LzAdminWeixin adminWeixin,Model model,HttpSession session) throws JsonParseException, JsonMappingException, IOException
	{
		LzAdmin adminRegistor = (LzAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		//�������е���ְԱ��
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
					//��ӹ���Ա
					//1����ɾ��΢�Ŷ˵Ĺ���Ա  ����ӻ�ȥ���µĹ���Ա��Ϣ
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
	 * @���ܽ��� ��ת������ҵ�ŵ�ҳ��
	 * @upd_history [20160331][yChoco][��������]
	 * */
	/*
	@RequestMapping("/toBindQiyehao")
	public String toBindQiyehao(@ModelAttribute("command") LzAdmin adminResult,Model model)
	{
		return "/weixinqyh/bindQiyehao";
	}
	*/
	
	/**
	 * @���ܽ��� ɨ��ҳ�����ת
	 * @upd_history [20160331][yChoco][��������]
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
			//����session
			admin= adminService.getAdminById(admin.getAdminId());
			session.setAttribute(SysConstant.ADMIN_INFO, admin);
		}
		model.addAttribute("alertMsg", "�޸��ֻ������!");
		return "/weixinqyh/updAdminMobile";
	}
	
	**/
}

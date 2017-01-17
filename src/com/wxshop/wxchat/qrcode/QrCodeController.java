package com.wxshop.wxchat.qrcode;


import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pudding.weichat.accesstoken.AccessTokenUtil;

@Controller
@RequestMapping("/qrCode")
public class QrCodeController {
	
	@Autowired 
	private IQrCodeService qrCodeService;
	
	@RequestMapping(value = "/getLoginWeiQrcode")
	@ResponseBody
	public Map<String,Object> getLoginWeiQrcode(HttpSession session,Model model)
	{
		String sessionID = session.getId();
		String accessToken = AccessTokenUtil.getAccessToken("wx699aebd2bc63a9fb", "b217c1c39dddfdbde6f489cc44953400").getAccess_token();
		return qrCodeService.getLoginWeiQrcode(sessionID,accessToken);
	}
}

package com.wxshop.weichat.msg.request;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.pudding.weichat.message.response.WcWeiBaseMsgResp;

import com.wxshop.util.Page;
import com.wxshop.wxchat.msg.WcWeiMessage;

public interface IWeixinMessageService {

	public String processRequest_Jar(HttpServletRequest request,String token,String encodingAESKey,String appId) throws IOException;
	public WcWeiBaseMsgResp fenleiReq_Jar(Map<String, String> requestMap,String appId);

	
}

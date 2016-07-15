package com.wxshop.weixin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oilchem.weixin.message.response.LzWeiBaseMsgResp;
import com.wxshop.util.Page;
import com.wxshop.wxchat.msg.WcWeiMessage;

public interface IWeixinMessageService {
	public String processRequest(HttpServletRequest request,String token,String encodingAESKey,String appId,String type) throws Exception ;
	public String processRequest_Jar(HttpServletRequest request,String token,String encodingAESKey,String appId) throws IOException;
	public LzWeiBaseMsgResp fenleiReq_Jar(Map<String, String> requestMap,String appId);
	public Page queryWcWeiMsg(WcWeiMessage msg);
	public void addWcWeiMessage(WcWeiMessage msg);
	public WcWeiMessage getWcWeiMessageById(Integer id);
//	public List<Map<String,Object>> queryKeywordListByWei(Integer wecId);
	public void updWxMsg(WcWeiMessage wxmsg_Q);
	
	
}

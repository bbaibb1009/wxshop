package com.wxshop.weichat.msg.manage;

import cn.pudding.weichat.message.response.WcWeiBaseMsgResp;

import com.wxshop.util.Page;
import com.wxshop.wxchat.msg.WcWeiMessage;

public interface IWeixinMsgManageService {
	public Page queryWcWeiMsg(WcWeiMessage msg);
	public void addWcWeiMessage(WcWeiMessage msg);
	public WcWeiMessage getWcWeiMessageById(Integer id);
//	public List<Map<String,Object>> queryKeywordListByWei(Integer wecId);
	public void updWxMsg(WcWeiMessage wxmsg_Q);
	public WcWeiBaseMsgResp queryDefaultMsgByAppId(WcWeiBaseMsgResp respMessage,String content,String appId);
	public WcWeiBaseMsgResp querySubscribeMsgByAppId(WcWeiBaseMsgResp respMessage,String appId);
	public Page queryKeyWordMsgByAppId(WcWeiMessage msg);
	public WcWeiMessage getSubScribeMsgByApp(String appId);
	public void addWcKeywordMessage(WcWeiMessage msg,String[] keyWordArray,Integer adminId);
	
}

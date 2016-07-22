package com.wxshop.weichat.msg.manage;

import com.wxshop.util.Page;
import com.wxshop.wxchat.msg.WcWeiMessage;

public interface IWeixinMsgManageService {
	public Page queryWcWeiMsg(WcWeiMessage msg);
	public void addWcWeiMessage(WcWeiMessage msg);
	public WcWeiMessage getWcWeiMessageById(Integer id);
//	public List<Map<String,Object>> queryKeywordListByWei(Integer wecId);
	public void updWxMsg(WcWeiMessage wxmsg_Q);
	
}

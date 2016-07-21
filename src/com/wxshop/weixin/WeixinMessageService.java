package com.wxshop.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.pudding.weichat.Constant;
import cn.pudding.weichat.message.response.WcWeiBaseMsgResp;
import cn.pudding.weichat.message.response.WcWeiTextMsgResp;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.util.Page;
import com.wxshop.wxchat.msg.WcWeiMessage;

@Service
@Transactional
public class WeixinMessageService implements IWeixinMessageService {

	@Autowired 
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	private static Logger log = Logger.getLogger(WeixinMessageService.class);
	
	/** 
     * 处理微信发来的请求 
     * @param request 
     * @return 
	 * @throws Exception 
     */  						
    public String processRequest(HttpServletRequest request,String token,String encodingAESKey,String appId,String type) throws Exception  {  
        String respMessage 		= null;  
        String encrypt_type 	= (String)request.getParameter("encrypt_type")==null?"":(String)request.getParameter("encrypt_type");
    	String msg_signature 	= (String)request.getParameter("msg_signature")==null?"":(String)request.getParameter("msg_signature");
    	String timestamp 		= (String)request.getParameter("timestamp")==null?"":(String)request.getParameter("timestamp");
    	String nonce 			= (String)request.getParameter("nonce")==null?"":(String)request.getParameter("nonce");
        // 默认返回的文本消息内容  
        String respContent = "请求处理异常，请稍候尝试！";  
        InputStream inputStream = request.getInputStream();
        // xml请求解析  
        Map<String, String> requestMap = new HashMap<String,String>();
        //requestMap = com.oilchem.weixin.message.MessageUtil.parseXml(request, token, encodingAESKey, appId);  
        //按照加密方式的不同进行消息的预处理
        if(encrypt_type.equals("aes"))
        {
            requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlAes(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        else
        {
            requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        // 回复不同类型的结构1
        WcWeiBaseMsgResp respMsg = fenleiReq(requestMap);  
        // respMessage = MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce,type);
        respMessage = cn.pudding.weichat.message.MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce);
        return respMessage;  
    }
    
    /** 
     * 对微信发过来的请求进行分类
     * @param request 
     * @return 
     */
    public WcWeiBaseMsgResp fenleiReq(Map<String, String> requestMap)
    {
    	// 发送方帐号（open_id）  
        String fromUserName = requestMap.get("FromUserName")==null?"":requestMap.get("FromUserName");  
        // 公众帐号  
        String toUserName 	= requestMap.get("ToUserName")==null?"":requestMap.get("ToUserName");  
        // 消息类型  
        String msgType 		= requestMap.get("MsgType")==null?"":requestMap.get("MsgType");  
        //发过来的消息
        String content		= requestMap.get("Content")==null?"":requestMap.get("Content");
        String respContent = "";
       WcWeiBaseMsgResp respMessage = new WcWeiBaseMsgResp();
        respMessage.setToUserName(fromUserName);  
        respMessage.setFromUserName(toUserName);  
        respMessage.setCreateTime(new Date().getTime());  
        respMessage.setFuncFlag(0);  
       // log.error("消息类型:"+msgType);
        // 文本消息  
        if(msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) 
        {  
            //在这里将发送过来的消息进行分类处理
        	if(content.startsWith("会议")||content.startsWith("隆众会议"))
        	{
        		WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(respMessage);
        		txtMsg.setContent("http://meeting.oilchem.net/");
        		respMessage = txtMsg;
        	}
        	else if(content.startsWith("石化通")||content.startsWith("供求")||content.startsWith("供需")||content.startsWith("下载"))
        	{
        		//respMessage =  com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(contentReq);
        	}
        	else
        	{
        		//respMessage = TextMsgUtil.getDefualtTextMsg(respMessage);
        	}
        }  
        // 图片消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_IMAGE)) 
        {  
            respContent = "您发送的是图片消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_IMAGE);
        }  
        // 地理位置消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LOCATION)) 
        {  
            respContent = "您发送的是地理位置消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_LOCATION);
        }  
        // 链接消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LINK)) 
        {  
            respContent = "您发送的是链接消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_LINK);
        }  
        // 音频消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_VOICE)) 
        {  
            respContent = "您发送的是音频消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_VOICE);
        }  
        // 事件推送  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_EVENT)) 
        {  
            // 事件类型  
            String eventType = requestMap.get("Event");  
            //log.error("##########################事件类型:"+eventType);
            // 订阅  
            if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) 
            {  
            	String EventKey = requestMap.get("EventKey"); 
            	//log.error("##########################qrscene_:"+EventKey);
            	if(EventKey.startsWith("qrscene_"))
            	{
            		String code = requestMap.get("EventKey").replace("qrscene_", "").trim();  
                	//log.error("关注后："+code);
                	//respMessage = gqService.getGqFxListUrl(code,respMessage);
            	}
            	else
            	{
            		respContent = 
                        "您好！欢迎添加隆众资讯微信公共平台/:8-)/:8-)/:8-)。\n"+
                        "为了更好的方便您的工作咨询，烦请告知您的姓名、公司名称、联系电话，以便接受更全面的信息服务/:gift/:gift/:gift。\n"+
                        "回复【会议】即可知晓最新会议内容安排，如果您有更好的建议、需求，请拨打：0533-2591688       联系我们/磕头/磕头/磕头。\n"+
                        "谢谢您的支持/:,@-D/:,@-D/:,@-D！\n"+
                        "点击自定义菜单  石化通  即可免费下载隆众石化最新供需平台APP——【隆众石化通】/:gift/:gift/:gift\n"+
                        "点击自定义菜单  短讯通  即可免费下载隆众资讯短信APP——【隆众短讯通】/:gift/:gift/:gift";  
                    respMessage = cn.pudding.weichat.message.TextMsgUtil.getTextMsg(respMessage,respContent);
            	}
            }  
            // 取消订阅  
            else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) 
            {  
                // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
            	respMessage.setMsgType(Constant.EVENT_TYPE_UNSUBSCRIBE);
            }  
            // 自定义菜单点击事件  
            else if (eventType.equals(Constant.EVENT_TYPE_CLICK)) 
            {  
                // TODO 自定义菜单权没有开放，暂不处理该类消息 
            	respMessage.setMsgType(Constant.EVENT_TYPE_CLICK);
            } 
//            else if (eventType.equals(Constant.EVENT_TYPE_SCAN))
//            {
//            	//扫描事件
//            	//扫描发现有识别码后
//            	String code = requestMap.get("EventKey");  
//            	//log.error("扫描后："+code);
////            	respMessage = gqService.getGqFxListUrl(code,respMessage);
//            }
        }
        
    	return respMessage;
    }

	public String processRequest_Jar(HttpServletRequest request,String token,String encodingAESKey,String appId)throws IOException 
	{
		log.error("11");
		String respMessage 		= null;  
		log.error("12");
		String encrypt_type 	= (String)request.getParameter("encrypt_type")==null? "":(String)request.getParameter("encrypt_type");
    	String msg_signature 	= (String)request.getParameter("msg_signature")==null?"":(String)request.getParameter("msg_signature");
    	String timestamp 		= (String)request.getParameter("timestamp")==null? "":(String)request.getParameter("timestamp");
    	String nonce 			= (String)request.getParameter("nonce")==null? "":(String)request.getParameter("nonce");
        // 默认返回的文本消息内容  
        String respContent = "请求处理异常，请稍候尝试！";  
        log.error("13");
        InputStream inputStream = request.getInputStream();
        // xml请求解析  
        Map<String, String> requestMap = new HashMap<String,String>();
        //按照加密方式的不同进行消息的预处理
        if(encrypt_type.equals("aes"))
        {
        	log.error("14");
            requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlAes(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        else
        {
        	log.error("15");
            requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        // 不同的消息内容返回不同的响应
        log.error("16");
        log.error(requestMap);
        WcWeiBaseMsgResp respMsg = fenleiReq_Jar(requestMap,appId); 
        log.error("17");
        //再根据加密方式的不同进行消息的相应前封装
        respMessage = cn.pudding.weichat.message.MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce);
        log.error("18");
        return respMessage; 
	}
    
	/** 
     * 对微信发过来的请求进行分类
     * @param request 
     * @return 
     */
    public WcWeiBaseMsgResp fenleiReq_Jar(Map<String, String> requestMap,String appId)
    {
    	//发送方帐号(open_id)  
        String fromUserName = requestMap.get("FromUserName")==null?"":requestMap.get("FromUserName");  
        //公众帐号  (app_Id)
        String toUserName 	= requestMap.get("ToUserName")==null?"":requestMap.get("ToUserName");  
        //消息类型  
        String msgType 		= requestMap.get("MsgType")==null?"":requestMap.get("MsgType");  
        //发过来的消息
        String content		= requestMap.get("Content")==null?"":requestMap.get("Content");
        String respContent 	= "";
        WcWeiBaseMsgResp 	respMessage = new WcWeiBaseMsgResp();
        respMessage.setToUserName(fromUserName);  
        respMessage.setFromUserName(toUserName);  
        respMessage.setCreateTime(new Date().getTime());  
        respMessage.setFuncFlag(0);  
        // 文本消息  
        if(msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) 
        {  
            //在这里将发送过来的消息进行分类处理
        	//先根据appid 取出默认回复信息
        	respMessage = this.queryDefaultMsgByAppId(respMessage,content,appId);
        
        }  
        // 图片消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_IMAGE)) 
        {  
            respContent = "您发送的是图片消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_IMAGE);
        }  
        // 地理位置消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LOCATION)) 
        {  
            respContent = "您发送的是地理位置消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_LOCATION);
        }  
        // 链接消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LINK)) 
        {  
            respContent = "您发送的是链接消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_LINK);
        }  
        // 音频消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_VOICE)) 
        {  
            respContent = "您发送的是音频消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_VOICE);
        }  
        // 事件推送  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_EVENT)) 
        {  
            // 事件类型  
            String eventType = requestMap.get("Event");  
            // 订阅  
            if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) 
            {  
//                respContent = 
//                    "您好！欢迎添加隆众资讯微信公共平台/:8-)/:8-)/:8-)。\n"+
//                    "为了更好的方便您的工作咨询，烦请告知您的姓名、公司名称、联系电话，以便接受更全面的信息服务/:gift/:gift/:gift。\n"+
//                    "回复【会议】即可知晓最新会议内容安排，如果您有更好的建议、需求，请拨打：0533-2591688       联系我们/磕头/磕头/磕头。\n"+
//                    "谢谢您的支持/:,@-D/:,@-D/:,@-D！\n"+
//                    "点击自定义菜单  石化通  即可免费下载隆众石化最新供需平台APP——【隆众石化通】/:gift/:gift/:gift\n"+
//                    "点击自定义菜单  短讯通  即可免费下载隆众资讯短信APP——【隆众短讯通】/:gift/:gift/:gift";  
//                respMessage = com.oilchem.weixin.message.TextMsgUtil.getTextMsg(respMessage,respContent);
                //关注后就添加一条用户记录到数据库
//            	this.addWatcherBySubscribe(respMessage,appId);
//            	respMessage = this.querySubscribeMsgByAppId(respMessage,appId);
            }  
            // 取消订阅  
            else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) 
            {  
                // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
            	// 取消关注后要将用户的状态改为已取消关注
//            	this.updWatcherByUnSubscribe(respMessage,appId);
            	respMessage.setMsgType(Constant.EVENT_TYPE_UNSUBSCRIBE);
            }  
            // 自定义菜单点击事件  
            else if (eventType.equals(Constant.EVENT_TYPE_CLICK)) {  
                // TODO 自定义菜单权没有开放，暂不处理该类消息 
            	respMessage.setMsgType(Constant.EVENT_TYPE_CLICK);
            }  
        }
        return respMessage;
    }
//    
//    public void addWatcherBySubscribe(LzWeiBaseMsgResp respMessage,String appId) 
//    {
//    	try
//    	{
//    		//先根据appId 查出企业微信对象来
//        	LzWeiEnter wec= weixinservice.getWeiEnterByAppId(appId);
//        	String openId = respMessage.getToUserName();
//        	LzWeiWatcher watcher = weixinservice.getWatcherByOpenId(openId,wec.getWecId());
//    		if(watcher==null)
//    		{
//    			watcher = new LzWeiWatcher();
//    			watcher.setWacOpenid(openId);
//    			watcher.setWacStatus("0");
//    			watcher.setWacAppid(wec.getWecAppId());
//    			watcher.setWacWecId(wec.getWecId());
//    			weixinservice.addWatcher(watcher);
//    		}
//    		Map<String,Object> userMap = weixinservice.querUserInfo(openId,wec.getWecId());
//    		if(userMap!=null)
//    		{
//    			watcher.setWacSubscribe(Integer.parseInt((String)userMap.get("subscribe")));
//    			watcher.setWacNickName((String)userMap.get("nickname"));
//    			watcher.setWacSex(userMap.get("sex").toString());
//    			watcher.setWacLanguage((String)userMap.get("language"));
//    			watcher.setWacCity((String)userMap.get("city"));
//    			watcher.setWacProvince((String)userMap.get("province"));
//    			watcher.setWacCountry((String)userMap.get("country"));
//    			watcher.setWacHeadImgUrl((String)userMap.get("headimgurl"));
//    			watcher.setWacStatus("1");
//    			Date subscribeTime = new Date();
//    			subscribeTime.setTime((Long.parseLong((String)userMap.get("subscribe_time")))*(long)1000);
//    			watcher.setWacSubscribeTime(DateUtil.parseString(subscribeTime,"yyyy-MM-dd HH:mm:ss"));
//    			weixinservice.updWatcher(watcher);
//    		}
//    	}
//    	catch(Exception e)
//    	{
//    		e.printStackTrace();
//    	}
//    }
//    
//    public void updWatcherByUnSubscribe(LzWeiBaseMsgResp respMessage,String appId)
//    {
//    	//根据openId和appid找到相应的watcher
//    	//置为取消关注
//    	LzWeiEnter wec= weixinservice.getWeiEnterByAppId(appId);
//    	String openId = respMessage.getToUserName();
//    	LzWeiWatcher watcher = weixinservice.getWatcherByOpenId(openId,wec.getWecId());
//    	watcher.setWacSubscribe(0);
//    	weixinservice.updWatcher(watcher);
//    }
//    
    public WcWeiBaseMsgResp queryDefaultMsgByAppId(WcWeiBaseMsgResp respMessage,String content,String appId)
    {
    	WcWeiMessage msg = this.getKeyWordMsgByContent(content, appId);
    	//先判断content是不是关键字
    	if(msg!=null)
    	{
    		String msgType = msg.getWmgMsgType();
    		if(msgType.equals("2"))
    		{
    			//log.error("***********************318行：是关键字且是文本信息***********************");
    			WcWeiTextMsgResp msgResp = new WcWeiTextMsgResp(respMessage);
    			msgResp.setContent(msg.getWmgContent());
    			return msgResp;
    		}
    		else if(msgType.equals("2"))
    		{
    			//log.error("***********************318行：是关键字且是图文信息***********************");
    			//LzNewsMsgResp msgResp = new LzNewsMsgResp(respMessage);
    			WcWeiTextMsgResp msgResp = new WcWeiTextMsgResp(respMessage);
    			msgResp.setContent(msg.getWmgContent());
    			return msgResp;
    		}
    		else
    		{
    			//log.error("***********************318行：是关键字 但不是文本信息***********************");
    			return cn.pudding.weichat.message.TextMsgUtil.getDefualtTextMsg(respMessage);
    		}
    	}
    	//不是关键字的就返回默认回复信息
    	else
    	{
        	String sql = 
        		" SELECT b.WMG_ID, b.WMG_MSG_TYPE,b.WMG_CONTENT  FROM WC_WEI_FUWUHAO a  JOIN WC_WEI_MESSAGE b ON a.FWH_APP_ID = b.WMG_APP_ID   WHERE a.FWH_APP_ID = ? AND b.WMG_REPLY_TYPE = '2' ";
        	List<Map<String,Object>> list = jdbcDao.queryForList(sql,new Object[]{appId});
        	if(list.size()>0)
        	{
        		Map<String,Object> map = list.get(0);
        		Integer wmgId = (Integer)map.get("WMG_ID");
        		msg = this.getWcWeiMessageById(wmgId);
        		String msgType = msg.getWmgMsgType() ;
        		if(msgType.equals("2"))
        		{
        			//log.error("***********************338行***********************");
        			WcWeiTextMsgResp msgResp = new WcWeiTextMsgResp(respMessage);
        			msgResp.setContent(msg.getWmgContent());
        			return msgResp;
        		}
        		else
        		{
        			//log.error("***********************345行：不是返回文本信息***********************");
        			return cn.pudding.weichat.message.TextMsgUtil.getDefualtTextMsg(respMessage);
        		}
        	}
        	else
        	{
        		return cn.pudding.weichat.message.TextMsgUtil.getDefualtTextMsg(respMessage);
        	}
    	}
    }
//    
//    
//    public LzWeiBaseMsgResp  querySubscribeMsgByAppId(LzWeiBaseMsgResp respMessage,String appId)
//    {
//    	LzWeiMessage msg = this.getSubScribeMsgByApp(appId);
//    	//先判断content是不是关键字
//    	if(msg!=null)
//    	{
//    		String msgType = msg.getWmgMsgType();
//    		if(msgType.equals("2"))
//    		{
//    			LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
//    			msgResp.setContent(msg.getWmgContent());
//    			return msgResp;
//    		}
//    		else
//    		{
//    			//log.error("***********************318行：有关注回复 但不是文字消息***********************");
//    			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
//    		}
//    	}
//    	else
//    	{
//    		//log.error("***********************387行：没有设置关注回复***********************");
//			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
//    		
//    	}
//     }
//    
//    
    public Page queryWcWeiMsg(WcWeiMessage msg) {
		// TODO Auto-generated method stub
    	String wmgReplyType_Q = msg.getWmgReplyType_Q();
    	String wmgMsgType_Q = msg.getWmgMsgType_Q();
    	String wmgAesType_Q = msg.getWmgAesType_Q();
    	String wmgAppId_Q = msg.getWmgAppId_Q();
    	StringBuilder sql =new StringBuilder(
  			
    		" select " +
    		" a.WMG_APP_ID, " +
    		" a.WMG_ID," +
    		" a.WMG_CONTENT," +
    		" a.WMG_REPLY_TYPE," +
    		" a.WMG_MSG_TYPE," +
    		" a.WMG_AES_TYPE," +
    		" a.WMG_STATUS," +
    		" a.WMG_DESC," +
    		" b.WSA_NAME WMG_REGISTOR," +
    		" date_format(a.WMG_REGISTDATE, '%Y-%m-%d %H:%i:%s') as WMG_REGISTDATE" +
    		" from WC_WEI_MESSAGE a " +
    		" left join WC_SHOP_ADMIN b on a.WMG_REGISTOR = b.WSA_ID " +
    		" where 1=1 "
    	);
    	StringBuilder sqlCnt = new StringBuilder(
    		" select count(*) from WC_WEI_MESSAGE a " +
    		" where 1=1 "
    	);
    	List<Object> paraList = new ArrayList<Object>(){};
    	if(wmgAppId_Q!=null && wmgAppId_Q.length()>0)
    	{
    		sql.append(" and a.WMG_APP_ID =  ? ");
    		sqlCnt.append(" and a.WMG_APP_ID = ? ");
    		paraList.add(wmgAppId_Q);
    	}
    	if(wmgReplyType_Q!=null && wmgReplyType_Q.length()>0)
    	{
    		sql.append(" and a.WMG_REPLY_TYPE =  ? ");
    		sqlCnt.append(" and a.WMG_REPLY_TYPE = ? ");
    		paraList.add(wmgReplyType_Q);
    	}
    	if(wmgMsgType_Q!=null && wmgMsgType_Q.length()>0)
    	{
    		sql.append(" and a.WMG_MSG_TYPE =  ? ");
    		sqlCnt.append(" and a.WMG_MSG_TYPE = ? ");
    		paraList.add(wmgMsgType_Q);
    	}
    	if(wmgAesType_Q!=null && wmgAesType_Q.length()>0)
    	{
    		sql.append(" and a.WMG_AES_TYPE =  ? ");
    		sqlCnt.append(" and a.WMG_AES_TYPE = ? ");
    		paraList.add(wmgAesType_Q);
    	}
    	Page page = new Page(sql.toString(),sqlCnt.toString(),msg.getCurrentPage(),msg.getPageSize(),paraList.toArray());
    	jdbcDao.queryForPage(page);
		return page;
	}

	public void addWcWeiMessage(WcWeiMessage msg) 
	{
		// TODO Auto-generated method stub
		hibernateDao.add(msg);
	}
//
	public WcWeiMessage getWcWeiMessageById(Integer id)
	{
		return hibernateDao.get(WcWeiMessage.class, id);
	}
//	
	public WcWeiMessage getKeyWordMsgByContent(String content,String appId)
	{
		String sql = 
			" select a.WKG_WMG_ID " +
			" from WC_WEI_KEYWORD_MESSAGE a " +
			" join WC_WEI_FUWUHAO b on a.WKG_WEC_ID = b.FWH_ID  " +
			" where b.FWH_APP_ID = ? and a.WKG_KEYWORDS = ? ";
		List<Map<String,Object>> list = jdbcDao.queryForList(sql, new Object[]{appId,content});
		if(list.size()>0)
		{
			Map<String,Object> map = list.get(0);
			Integer wmgId = (Integer)map.get("WKG_WMG_ID");
			WcWeiMessage msgWei = this.getWcWeiMessageById(wmgId); 
			return msgWei;
		}
		else
		{
			return null;
		}
	}
//	
//	public LzWeiMessage getSubScribeMsgByApp(String appId)
//	{
//		String sql = 
//			" select a.WEC_SUBSCRIBE_MSG from LZ_WEI_ENTER a " +
//			" where a.WEC_APP_ID = ?  ";
//		List<Map<String,Object>> list = jdbcDao.queryForList(sql, new Object[]{appId});
//		if(list.size()>0)
//		{
//			Map<String,Object> map = list.get(0);
//			Integer wmgId = (Integer)map.get("WEC_SUBSCRIBE_MSG");
//			LzWeiMessage msgWei = this.getLzWeiMessageById(wmgId); 
//			return msgWei;
//		}
//		else
//		{
//			return null;
//		}
//	}
//	
//
//	public List<Map<String, Object>> queryKeywordListByWei(Integer wecId) {
//		// TODO Auto-generated method stub
//		String sql = 
//			" select distinct  " +
//			"  LEFT(a.kwd,LEN(a.kwd)-1) as kwd,b.WMG_CONTENT,b.WMG_ID ,a.WKG_WEC_ID,c.WKG_APP_ID " +
//			"  from  " +
//			"  (  " +
//			" 	select bb.WMG_ID,cc.WKG_WEC_ID, (select  WKG_KEYWORDS+',' from LZ_WEI_KEYWORD_MESSAGE where WKG_WMG_ID = bb.WMG_ID FOR XML PATH('')) as kwd from LZ_WEI_MESSAGE bb left join LZ_WEI_KEYWORD_MESSAGE cc on bb.WMG_ID = cc.WKG_WMG_ID group by bb.WMG_ID ,cc.WKG_WEC_ID " +
//			" ) a  " +
//			" left join LZ_WEI_MESSAGE b on a.WMG_ID = b.WMG_ID  " +
//			" join LZ_WEI_KEYWORD_MESSAGE c on c.WKG_WEC_ID = a.WKG_WEC_ID  " +
//			" where c.WKG_WEC_ID = ? " ;
//		return jdbcDao.queryForList(sql, new Object[]{wecId});
//	}
//
	public void updWxMsg(WcWeiMessage msg) {
		// TODO Auto-generated method stub
		hibernateDao.update(msg);
	}
	
	
	
	
}

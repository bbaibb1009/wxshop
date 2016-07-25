package com.wxshop.weichat.msg.request;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
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
import com.wxshop.weichat.fans.IWeiFansService;
import com.wxshop.weichat.fuwuhao.IWeiFuwuhaoService;
import com.wxshop.weichat.msg.manage.IWeixinMsgManageService;

@Service
@Transactional
public class WeixinMessageService implements IWeixinMessageService {

	@Autowired 
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	@Autowired
	private IWeiFuwuhaoService weiFuwuhaoService;
	
	@Autowired
	private IWeiFansService weiFansService;
	
	@Autowired
	private IWeixinMsgManageService weimsgmanageservice;
	
	private static Logger log = Logger.getLogger(WeixinMessageService.class);
	
	
    

	public String processRequest_Jar(HttpServletRequest request,String token,String encodingAESKey,String appId)throws IOException 
	{

		String respMessage 		= null;  

		String encrypt_type 	= (String)request.getParameter("encrypt_type")==null? "":(String)request.getParameter("encrypt_type");
    	String msg_signature 	= (String)request.getParameter("msg_signature")==null?"":(String)request.getParameter("msg_signature");
    	String timestamp 		= (String)request.getParameter("timestamp")==null? "":(String)request.getParameter("timestamp");
    	String nonce 			= (String)request.getParameter("nonce")==null? "":(String)request.getParameter("nonce");
        // 默认返回的文本消息内容  
        String respContent = "请求处理异常，请稍候尝试！";  

        InputStream inputStream = request.getInputStream();
        // xml请求解析  
        Map<String, String> requestMap = new HashMap<String,String>();
        //按照加密方式的不同进行消息的预处理
        if(encrypt_type.equals("aes"))
        {

            requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlAes(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        else
        {

            requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        // 不同的消息内容返回不同的响应
        WcWeiBaseMsgResp respMsg = fenleiReq_Jar(requestMap,appId); 
        //再根据加密方式的不同进行消息的相应前封装
        respMessage = cn.pudding.weichat.message.MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce);
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
        	respMessage = weimsgmanageservice.queryDefaultMsgByAppId(respMessage,content,appId);
        
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
      
               // 关注后就添加一条用户记录到数据库
            	String openId =  respMessage.getToUserName();
            	weiFansService.addFansBySubscribe(openId,appId);
            	respMessage = weimsgmanageservice.querySubscribeMsgByAppId(respMessage,appId);
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

	
	
	
}

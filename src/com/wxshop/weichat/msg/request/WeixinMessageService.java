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
        // Ĭ�Ϸ��ص��ı���Ϣ����  
        String respContent = "�������쳣�����Ժ��ԣ�";  

        InputStream inputStream = request.getInputStream();
        // xml�������  
        Map<String, String> requestMap = new HashMap<String,String>();
        //���ռ��ܷ�ʽ�Ĳ�ͬ������Ϣ��Ԥ����
        if(encrypt_type.equals("aes"))
        {

            requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlAes(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        else
        {

            requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        // ��ͬ����Ϣ���ݷ��ز�ͬ����Ӧ
        WcWeiBaseMsgResp respMsg = fenleiReq_Jar(requestMap,appId); 
        //�ٸ��ݼ��ܷ�ʽ�Ĳ�ͬ������Ϣ����Ӧǰ��װ
        respMessage = cn.pudding.weichat.message.MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce);
        return respMessage; 
	}
    
	/** 
     * ��΢�ŷ�������������з���
     * @param request 
     * @return 
     */
    public WcWeiBaseMsgResp fenleiReq_Jar(Map<String, String> requestMap,String appId)
    {
    	//���ͷ��ʺ�(open_id)  
        String fromUserName = requestMap.get("FromUserName")==null?"":requestMap.get("FromUserName");  
        //�����ʺ�  (app_Id)
        String toUserName 	= requestMap.get("ToUserName")==null?"":requestMap.get("ToUserName");  
        //��Ϣ����  
        String msgType 		= requestMap.get("MsgType")==null?"":requestMap.get("MsgType");  
        //����������Ϣ
        String content		= requestMap.get("Content")==null?"":requestMap.get("Content");
        String respContent 	= "";
        WcWeiBaseMsgResp 	respMessage = new WcWeiBaseMsgResp();
        respMessage.setToUserName(fromUserName);  
        respMessage.setFromUserName(toUserName);  
        respMessage.setCreateTime(new Date().getTime());  
        respMessage.setFuncFlag(0);  
        // �ı���Ϣ  
        if(msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) 
        {  
            //�����ｫ���͹�������Ϣ���з��ദ��
        	//�ȸ���appid ȡ��Ĭ�ϻظ���Ϣ
        	respMessage = weimsgmanageservice.queryDefaultMsgByAppId(respMessage,content,appId);
        
        }  
        // ͼƬ��Ϣ  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_IMAGE)) 
        {  
            respContent = "�����͵���ͼƬ��Ϣ��";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_IMAGE);
        }  
        // ����λ����Ϣ  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LOCATION)) 
        {  
            respContent = "�����͵��ǵ���λ����Ϣ��";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_LOCATION);
        }  
        // ������Ϣ  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LINK)) 
        {  
            respContent = "�����͵���������Ϣ��";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_LINK);
        }  
        // ��Ƶ��Ϣ  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_VOICE)) 
        {  
            respContent = "�����͵�����Ƶ��Ϣ��";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_VOICE);
        }  
        // �¼�����  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_EVENT)) 
        {  
            // �¼�����  
            String eventType = requestMap.get("Event");  
            // ����  
            if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) 
            {  
      
               // ��ע������һ���û���¼�����ݿ�
            	String openId =  respMessage.getToUserName();
            	weiFansService.addFansBySubscribe(openId,appId);
            	respMessage = weimsgmanageservice.querySubscribeMsgByAppId(respMessage,appId);
            }
            // ȡ������  
            else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) 
            {  
                // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
            	// ȡ����ע��Ҫ���û���״̬��Ϊ��ȡ����ע
//            	this.updWatcherByUnSubscribe(respMessage,appId);
            	respMessage.setMsgType(Constant.EVENT_TYPE_UNSUBSCRIBE);
            }  
            // �Զ���˵�����¼�  
            else if (eventType.equals(Constant.EVENT_TYPE_CLICK)) {  
                // TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ 
            	respMessage.setMsgType(Constant.EVENT_TYPE_CLICK);
            }  
        }
        return respMessage;
    }
    
    
    
    
   


    
//    public void updWatcherByUnSubscribe(LzWeiBaseMsgResp respMessage,String appId)
//    {
//    	//����openId��appid�ҵ���Ӧ��watcher
//    	//��Ϊȡ����ע
//    	LzWeiEnter wec= weixinservice.getWeiEnterByAppId(appId);
//    	String openId = respMessage.getToUserName();
//    	LzWeiWatcher watcher = weixinservice.getWatcherByOpenId(openId,wec.getWecId());
//    	watcher.setWacSubscribe(0);
//    	weixinservice.updWatcher(watcher);
//    }

	
	
	
}

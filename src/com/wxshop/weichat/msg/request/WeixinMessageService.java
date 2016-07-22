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
	
	private static Logger log = Logger.getLogger(WeixinMessageService.class);
	
	/** 
     * ����΢�ŷ��������� 
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
        // Ĭ�Ϸ��ص��ı���Ϣ����  
        String respContent = "�������쳣�����Ժ��ԣ�";  
        InputStream inputStream = request.getInputStream();
        // xml�������  
        Map<String, String> requestMap = new HashMap<String,String>();
        //requestMap = com.oilchem.weixin.message.MessageUtil.parseXml(request, token, encodingAESKey, appId);  
        //���ռ��ܷ�ʽ�Ĳ�ͬ������Ϣ��Ԥ����
        if(encrypt_type.equals("aes"))
        {
            requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlAes(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        else
        {
            requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        // �ظ���ͬ���͵Ľṹ1
        WcWeiBaseMsgResp respMsg = fenleiReq(requestMap);  
        // respMessage = MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce,type);
        respMessage = cn.pudding.weichat.message.MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce);
        return respMessage;  
    }
    
    /** 
     * ��΢�ŷ�������������з���
     * @param request 
     * @return 
     */
    public WcWeiBaseMsgResp fenleiReq(Map<String, String> requestMap)
    {
    	// ���ͷ��ʺţ�open_id��  
        String fromUserName = requestMap.get("FromUserName")==null?"":requestMap.get("FromUserName");  
        // �����ʺ�  
        String toUserName 	= requestMap.get("ToUserName")==null?"":requestMap.get("ToUserName");  
        // ��Ϣ����  
        String msgType 		= requestMap.get("MsgType")==null?"":requestMap.get("MsgType");  
        //����������Ϣ
        String content		= requestMap.get("Content")==null?"":requestMap.get("Content");
        String respContent = "";
       WcWeiBaseMsgResp respMessage = new WcWeiBaseMsgResp();
        respMessage.setToUserName(fromUserName);  
        respMessage.setFromUserName(toUserName);  
        respMessage.setCreateTime(new Date().getTime());  
        respMessage.setFuncFlag(0);  
       // log.error("��Ϣ����:"+msgType);
        // �ı���Ϣ  
        if(msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) 
        {  
            //�����ｫ���͹�������Ϣ���з��ദ��
        	if(content.startsWith("����")||content.startsWith("¡�ڻ���"))
        	{
        		WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(respMessage);
        		txtMsg.setContent("http://meeting.oilchem.net/");
        		respMessage = txtMsg;
        	}
        	else if(content.startsWith("ʯ��ͨ")||content.startsWith("����")||content.startsWith("����")||content.startsWith("����"))
        	{
        		//respMessage =  com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(contentReq);
        	}
        	else
        	{
        		//respMessage = TextMsgUtil.getDefualtTextMsg(respMessage);
        	}
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
            //log.error("##########################�¼�����:"+eventType);
            // ����  
            if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) 
            {  
            	String EventKey = requestMap.get("EventKey"); 
            	//log.error("##########################qrscene_:"+EventKey);
            	if(EventKey.startsWith("qrscene_"))
            	{
            		String code = requestMap.get("EventKey").replace("qrscene_", "").trim();  
                	//log.error("��ע��"+code);
                	//respMessage = gqService.getGqFxListUrl(code,respMessage);
            	}
            	else
            	{
            		respContent = 
                        "���ã���ӭ���¡����Ѷ΢�Ź���ƽ̨/:8-)/:8-)/:8-)��\n"+
                        "Ϊ�˸��õķ������Ĺ�����ѯ�������֪������������˾���ơ���ϵ�绰���Ա���ܸ�ȫ�����Ϣ����/:gift/:gift/:gift��\n"+
                        "�ظ������顿����֪�����»������ݰ��ţ�������и��õĽ��顢�����벦��0533-2591688       ��ϵ����/��ͷ/��ͷ/��ͷ��\n"+
                        "лл����֧��/:,@-D/:,@-D/:,@-D��\n"+
                        "����Զ���˵�  ʯ��ͨ  �����������¡��ʯ�����¹���ƽ̨APP������¡��ʯ��ͨ��/:gift/:gift/:gift\n"+
                        "����Զ���˵�  ��Ѷͨ  �����������¡����Ѷ����APP������¡�ڶ�Ѷͨ��/:gift/:gift/:gift";  
                    respMessage = cn.pudding.weichat.message.TextMsgUtil.getTextMsg(respMessage,respContent);
            	}
            }  
            // ȡ������  
            else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) 
            {  
                // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
            	respMessage.setMsgType(Constant.EVENT_TYPE_UNSUBSCRIBE);
            }  
            // �Զ���˵�����¼�  
            else if (eventType.equals(Constant.EVENT_TYPE_CLICK)) 
            {  
                // TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ 
            	respMessage.setMsgType(Constant.EVENT_TYPE_CLICK);
            } 
//            else if (eventType.equals(Constant.EVENT_TYPE_SCAN))
//            {
//            	//ɨ���¼�
//            	//ɨ�跢����ʶ�����
//            	String code = requestMap.get("EventKey");  
//            	//log.error("ɨ���"+code);
////            	respMessage = gqService.getGqFxListUrl(code,respMessage);
//            }
        }
        
    	return respMessage;
    }

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
        	respMessage = this.queryDefaultMsgByAppId(respMessage,content,appId);
        
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
            	respMessage = this.querySubscribeMsgByAppId(respMessage,appId);
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

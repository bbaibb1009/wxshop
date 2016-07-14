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

import com.oilchem.weixin.Constant;
import com.oilchem.weixin.message.response.LzWeiBaseMsgResp;
import com.oilchem.weixin.message.response.LzWeiTextMsgResp;
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
            requestMap = com.oilchem.weixin.message.MessageUtil.parseXmlAes(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        else
        {
            requestMap = com.oilchem.weixin.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        // �ظ���ͬ���͵Ľṹ1
        LzWeiBaseMsgResp respMsg = fenleiReq(requestMap);  
        // respMessage = MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce,type);
        respMessage = com.oilchem.weixin.message.MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce);
        return respMessage;  
    }
    
    /** 
     * ��΢�ŷ�������������з���
     * @param request 
     * @return 
     */
    public LzWeiBaseMsgResp fenleiReq(Map<String, String> requestMap)
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
        LzWeiBaseMsgResp respMessage = new LzWeiBaseMsgResp();
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
        		LzWeiTextMsgResp txtMsg = new LzWeiTextMsgResp(respMessage);
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
            if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) {  
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
                    respMessage = com.oilchem.weixin.message.TextMsgUtil.getTextMsg(respMessage,respContent);
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
            requestMap = com.oilchem.weixin.message.MessageUtil.parseXmlAes(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        else
        {
            requestMap = com.oilchem.weixin.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
        }
        // ��ͬ����Ϣ���ݷ��ز�ͬ����Ӧ
        LzWeiBaseMsgResp respMsg = fenleiReq_Jar(requestMap,appId);  
        //�ٸ��ݼ��ܷ�ʽ�Ĳ�ͬ������Ϣ����Ӧǰ��װ
        respMessage = com.oilchem.weixin.message.MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce);
        return respMessage; 
	}
    
	/** 
     * ��΢�ŷ�������������з���
     * @param request 
     * @return 
     */
    public LzWeiBaseMsgResp fenleiReq_Jar(Map<String, String> requestMap,String appId)
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
        LzWeiBaseMsgResp 	respMessage = new LzWeiBaseMsgResp();
        respMessage.setToUserName(fromUserName);  
        respMessage.setFromUserName(toUserName);  
        respMessage.setCreateTime(new Date().getTime());  
        respMessage.setFuncFlag(0);  
        // �ı���Ϣ  
        if(msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) 
        {  
            //�����ｫ���͹�������Ϣ���з��ദ��
        	//�ȸ���appid ȡ��Ĭ�ϻظ���Ϣ
        	//respMessage = this.queryDefaultMsgByAppId(respMessage,content,appId);
        	//respMessage = com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
//        	//���ж��ǲ��ǹؼ��� ����� �ͻظ���Ӧ���� ������Ǿ� �ظ�Ĭ������ 
//        	if(content.startsWith("����")||content.startsWith("¡�ڻ���")||content.startsWith("ʯ��ͨ")
//        		||content.startsWith("����")||content.startsWith("����")||content.startsWith("����"))
//        	{
//        		LzNewsMsgResp newsMsg = new LzNewsMsgResp(respMessage);
//        		List<Article>  articleLst = new ArrayList<Article>();
//        		Article article1 = new Article();
//        		article1.setTitle("2015�������ʵ���");
//        		article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/COJCmkcJvOAIpkRLNAQPBuhKm872GPkIyn2zcysXpo6iaicbHWS3G6Ac4IohIa0lyhzeKAuVfp7SibgxUZkXhRphQ/0");
//        		article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzA3MDU3OTgzMA==&mid=201324402&idx=1&sn=e9dd73c47bd3a5060d1393fe88ab564f#rd");
//        		article1.setDescription("");
//        		articleLst.add(article1);
//        		Article article2 = new Article();
//        		article2.setTitle("New!!¡��ʯ��ͨ����");
//        		article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/COJCmkcJvOAIpkRLNAQPBuhKm872GPkIpDO9HuAfKibNIC2IIa1iaxsgPsM6Uiayz3C1O9lofWyZ3c8qV0FH4xKiaw/0");
//        		article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA3MDU3OTgzMA==&mid=201324402&idx=3&sn=9f78a6b935d996ccf1ae3d929ed613ba#rd");
//        		article2.setDescription("");
//        		articleLst.add(article2);
//        		Article article3 = new Article();
//        		article3.setTitle("New!!¡��ʯ��ͨ����");
//        		article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/COJCmkcJvOAIpkRLNAQPBuhKm872GPkIpDO9HuAfKibNIC2IIa1iaxsgPsM6Uiayz3C1O9lofWyZ3c8qV0FH4xKiaw/0");
//        		article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA3MDU3OTgzMA==&mid=201324402&idx=2&sn=13fa531ffefd916616173a22f1ef6912#rd");
//        		article3.setDescription("");
//        		articleLst.add(article3);
//        		newsMsg.setArticleCount(articleLst.size());
//        		newsMsg.setArticles(articleLst);
//        		respMessage = newsMsg;
//        	}
//        	else
//        	{
//        		respMessage = com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
//        	}
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
//                respContent = 
//                    "���ã���ӭ���¡����Ѷ΢�Ź���ƽ̨/:8-)/:8-)/:8-)��\n"+
//                    "Ϊ�˸��õķ������Ĺ�����ѯ�������֪������������˾���ơ���ϵ�绰���Ա���ܸ�ȫ�����Ϣ����/:gift/:gift/:gift��\n"+
//                    "�ظ������顿����֪�����»������ݰ��ţ�������и��õĽ��顢�����벦��0533-2591688       ��ϵ����/��ͷ/��ͷ/��ͷ��\n"+
//                    "лл����֧��/:,@-D/:,@-D/:,@-D��\n"+
//                    "����Զ���˵�  ʯ��ͨ  �����������¡��ʯ�����¹���ƽ̨APP������¡��ʯ��ͨ��/:gift/:gift/:gift\n"+
//                    "����Զ���˵�  ��Ѷͨ  �����������¡����Ѷ����APP������¡�ڶ�Ѷͨ��/:gift/:gift/:gift";  
//                respMessage = com.oilchem.weixin.message.TextMsgUtil.getTextMsg(respMessage,respContent);
                //��ע������һ���û���¼�����ݿ�
//            	this.addWatcherBySubscribe(respMessage,appId);
//            	respMessage = this.querySubscribeMsgByAppId(respMessage,appId);
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
//    
//    public void addWatcherBySubscribe(LzWeiBaseMsgResp respMessage,String appId) 
//    {
//    	try
//    	{
//    		//�ȸ���appId �����ҵ΢�Ŷ�����
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
//    	//����openId��appid�ҵ���Ӧ��watcher
//    	//��Ϊȡ����ע
//    	LzWeiEnter wec= weixinservice.getWeiEnterByAppId(appId);
//    	String openId = respMessage.getToUserName();
//    	LzWeiWatcher watcher = weixinservice.getWatcherByOpenId(openId,wec.getWecId());
//    	watcher.setWacSubscribe(0);
//    	weixinservice.updWatcher(watcher);
//    }
//    
//    public LzWeiBaseMsgResp queryDefaultMsgByAppId(LzWeiBaseMsgResp respMessage,String content,String appId)
//    {
//    	LzWeiMessage msg = this.getKeyWordMsgByContent(content, appId);
//    	//���ж�content�ǲ��ǹؼ���
//    	if(msg!=null)
//    	{
//    		String msgType = msg.getWmgMsgType();
//    		if(msgType.equals("2"))
//    		{
//    			//log.error("***********************318�У��ǹؼ��������ı���Ϣ***********************");
//    			LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
//    			msgResp.setContent(msg.getWmgContent());
//    			return msgResp;
//    		}
//    		else if(msgType.equals("2"))
//    		{
//    			//log.error("***********************318�У��ǹؼ�������ͼ����Ϣ***********************");
//    			LzNewsMsgResp msgResp = new LzNewsMsgResp(respMessage);
//    			return msgResp;
//    		}
//    		else
//    		{
//    			//log.error("***********************318�У��ǹؼ��� �������ı���Ϣ***********************");
//    			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
//    		}
//    	}
//    	//���ǹؼ��ֵľͷ���Ĭ�ϻظ���Ϣ
//    	else
//    	{
//        	String sql = 
//        		" select b.WMG_ID, b.WMG_MSG_TYPE,b.WMG_CONTENT " +
//        		" from LZ_WEI_ENTER a " +
//        		" join LZ_WEI_MESSAGE b on a.WEC_DEFAULT_MSG = b.WMG_ID  " +
//        		" where a.WEC_APP_ID = ? ";
//        	List<Map<String,Object>> list = jdbcDao.queryForList(sql,new Object[]{appId});
//        	if(list.size()>0)
//        	{
//        		Map<String,Object> map = list.get(0);
//        		Integer wmgId = (Integer)map.get("WMG_ID");
//        		msg = this.getLzWeiMessageById(wmgId);
//        		String msgType = msg.getWmgMsgType() ;
//        		if(msgType.equals("2"))
//        		{
//        			//log.error("***********************338��***********************");
//        			LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
//        			msgResp.setContent(msg.getWmgContent());
//        			return msgResp;
//        		}
//        		else
//        		{
//        			//log.error("***********************345�У����Ƿ����ı���Ϣ***********************");
//        			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
//        		}
//        	}
//        	else
//        	{
//        		return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
//        	}
//    	}
//    }
//    
//    
//    public LzWeiBaseMsgResp  querySubscribeMsgByAppId(LzWeiBaseMsgResp respMessage,String appId)
//    {
//    	LzWeiMessage msg = this.getSubScribeMsgByApp(appId);
//    	//���ж�content�ǲ��ǹؼ���
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
//    			//log.error("***********************318�У��й�ע�ظ� ������������Ϣ***********************");
//    			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
//    		}
//    	}
//    	else
//    	{
//    		//log.error("***********************387�У�û�����ù�ע�ظ�***********************");
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
    		" a.WMG_ID," +
    		" a.WMG_CONTENT," +
    		" a.WMG_REPLY_TYPE," +
    		" a.WMG_MSG_TYPE," +
    		" a.WMG_AES_TYPE," +
    		" a.WMG_STATUS," +
    		" a.WMG_DESC," +
    		" a.WMG_REGISTOR," +
    		" a.WMG_REGISTDATE " +
    		" from WC_WEI_MESSAGE a " +
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
//	public LzWeiMessage getLzWeiMessageById(Integer id)
//	{
//		return hibernateDao.get(LzWeiMessage.class, id);
//	}
//	
//	public LzWeiMessage getKeyWordMsgByContent(String content,String appId)
//	{
//		String sql = 
//			" select a.WKG_WMG_ID from LZ_WEI_KEYWORD_MESSAGE a " +
//			" join LZ_WEI_ENTER b on a.WKG_WEC_ID = b.WEC_ID  " +
//			" where b.WEC_APP_ID = ? and a.WKG_KEYWORDS = ? ";
//		List<Map<String,Object>> list = jdbcDao.queryForList(sql, new Object[]{appId,content});
//		if(list.size()>0)
//		{
//			Map<String,Object> map = list.get(0);
//			Integer wmgId = (Integer)map.get("WKG_WMG_ID");
//			LzWeiMessage msgWei = this.getLzWeiMessageById(wmgId); 
//			return msgWei;
//		}
//		else
//		{
//			return null;
//		}
//	}
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
//	public void updLzWeiMsg(LzWeiMessage msg) {
//		// TODO Auto-generated method stub
//		hibernateDao.update(msg);
//	}
	
	
	
	
}

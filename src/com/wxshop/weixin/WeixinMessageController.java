package com.wxshop.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oilchem.weixin.mp.aes.AesException;
import com.oilchem.weixin.mp.aes.SHA1;


@Controller
@RequestMapping("/weixinmsg")
public class WeixinMessageController {

	@Autowired
	private IWeixinMessageService weixinmsgservice;
	
	@Autowired
	private IWeixinService weixinservice;
//
//	@Value("${weixin.fuwuhao_token}")
//	private String token;
//	    
//	@Value("${weixin.fuwuhao_EncodingAESKey}")
//	private String encodingAESKey;
//	    
//	@Value("${weixin.appid}")
//	private String appId;//wx6a59b66048cc5acb
	
	
	//8ab5bea35793d82232e77a6c55a8a3a3
	private static Logger log = Logger.getLogger(WeixinMessageController.class);
	
	/**
	 * ������"52pudding"����Ϣ����
	 **/
	/**
	 * �����ǡ����Թ��ںš�����Ϣ����
	 ***/
	@RequestMapping(value = "/getServeEchoTest/{appId}", method = RequestMethod.GET)
	public String getServeEchoTestGet(@PathVariable String appId,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws AesException, IOException
	{
		try
		{
			WcWeiFuwuhao fuwuhao = weixinservice.getWeiFwhByAppId(appId);
		    String token 		= fuwuhao.getFwhToken();//abc123
			String signature 	= (String)request.getParameter("signature");// ΢�ż���ǩ��
			log.info("signature:"		+signature);
	        String echostr 		= (String)request.getParameter("echostr");	// ����ַ���
	        log.info("echostr:"			+echostr);	
	        String timestamp 	= (String)request.getParameter("timestamp");// ʱ���
	        log.info("timestamp:"		+timestamp);
	        String nonce 		= (String)request.getParameter("nonce");	// �����
	        log.info("nonce:"			+nonce);	
	        String encrypt = "";
	        String[] str = {token,timestamp,nonce};
	        Arrays.sort(str); // �ֵ�������
	        // SHA1����
	        String digest = SHA1.getSHA1(token,timestamp,nonce,encrypt);
	        // ȷ����������΢��
	        log.info("digest:"+digest);
	        log.info("signature:"+signature);
	        if(digest.equals(signature)) 
	        {
	            response.getWriter().print(echostr);
	        }
	        else
	        {
	        }
		}
		catch(AesException ae)
		{
			ae.printStackTrace();
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			return null;
		}
	}
	
	@RequestMapping(value = "/getServeEchoTest/{appId}", method = RequestMethod.POST)
	public String getServeEchoTestPost(@PathVariable String appId,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException
	{
        //�ȸ���appId���������΢���˺�
		WcWeiFuwuhao fwh = weixinservice.getWeiFwhByAppId(appId);
        String token_test 			= fwh.getFwhToken();//pudding
        String encodingAESKey_test 	= fwh.getFwhEncodingAesKey();//
        // ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩   
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        // ���ú���ҵ���������Ϣ��������Ϣ   
        String respMessage = weixinmsgservice.processRequest_Jar(request,token_test,encodingAESKey_test,appId); 
        // ��Ӧ��Ϣ   
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();
        return null;
	}

//	
//	
//	/**
//	 * ��ѯĬ�ϻظ���Ϣ
//	 ***/
//	@RequestMapping(value = "/queryDefaultMsg")
//	public String queryDefaultMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
//	{
//		msg.setWmgReplyType_Q("2");
//		model.addAttribute(SysConstant.PAGE_RESULT, weixinmsgservice.queryLzWeiMsg(msg));
//		return "/weixin/showDefaultMsg";
//	}
//	
//	
//	@RequestMapping(value = "/querySubscribeMsg")
//	public String querySubscribeMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
//	{
//		msg.setWmgReplyType_Q("3");
//		model.addAttribute(SysConstant.PAGE_RESULT, weixinmsgservice.queryLzWeiMsg(msg));
//		return "/weixin/showSubscribeMsg";
//	}
//	
//	
//	@RequestMapping(value = "/queryKeyWordsMsg")
//	public String queryKeyWordsMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
//	{
//		msg.setWmgReplyType_Q("1");
//		model.addAttribute(SysConstant.PAGE_RESULT, weixinmsgservice.queryLzWeiMsg(msg));
//		return "/weixin/showKeyWordsMsg";
//	}
//	/**
//	 * �ظ���Ϣ��ѯ
//	 * */
//	@RequestMapping(value = "/queryLzWeiMsg")
//	public String queryLzWeiMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
//	{
//		model.addAttribute(SysConstant.PAGE_RESULT,weixinmsgservice.queryLzWeiMsg(msg));
//		return "/weixin/queryLzWeiMsg";
//	}
//	
//	
//	/**
//	 * ���ܽ���:��ת����Ϣ���
//	 * @author yChoco
//	 * @upd_history [20151208][JAY][��ת����ѯ����_Q]
//	 * */
//	@RequestMapping(value = "/toAddLzWeiMsg", method = RequestMethod.POST)
//	public String toAddLzWeiMsg(LzWeiMessage msg_Q,Model model)
//		throws IllegalArgumentException, IllegalAccessException
//	{
//		LzWeiMessage msg = new LzWeiMessage();
//		StringUtil.copyProperties(msg_Q, msg);
//		model.addAttribute("command", msg);
//		return "/weixin/addLzWeiMsg";
//	}
//	
//	/**
//	 * ��Ϣ��ӱ���
//	 * */
//	@RequestMapping(value = "/addLzWeiMsg")
//	public String addLzWeiMsg(@ModelAttribute("command") LzWeiMessage msg,HttpSession session, RedirectAttributes redirectAttribute)
//	{
//		String content = msg.getWmgContent()==null?"":msg.getWmgContent();
//		String msgType = msg.getWmgMsgType()==null?"":msg.getWmgMsgType();
//		String aesType = msg.getWmgAesType()==null?"":msg.getWmgAesType();
//		LzAdmin admin  = (LzAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
//		msg.setWmgStatus("1000");
//		msg.setWmgRegistor(admin.getAdminId());
//		msg.setWmgRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		//Ҫ�ȸ�����Ϣ���ͺͼ������� ������Ϣ��XML��ʽ
//		if(msgType.equals("1"))
//		{
//			//ͼ����Ϣ�ȱ�����Ϣ 
//			//�ٱ���ͼ�Ĺ�ϵ
//
//		}
//		else if(msgType.equals("2"))
//		{
//			//������Ϣֱ�ӱ���
//			weixinmsgservice.addLzWeiMsg(msg);
//		}
//		return "redirect:/weixinmsg/queryLzWeiMsg";
//	}
//	
//	/**
//	 * ��ת����Ϣ�޸�
//	 * @throws IllegalAccessException 
//	 * @throws IllegalArgumentException 
//	 * 
//	 * */
//	
//	@RequestMapping(value = "/toUpdLzWeiMsg")
//	public String toUpdLzWeiMsg(@ModelAttribute("command") LzWeiMessage msg_Q,Model model) throws IllegalArgumentException, IllegalAccessException
//	{
//		LzWeiMessage msg = weixinmsgservice.getLzWeiMessageById(msg_Q.getWmgId());
//		StringUtil.copyProperties(msg_Q, msg);
//		model.addAttribute("command", msg);	
//		return "/weixin/updLzWeiMsg";
//	}
//	
//	@RequestMapping(value ="/updLzWeiMsg", method = RequestMethod.POST )
//	public String updLzWeiMsg(LzWeiMessage msg, HttpServletRequest request,RedirectAttributes redirectAttributes)
//	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
//	{
//		weixinmsgservice.updLzWeiMsg(msg);
//		redirectAttributes.addFlashAttribute("msgCode", "2");
//		redirectAttributes.addFlashAttribute("alertMsg", "�ظ���Ϣ�޸ĳɹ�");
//		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/weixinmsg/queryLzWeiMsg", msg));
//		return "redirect:/admin/toMsg";
//	}
//	
//	
//	
//	public String getTextMsgXMLByType(String content,String aesType)
//	{
//		return "";
//	}
//	
//	/**
//	 * ��ת����������
//	 * */
//	@RequestMapping(value = "/toTestLzWeiMsg")
//	public String toTestLzWeiMsg(HttpServletRequest request,Model model)
//	{
//		return "/weixin/testLzMsg";
//	}
//	
//	/**
//	 * ���Է�����Ϣ������
//	 * */
//	@RequestMapping(value = "/testLzWeiMsg")
//	public String testLzWeiMsg(HttpServletRequest request,Model model)
//	{
//		Map<String,String> requestMap = new HashMap<String,String>();
//		
//		//���ͷ��ʺ�(open_id)  
//        String fromUserName = request.getParameter("FromUserName")==null?"":(String)request.getParameter("FromUserName");  
//        //�����ʺ�  (app_Id)
//        String toUserName 	= request.getParameter("ToUserName")==null?"":(String)request.getParameter("ToUserName");  
//        //��Ϣ����  
//        String msgType 		= request.getParameter("MsgType")==null?"":(String)request.getParameter("MsgType");  
//        //����������Ϣ
//        String content		= request.getParameter("Content")==null?"":(String)request.getParameter("Content");
//        
//        String appId			= request.getParameter("Appid")==null?"":(String)request.getParameter("Appid");  
//        
//        requestMap.put("FromUserName", fromUserName);
//        requestMap.put("ToUserName", toUserName);
//        requestMap.put("MsgType", msgType);
//        requestMap.put("Content", content);
//        
//		LzWeiBaseMsgResp msgBase = weixinmsgservice.fenleiReq_Jar(requestMap,appId);
//		System.out.println(xstream.toXML(msgBase));
//		model.addAttribute("xml", xstream.toXML(msgBase));
//		request.setAttribute("xml", xstream.toXML(msgBase));
//		return "/weixin/testLzMsg";
//	}
//	
//	private static XStream xstream = new XStream(new XppDriver() {  
//        public HierarchicalStreamWriter createWriter(Writer out) {  
//            return new PrettyPrintWriter(out) {  
//                // ������xml�ڵ��ת��������CDATA���  
//                boolean cdata = true;  
//                @SuppressWarnings("unchecked")  
//                public void startNode(String name, Class clazz) {  
//                    super.startNode(name, clazz);  
//                }  
//                protected void writeText(QuickWriter writer, String text) {  
//                    if (cdata) {  
//                        writer.write("<![CDATA[");  
//                        writer.write(text);  
//                        writer.write("]]>");  
//                    } else {  
//                        writer.write(text);  
//                    }  
//                }  
//            };  
//        }  
//    }); 
//	
	
	public static void main(String [] args) throws ClientProtocolException, IOException
	{
		String encrypt_type 	= "aes";
    	String msg_signature 	= "f1b165ac582edb3011a4204d77119db77c5dca77";
    	String timestamp 		= "1418278106";
    	String nonce 			= "1624923258";
    	log.info("token:"+"sdlongzhong");
    	log.info("encrypt_type:"+encrypt_type);
    	log.info("msg_signature:"+msg_signature);
    	log.info("timestamp:"+timestamp);
    	log.info("nonce:"+nonce);
		String requestUrl = "http://localhost:8080/oilchem/getServiceEchostr?encrypt_type="+encrypt_type+"&msg_signature="+msg_signature+"&timestamp="+timestamp+"&nonce="+nonce;
		String outputStr  = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			"<xml>"+
			    "<ToUserName><![CDATA[gh_aad201754c1b]]></ToUserName>"+
			    "<FromUserName><![CDATA[o78cNt67oIJV8gm9qHqDye-9Y6hk]]></FromUserName>"+
			    "<CreateTime>1418278106</CreateTime>"+
			    "<MsgType><![CDATA[text]]></MsgType>"+
			    "<Content><![CDATA[ju]]></Content>"+
			    "<MsgId>6091458082104059262</MsgId>"+
			    "<Encrypt><![CDATA[NJLPn/YYF1NQ6Rw51uLlVzJbAxtC+9BqMc60UYuHhCos36Jc9bN5uRYviYQoi5Jthm0zNIsrVYiEbXMdsov+Ju5Tlo/uzG+GWvH+i35+L+yOkT0S9lDKdYfB58kW+gphFKAjGO0KtvAzsY4TnaTF60J7MOrYD6ZQCnEZIdqJ0r1rudLSFSjr4s7nhHvfHn/WQ34ucAhxC0mw0t81pYsS8m5ekYvDES1k1wBtDdOXaUjRY2XiokdcaWOmBq4+qj4smtXjwOabJRhPKLCbbwaF+5kSKtTJY+PyaHLjQr0HnTRr88RqeMKZRuPPMYXlg3SGMrv3ctupUqIJ8Zjk4rCRp45bUeXUEqc05uMeppfMRvxzggNuojMIImhQy1QGxpfoBI3KH/kGaKyAg/nl16pj+WUhhCdePtZ3+Q/LrdSbFAw=]]></Encrypt>"+
			"</xml>";
		
		JSONObject msgObj = new JSONObject();
		msgObj.put("touser", outputStr);
		
		HttpClient client = new DefaultHttpClient();    
		HttpPost post = new HttpPost(requestUrl);    
	    JSONObject response = null;    
	    StringEntity s = new StringEntity(msgObj.toString());    
		s.setContentEncoding("UTF-8");    
		post.setEntity(s);    
		HttpResponse res = client.execute(post);    
		if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
		{       
		    HttpEntity entity = res.getEntity();   
		    String result = EntityUtils.toString(res.getEntity(),"utf-8").trim();
		    System.out.println(result);
		} 
	}
	
	
}

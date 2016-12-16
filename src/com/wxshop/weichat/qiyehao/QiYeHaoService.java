package com.wxshop.weichat.qiyehao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pudding.weichat.Constant;
import cn.pudding.weichat.http.HttpUtil;
import cn.pudding.weichat.message.TextMsgUtil;
import cn.pudding.weichat.message.response.WcWeiBaseMsgResp;
import cn.pudding.weichat.message.response.WcWeiTextMsgResp;

import com.wxshop.sys.IShopAdminService;
import com.wxshop.sys.WcShopAdmin;
import com.wxshop.util.DateUtil;

@Service
public class QiYeHaoService implements IQiYeHaoService
{
	private static Logger log = Logger.getLogger(QiYeHaoService.class);
	
	@Autowired
	private IShopAdminService adminService ;
	
	@Autowired
	private IShopAdminQiyehaoService adminQiyehaoService; 
	
	
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public String getTokenJson(String corpid, String corpsecret)
	{
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n��ȡAccessToken\n").append(url).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public String getToken(String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException
	{
		String tokenJson = getTokenJson(corpid, corpsecret);
		Map<String, Object> tokenMap = objectMapper.readValue(tokenJson, Map.class);
		String token = (String) tokenMap.get("access_token");
		return token;
	}
	
	public String addDept(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "POST", json);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n��������\n").append(url).append("\n").append(json).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	public String updDept(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "POST", json);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n���²���\n").append(url).append("\n").append(json).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	public String delDept(String corpid, String corpsecret, Integer id) throws JsonParseException, JsonMappingException,  IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=" + token + "&id=" + id;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\nɾ������\n").append(url).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	public String queryDept(String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n��ȡ�����б�\n").append(url).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	public String addUser(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "POST", json);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n������Ա\n").append(url).append("\n").append(json).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	public String updUser(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "POST", json);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n���³�Ա\n").append(url).append("\n").append(json).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	public String delUser(String corpid, String corpsecret, String userid) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=" + token + "&userid=" + userid;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\nɾ����Ա\n").append(url).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	public String queryUser(String corpid, String corpsecret, String userid) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + token + "&userid=" + userid;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n��ȡ��Ա\n").append(url).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	public String queryDeptUser(String corpid, String corpsecret, Integer departmentId, String fetchChild, String status) 
		throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=" + token + "&department_id=" + departmentId
		+ ((fetchChild != null && fetchChild.length() > 0) ? "&fetch_child=" + fetchChild : "")
		+ ((status != null && status.length() > 0) ? "&status=" + status : "");
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n��ȡ���ų�Ա\n").append(url).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	public String sendMessage(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "POST", json);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n������Ϣ\n").append(url).append("\n").append(json).append("\n�����").append(result);
		log.info(builder);
		return result;
	}
	
	public List<Map<String, Object>> queryDeptListAll(String corpid,String corpsecret) throws JsonParseException, JsonMappingException, IOException 
	{
		// TODO Auto-generated method stub
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET",null);
		JSONArray jsonAry = jsonObj.getJSONArray("department");
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		for(int i=0;i<jsonAry.length();i++)
		{
			JSONObject obj = jsonAry.getJSONObject(i);
			Integer id = (Integer)obj.get("id");
			String deptName = (String)obj.get("name");
			Integer parentId = (Integer)obj.get("parentid");
			if(id>1)//���ܰ�"���ڵ�"ɾ����
			{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("txl_id", id);
				map.put("dept_name", deptName);
				map.put("parentid",parentId);
				listMap.add(map);
			}
		}
		return listMap;
	}
	
	public List<Map<String, Object>> queryUserAll(String corpid,String corpsecret) throws JsonParseException, JsonMappingException, IOException 
	{
		// TODO Auto-generated method stub
		String token = getToken(corpid,corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+token+"&fetch_child=1&status=0";
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET",null);
		JSONArray jsonAry = jsonObj.getJSONArray("userlist");
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		for(int i=0;i<jsonAry.length();i++)
		{
			JSONObject obj = jsonAry.getJSONObject(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("username", (String)obj.get("userid"));
			listMap.add(map);
		}
		return listMap;
	}

	public String getErrmsg(String errcode)
	{
			 if( errcode.equals("0") ) 		{ return "����ɹ�"; }
		else if( errcode.equals("-1") ) 	{ return "ϵͳ��æ"; } 
		else if( errcode.equals("40001") ) 	{ return "��ȡaccess_tokenʱSecret���󣬻���access_token��Ч";}  
		else if( errcode.equals("40002") ) 	{ return "���Ϸ���ƾ֤����  ";}
		else if( errcode.equals("40003") ) 	{ return "���Ϸ���UserID ";}
		else if( errcode.equals("40004") ) 	{ return "���Ϸ���ý���ļ�����  ";}
		else if( errcode.equals("40005") ) 	{ return "���Ϸ����ļ�����  ";}
		else if( errcode.equals("40006") ) 	{ return "���Ϸ����ļ���С  ";}
		else if( errcode.equals("40007") ) 	{ return "���Ϸ���ý���ļ�id ";}
		else if( errcode.equals("40008") ) 	{ return "���Ϸ�����Ϣ����  ";}
		else if( errcode.equals("40013") ) 	{ return "���Ϸ���corpid ";}
		else if( errcode.equals("40014") ) 	{ return "���Ϸ���access_token ";}
		else if( errcode.equals("40015") ) 	{ return "���Ϸ��Ĳ˵�����   ";}
		else if( errcode.equals("40016") ) 	{ return "���Ϸ��İ�ť����   ";}
		else if( errcode.equals("40017") ) 	{ return "���Ϸ��İ�ť����   ";}
		else if( errcode.equals("40018") ) 	{ return "���Ϸ��İ�ť���ֳ���   ";}
		else if( errcode.equals("40019") ) 	{ return "���Ϸ��İ�ťKEY����   ";}
		else if( errcode.equals("40020") ) 	{ return "���Ϸ��İ�ťURL����    ";}
		else if( errcode.equals("40021") ) 	{ return "���Ϸ��Ĳ˵��汾��   ";}
		else if( errcode.equals("40022") ) 	{ return "���Ϸ����Ӳ˵�����  ";}
		else if( errcode.equals("40023") ) 	{ return "���Ϸ����Ӳ˵���ť����   ";}
		else if( errcode.equals("40024") ) 	{ return "���Ϸ����Ӳ˵���ť����    ";}
		else if( errcode.equals("40025") ) 	{ return "���Ϸ����Ӳ˵���ť���ֳ���   ";}
		else if( errcode.equals("40026") ) 	{ return "���Ϸ����Ӳ˵���ťKEY����   ";}
		else if( errcode.equals("40027") ) 	{ return "���Ϸ����Ӳ˵���ťURL����  ";}
		else if( errcode.equals("40028") ) 	{ return "���Ϸ����Զ���˵�ʹ��Ա��  ";}
		else if( errcode.equals("40029") ) 	{ return "���Ϸ���oauth_code ";}
		else if( errcode.equals("40031") ) 	{ return "���Ϸ���UserID�б�    ";}
		else if( errcode.equals("40032") ) 	{ return "���Ϸ���UserID�б���     ";}
		else if( errcode.equals("40033") ) 	{ return "���Ϸ��������ַ������ܰ���\\uxxxx��ʽ���ַ� ";}
		else if( errcode.equals("40035") ) 	{ return "���Ϸ��Ĳ���                                           ";}
		else if( errcode.equals("40038") ) 	{ return "���Ϸ��������ʽ                                 ";}
		else if( errcode.equals("40039") ) 	{ return "���Ϸ���URL����                                   ";}
		else if( errcode.equals("40040") ) 	{ return "���Ϸ��Ĳ��token           ";}
		else if( errcode.equals("40041") ) 	{ return "���Ϸ��Ĳ��id              ";}
		else if( errcode.equals("40042") ) 	{ return "���Ϸ��Ĳ���Ự                                 ";}
		else if( errcode.equals("40048") ) 	{ return "url�а������Ϸ�domain       ";}
		else if( errcode.equals("40054") ) 	{ return "���Ϸ����Ӳ˵�url����                    ";}
		else if( errcode.equals("40055") ) 	{ return "���Ϸ��İ�ťurl����                         ";}
		else if( errcode.equals("40056") ) 	{ return "���Ϸ���agentid             ";}
		else if( errcode.equals("40057") ) 	{ return "���Ϸ���callbackurl         ";}
		else if( errcode.equals("40058") ) 	{ return "���Ϸ��ĺ������                                 ";}
		else if( errcode.equals("40059") ) 	{ return "���Ϸ����ϱ�����λ�ñ�־λ        ";}
		else if( errcode.equals("40060") ) 	{ return "�����ϱ�����λ�ñ�־λʱû������callbackurl  ";}
		else if( errcode.equals("40061") ) 	{ return "����Ӧ��ͷ��ʧ��                                 ";}
		else if( errcode.equals("40062") ) 	{ return "���Ϸ���Ӧ��ģʽ                                 ";}
		else if( errcode.equals("40063") ) 	{ return "�������Ϊ��     "; }
		else if( errcode.equals("40064") ) 	{ return "�����������Ѵ���    "; }
		else if( errcode.equals("40065") ) 	{ return "���Ϸ��Ĺ��������ֳ���    "; }
		else if( errcode.equals("40066") ) 	{ return "���Ϸ��Ĳ����б�   "; }
		else if( errcode.equals("40067") ) { return "���ⳤ�Ȳ��Ϸ�   "; }
		else if( errcode.equals("40068") ) { return "���Ϸ��ı�ǩID "; }
		else if( errcode.equals("40069") ) { return "���Ϸ��ı�ǩID�б�   "; }
		else if( errcode.equals("40070") ) { return "�б������б�ǩ���û���ID�����Ϸ�   "; }
		else if( errcode.equals("40071") ) { return "���Ϸ��ı�ǩ���֣���ǩ�����Ѿ�����  "; }
		else if( errcode.equals("40072") ) { return "���Ϸ��ı�ǩ���ֳ���    "; }
		else if( errcode.equals("40073") ) { return "���Ϸ���openid  "; }
		else if( errcode.equals("40074") ) { return "news��Ϣ��֧��ָ��Ϊ�߱�����Ϣ                    "; }
		else if( errcode.equals("40077") ) { return "���Ϸ���Ԥ��Ȩ��                                  ";}
		else if( errcode.equals("40078") ) { return "���Ϸ�����ʱ��Ȩ��                             ";}
		else if( errcode.equals("40079") ) { return "���Ϸ�����Ȩ��Ϣ                                  ";}
		else if( errcode.equals("40080") ) { return "���Ϸ���suitesecret                               "; }
		else if( errcode.equals("40082") ) { return "���Ϸ���suitetoken                                "; }
		else if( errcode.equals("40083") ) { return "���Ϸ���suiteid                                   "; }
		else if( errcode.equals("40084") ) { return "���Ϸ���������Ȩ��                                "; }
		else if( errcode.equals("40085") ) { return "���Ϸ���suiteticket                             "; }
		else if( errcode.equals("40086") ) { return "���Ϸ��ĵ�����Ӧ��appid                          "; }
		else if( errcode.equals("41001") ) { return "ȱ��access_token����                              "; }
		else if( errcode.equals("41002") ) { return "ȱ��corpid����                                    "; }
		else if( errcode.equals("41003") ) { return "ȱ��refresh_token����                             "; }
		else if( errcode.equals("41004") ) { return "ȱ��secret����                                    "; }
		else if( errcode.equals("41005") ) { return "ȱ�ٶ�ý���ļ�����                                "; }
		else if( errcode.equals("41006") ) { return "ȱ��media_id����                                  "; }
		else if( errcode.equals("41007") ) { return "ȱ���Ӳ˵�����                                    "; }
		else if( errcode.equals("41008") ) { return "ȱ��oauth code                                    "; }
		else if( errcode.equals("41009") ) { return "ȱ��UserID                                        "; }
		else if( errcode.equals("41010") ) { return "ȱ��url                                           "; }
		else if( errcode.equals("41011") ) { return "ȱ��agentid                                       "; }
		else if( errcode.equals("41012") ) { return "ȱ��Ӧ��ͷ��mediaid                               "; }
		else if( errcode.equals("41013") ) { return "ȱ��Ӧ������                                      "; }
		else if( errcode.equals("41014") ) { return "ȱ��Ӧ������                                      "; }
		else if( errcode.equals("41015") ) { return "ȱ��Content                                       "; }
		else if( errcode.equals("41016") ) { return "ȱ�ٱ���                                          "; }
		else if( errcode.equals("41017") ) { return "ȱ�ٱ�ǩID                                        "; }
		else if( errcode.equals("41018") ) { return "ȱ�ٱ�ǩ����                                      "; }
		else if( errcode.equals("41021") ) { return "ȱ��suiteid                                       "; }
		else if( errcode.equals("41022") ) { return "ȱ��suitetoken                                    "; }
		else if( errcode.equals("41023") ) { return "ȱ��suiteticket                                   "; }
		else if( errcode.equals("41024") ) { return "ȱ��suitesecret                                   "; }
		else if( errcode.equals("41025") ) { return "ȱ��������Ȩ��                                    "; }
		else if( errcode.equals("42001") ) { return "access_token��ʱ                                  "; }
		else if( errcode.equals("42002") ) { return "refresh_token��ʱ                                 "; }
		else if( errcode.equals("42003") ) { return "oauth_code��ʱ                                    "; }
		else if( errcode.equals("42004") ) { return "���token��ʱ                                     "; }
		else if( errcode.equals("42007") ) { return "Ԥ��Ȩ��ʧЧ                                      "; }
		else if( errcode.equals("42008") ) { return "��ʱ��Ȩ��ʧЧ                                    "; }
		else if( errcode.equals("42009") ) { return "suitetokenʧЧ                                    "; }
		else if( errcode.equals("43001") ) { return "��ҪGET����                                       "; }
		else if( errcode.equals("43002") ) { return "��ҪPOST����                                      "; }
		else if( errcode.equals("43003") ) { return "��ҪHTTPS"; }
		else if( errcode.equals("43004") ) { return "��Ҫ�����߹�ע "; }
		else if( errcode.equals("43005") ) { return "��Ҫ���ѹ�ϵ"; }
		else if( errcode.equals("43006") ) { return "��Ҫ����  "; }
		else if( errcode.equals("43007") ) { return "��Ҫ��Ȩ "; }
		else if( errcode.equals("43008") ) { return "��Ҫ֧����Ȩ"; }
		else if( errcode.equals("43009") ) { return "��ҪԱ���ѹ�ע"; }
		else if( errcode.equals("43010") ) { return "��Ҫ���ڻص�ģʽ "; }
		else if( errcode.equals("43011") ) { return "��Ҫ��ҵ��Ȩ "; }
		else if( errcode.equals("44001") ) { return "��ý���ļ�Ϊ��"; }
		else if( errcode.equals("44002") ) { return "POST�����ݰ�Ϊ��"; }
		else if( errcode.equals("44003") ) { return "ͼ����Ϣ����Ϊ��"; }
		else if( errcode.equals("44004") ) { return "�ı���Ϣ����Ϊ��"; }
		else if( errcode.equals("45001") ) { return "��ý���ļ���С��������"; }
		else if( errcode.equals("45002") ) { return "��Ϣ���ݳ�������"; }
		else if( errcode.equals("45003") ) { return "�����ֶγ�������"; }
		else if( errcode.equals("45004") ) { return "�����ֶγ�������"; }
		else if( errcode.equals("45005") ) { return "�����ֶγ�������"; }
		else if( errcode.equals("45006") ) { return "ͼƬ�����ֶγ�������"; }
		else if( errcode.equals("45007") ) { return "��������ʱ�䳬������"; }
		else if( errcode.equals("45008") ) { return "ͼ����Ϣ��������"; }
		else if( errcode.equals("45009") ) { return "�ӿڵ��ó�������"; }
		else if( errcode.equals("45010") ) { return "�����˵������������� "; }
		else if( errcode.equals("45015") ) { return "�ظ�ʱ�䳬������"; }
		else if( errcode.equals("45016") ) { return "ϵͳ���飬�������޸�"; }
		else if( errcode.equals("45017") ) { return "�������ֹ���"; }
		else if( errcode.equals("45018") ) { return "����������������"; }
		else if( errcode.equals("45024") ) { return "�˺�������������"; }
		else if( errcode.equals("46001") ) { return "������ý������"; }
		else if( errcode.equals("46002") ) { return "�����ڵĲ˵��汾"; }
		else if( errcode.equals("46003") ) { return "�����ڵĲ˵�����"; }
		else if( errcode.equals("46004") ) { return "�����ڵ�Ա��"; }
		else if( errcode.equals("47001") ) { return "����JSON/XML���ݴ���"; }
		else if( errcode.equals("48002") ) { return "Api����"; }
		else if( errcode.equals("48003") ) { return "suitetoken��Ч"; }
		else if( errcode.equals("48004") ) { return "��Ȩ��ϵ��Ч"; }
		else if( errcode.equals("50001") ) { return "redirect_uriδ��Ȩ"; }
		else if( errcode.equals("50002") ) { return "Ա������Ȩ�޷�Χ"; }
		else if( errcode.equals("50003") ) { return "Ӧ����ͣ��"; }
		else if( errcode.equals("50004") ) { return "Ա��״̬����ȷ��δ��ע״̬��"; }
		else if( errcode.equals("50005") ) { return "��ҵ�ѽ���"; }
		else if( errcode.equals("60001") ) { return "���ų��Ȳ���������"; }
		else if( errcode.equals("60002") ) { return "���Ų㼶��ȳ�������"; }
		else if( errcode.equals("60003") ) { return "���Ų�����"; }
		else if( errcode.equals("60004") ) { return "���ײ��Ų�����"; }
		else if( errcode.equals("60005") ) { return "������ɾ���г�Ա�Ĳ���"; }
		else if( errcode.equals("60006") ) { return "������ɾ�����Ӳ��ŵĲ���"; }
		else if( errcode.equals("60007") ) { return "������ɾ��������"; }
		else if( errcode.equals("60008") ) { return "���������Ѵ���"; }
		else if( errcode.equals("60009") ) { return "�������ƺ��зǷ��ַ�"; }
		else if( errcode.equals("60010") ) { return "���Ŵ���ѭ����ϵ"; }
		else if( errcode.equals("60011") ) { return "����ԱȨ�޲��㣬��user/department/agent����Ȩ��   "; }
		else if( errcode.equals("60012") ) { return "������ɾ��Ĭ��Ӧ��                                "; }
		else if( errcode.equals("60013") ) { return "������ر�Ӧ��                                    "; }
		else if( errcode.equals("60014") ) { return "��������Ӧ��                                    "; }
		else if( errcode.equals("60015") ) { return "�������޸�Ĭ��Ӧ�ÿɼ���Χ                        "; }
		else if( errcode.equals("60016") ) { return "������ɾ�����ڳ�Ա�ı�ǩ                          "; }
		else if( errcode.equals("60017") ) { return "������������ҵ                                    "; }
		else if( errcode.equals("60019") ) { return "����������Ӧ�õ���λ���ϱ�����                    "; }
		else if( errcode.equals("60020") ) { return "����ip���ڰ�����֮��                              "; }
		else if( errcode.equals("60102") ) { return "UserID�Ѵ���                                      "; }
		else if( errcode.equals("60103") ) { return "�ֻ����벻�Ϸ�                                    "; }
		else if( errcode.equals("60104") ) { return "�ֻ������Ѵ���                                    "; }
		else if( errcode.equals("60105") ) { return "���䲻�Ϸ�                                        "; }
		else if( errcode.equals("60106") ) { return "�����Ѵ���                                        "; }
		else if( errcode.equals("60107") ) { return "΢�źŲ��Ϸ�                                      "; }
		else if( errcode.equals("60108") ) { return "΢�ź��Ѵ���                                      "; }
		else if( errcode.equals("60109") ) { return "QQ���Ѵ���                                        "; }
		else if( errcode.equals("60110") ) { return "���Ÿ�����������                                  "; }
		else if( errcode.equals("60111") ) { return "UserID������                                      "; }
		else if( errcode.equals("60112") ) { return "��Ա�������Ϸ�                                    "; }
		else if( errcode.equals("60113") ) { return "�����֤��Ϣ��΢�ź�/�ֻ�/���䣩����ͬʱΪ��      "; }
		else if( errcode.equals("60114") ) { return "�Ա𲻺Ϸ�                                        "; }
		else { return "δ֪�������:"+errcode; }
	}

	public String processRequest(HttpServletRequest request, String token,String encodingAESKey, String appId, String type) throws Exception 
	{
		// TODO Auto-generated method stub
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
	    requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
	    String encrypt = requestMap.get("Encrypt");
	    requestMap = cn.pudding.weichat.message.MessageUtil.encryptToMap( encrypt, token, encodingAESKey, appId);
	    // �ظ���ͬ���͵Ľṹ1
	    WcWeiBaseMsgResp respMsg = fenleiReq(requestMap);  
	    //log.error(respMsg.getCreateTime());
	    //log.error(respMsg.getFromUserName());
	    //log.error(respMsg.getMsgType());
	    respMessage = cn.pudding.weichat.message.MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce);
	    //log.error("ת��XML:"+respMessage);
	    respMessage = cn.pudding.weichat.message.MessageUtil.getAesXml(respMessage, "",token, encodingAESKey, appId, msg_signature, timestamp, nonce);
	    //log.error("����XML:"+respMessage);
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
        String fromUserName = requestMap.get("FromUserName")==null?""	:requestMap.get("FromUserName"); 
        log.error("fromUsername:"+fromUserName);
        // �����ʺ�  
        String toUserName 	= requestMap.get("ToUserName")==null?""		:requestMap.get("ToUserName");  
        // ��Ϣ����  
        String msgType 		= requestMap.get("MsgType")==null?""		:requestMap.get("MsgType");  
        //����������Ϣ
        String content		= requestMap.get("Content")==null?""		:requestMap.get("Content");
        String respContent = "";
        WcWeiBaseMsgResp respMessage = new WcWeiBaseMsgResp();
        respMessage.setToUserName(fromUserName);  
        respMessage.setFromUserName(toUserName);  
        respMessage.setCreateTime(new Date().getTime());  
        respMessage.setFuncFlag(0);
        /**
         * 1����֤�û��ĺϷ���
         *  �����û�����֤��ǰ�û��Ƿ����
         * **/
        WcShopAdmin admin = adminService.queryAdminByUsername(fromUserName);
        if(admin==null)
        {
        	//ֱ�ӷ���һ����ǰ�û�������
        	WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(respMessage);
    		txtMsg.setContent("����΢�źŰ󶨵��˻������ڣ�����������[���ѻ�Ա]״̬!");
    		return txtMsg;
        }
        // �ı���Ϣ  
        if(msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) 
        {  
            //�����ｫ���͹�������Ϣ���з��ദ��
//        	if(content.startsWith("����"))
//        	{
//        		WcWeiTextMsgResp txtMsg = new LzWeiTextMsgResp(respMessage);
//        		txtMsg.setContent("http://meeting.oilchem.net/");
//        		respMessage = txtMsg;
//        	}
//        	else if(content.startsWith("ʯ��ͨ")||content.startsWith("����")||content.startsWith("����")||content.startsWith("����"))
//        	{
//        		//respMessage = com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(contentReq);
//        	}
//        	else
//        	{
        		respMessage = TextMsgUtil.getDefualtTextMsg(respMessage);
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
            // ����  [��ע]
            if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) 
            {  
            	log.info(admin.getWsaUsername()+"��ע��΢����ҵ��!");
            	WcAdminWeixin adminWeixin =  adminQiyehaoService.getAdminWeixin(admin.getWsaUsername());
            	if(adminWeixin!=null)
            	{
            		log.info(admin.getWsaUsername()+"�޸�");
            		adminWeixin.setAweStatus("1");
            		adminWeixin.setAweAdminId(admin.getWsaId());
            		adminQiyehaoService.updAdminWeixin(adminWeixin);
            	}
            	else
            	{
            		log.info(admin.getWsaUsername()+"���");
            		adminWeixin = new WcAdminWeixin();
                	adminWeixin.setAweUserId(admin.getWsaUsername());
                	adminWeixin.setAweAdminId(admin.getWsaId());
                	adminWeixin.setAweStatus("1");
                	adminWeixin.setAweRegistDate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
                	adminWeixin.setAweRegistor(1);
                	adminQiyehaoService.addAdminWeixin(adminWeixin);
            	}
            	

            }  
            // ȡ������  [ȡ����ע]
            else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) 
            {  
                // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
            	respMessage.setMsgType(Constant.EVENT_TYPE_UNSUBSCRIBE);
            	//ȡ�����ĺ�,ɾ���󶨹�ϵ
            	adminQiyehaoService.delAdminWeixin(fromUserName);
            }  
            // �Զ���˵�����¼�  
            else if (eventType.toUpperCase().equals(Constant.EVENT_TYPE_CLICK)) 
            {  
                // TODO �Զ���˵����������Ϣ
            	
            	respContent = "������˰�ť";  
            	String eventKey		= requestMap.get("EventKey")==null?"":requestMap.get("EventKey");
            	WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(respMessage);
            	if(eventKey.equals("TONGXUNLU"))
	            {
//  	            	String delFlag = admin.getDelFlag();
//	            	if(delFlag.equals("1"))
//	            	{
//	            		//2������תָ����ַ
//	                   	String urlTxl = "<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3666a775468948ac&redirect_uri=https%3A%2F%2Fqy.weixin.qq.com%2Fcgi-bin%2Fwap_contact_v4&response_type=code&scope=snsapi_base&state=MTAxMTAwMjY0NA%3D%3D#wechat_redirect\">�����ȡͨѶ¼</a>";
//	                   	respContent = urlTxl;
//	                   	txtMsg.setContent(urlTxl);
//	                   	respMessage = txtMsg;
//	                }
//	            	else
//	           		{
//	            		txtMsg.setContent("����û��ת��ʽԱ�����ܻ�ȡ�ڲ�ͨѶ¼");
//	                   	respMessage = txtMsg;
//	            	}
	            }
	            else if(eventKey.equals("FORGETPWD"))
	            {
//	  	           	//2������תָ����ַ
//	            	String urlTxl = "<a href=\"http://gl.oilchem.net/oilchem/admin/toForgetPwd?tag=1&username="+admin.getUsername()+"\">�����������</a>";
//                   	respContent = urlTxl;
//                   	txtMsg.setContent(urlTxl);
//                   	respMessage = txtMsg;
	            }
	            else
	            {
	            	txtMsg.setContent("δ�����KEY"+eventKey);
	               	respMessage = txtMsg;
	            }
        	} 
            else if (eventType.equals(Constant.EVENT_TYPE_SCAN))
            {
            	
            }
            else if(eventType.equals(Constant.EVENT_TYPE_VIEW))
            {
            	//�����ʱ�� ���������
            	WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(respMessage);
        		txtMsg.setContent("���ղŵ����һ����ҵ�ŵ�����");
        		respMessage = txtMsg;
            }
        }
    	return respMessage;
    }


	
    
    
}

























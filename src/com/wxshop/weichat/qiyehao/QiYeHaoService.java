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
		StringBuilder builder = new StringBuilder("\n获取AccessToken\n").append(url).append("\n结果：").append(result);
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
		StringBuilder builder = new StringBuilder("\n创建部门\n").append(url).append("\n").append(json).append("\n结果：").append(result);
		log.info(builder);
		return result;
	}
	
	public String updDept(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "POST", json);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n更新部门\n").append(url).append("\n").append(json).append("\n结果：").append(result);
		log.info(builder);
		return result;
	}
	
	public String delDept(String corpid, String corpsecret, Integer id) throws JsonParseException, JsonMappingException,  IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=" + token + "&id=" + id;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n删除部门\n").append(url).append("\n结果：").append(result);
		log.info(builder);
		return result;
	}
	
	public String queryDept(String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n获取部门列表\n").append(url).append("\n结果：").append(result);
		log.info(builder);
		return result;
	}
	
	public String addUser(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "POST", json);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n创建成员\n").append(url).append("\n").append(json).append("\n结果：").append(result);
		log.info(builder);
		return result;
	}
	
	public String updUser(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "POST", json);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n更新成员\n").append(url).append("\n").append(json).append("\n结果：").append(result);
		log.info(builder);
		return result;
	}
	
	public String delUser(String corpid, String corpsecret, String userid) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=" + token + "&userid=" + userid;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n删除成员\n").append(url).append("\n结果：").append(result);
		log.info(builder);
		return result;
	}
	
	public String queryUser(String corpid, String corpsecret, String userid) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + token + "&userid=" + userid;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n获取成员\n").append(url).append("\n结果：").append(result);
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
		StringBuilder builder = new StringBuilder("\n获取部门成员\n").append(url).append("\n结果：").append(result);
		log.info(builder);
		return result;
	}
	
	public String sendMessage(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException
	{
		String token = getToken(corpid, corpsecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token;
		JSONObject jsonObj = HttpUtil.httpRequestJson(url, "POST", json);
		String result = jsonObj.toString();
		StringBuilder builder = new StringBuilder("\n发送消息\n").append(url).append("\n").append(json).append("\n结果：").append(result);
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
			if(id>1)//不能把"根节点"删除了
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
			 if( errcode.equals("0") ) 		{ return "请求成功"; }
		else if( errcode.equals("-1") ) 	{ return "系统繁忙"; } 
		else if( errcode.equals("40001") ) 	{ return "获取access_token时Secret错误，或者access_token无效";}  
		else if( errcode.equals("40002") ) 	{ return "不合法的凭证类型  ";}
		else if( errcode.equals("40003") ) 	{ return "不合法的UserID ";}
		else if( errcode.equals("40004") ) 	{ return "不合法的媒体文件类型  ";}
		else if( errcode.equals("40005") ) 	{ return "不合法的文件类型  ";}
		else if( errcode.equals("40006") ) 	{ return "不合法的文件大小  ";}
		else if( errcode.equals("40007") ) 	{ return "不合法的媒体文件id ";}
		else if( errcode.equals("40008") ) 	{ return "不合法的消息类型  ";}
		else if( errcode.equals("40013") ) 	{ return "不合法的corpid ";}
		else if( errcode.equals("40014") ) 	{ return "不合法的access_token ";}
		else if( errcode.equals("40015") ) 	{ return "不合法的菜单类型   ";}
		else if( errcode.equals("40016") ) 	{ return "不合法的按钮个数   ";}
		else if( errcode.equals("40017") ) 	{ return "不合法的按钮类型   ";}
		else if( errcode.equals("40018") ) 	{ return "不合法的按钮名字长度   ";}
		else if( errcode.equals("40019") ) 	{ return "不合法的按钮KEY长度   ";}
		else if( errcode.equals("40020") ) 	{ return "不合法的按钮URL长度    ";}
		else if( errcode.equals("40021") ) 	{ return "不合法的菜单版本号   ";}
		else if( errcode.equals("40022") ) 	{ return "不合法的子菜单级数  ";}
		else if( errcode.equals("40023") ) 	{ return "不合法的子菜单按钮个数   ";}
		else if( errcode.equals("40024") ) 	{ return "不合法的子菜单按钮类型    ";}
		else if( errcode.equals("40025") ) 	{ return "不合法的子菜单按钮名字长度   ";}
		else if( errcode.equals("40026") ) 	{ return "不合法的子菜单按钮KEY长度   ";}
		else if( errcode.equals("40027") ) 	{ return "不合法的子菜单按钮URL长度  ";}
		else if( errcode.equals("40028") ) 	{ return "不合法的自定义菜单使用员工  ";}
		else if( errcode.equals("40029") ) 	{ return "不合法的oauth_code ";}
		else if( errcode.equals("40031") ) 	{ return "不合法的UserID列表    ";}
		else if( errcode.equals("40032") ) 	{ return "不合法的UserID列表长度     ";}
		else if( errcode.equals("40033") ) 	{ return "不合法的请求字符，不能包含\\uxxxx格式的字符 ";}
		else if( errcode.equals("40035") ) 	{ return "不合法的参数                                           ";}
		else if( errcode.equals("40038") ) 	{ return "不合法的请求格式                                 ";}
		else if( errcode.equals("40039") ) 	{ return "不合法的URL长度                                   ";}
		else if( errcode.equals("40040") ) 	{ return "不合法的插件token           ";}
		else if( errcode.equals("40041") ) 	{ return "不合法的插件id              ";}
		else if( errcode.equals("40042") ) 	{ return "不合法的插件会话                                 ";}
		else if( errcode.equals("40048") ) 	{ return "url中包含不合法domain       ";}
		else if( errcode.equals("40054") ) 	{ return "不合法的子菜单url域名                    ";}
		else if( errcode.equals("40055") ) 	{ return "不合法的按钮url域名                         ";}
		else if( errcode.equals("40056") ) 	{ return "不合法的agentid             ";}
		else if( errcode.equals("40057") ) 	{ return "不合法的callbackurl         ";}
		else if( errcode.equals("40058") ) 	{ return "不合法的红包参数                                 ";}
		else if( errcode.equals("40059") ) 	{ return "不合法的上报地理位置标志位        ";}
		else if( errcode.equals("40060") ) 	{ return "设置上报地理位置标志位时没有设置callbackurl  ";}
		else if( errcode.equals("40061") ) 	{ return "设置应用头像失败                                 ";}
		else if( errcode.equals("40062") ) 	{ return "不合法的应用模式                                 ";}
		else if( errcode.equals("40063") ) 	{ return "红包参数为空     "; }
		else if( errcode.equals("40064") ) 	{ return "管理组名字已存在    "; }
		else if( errcode.equals("40065") ) 	{ return "不合法的管理组名字长度    "; }
		else if( errcode.equals("40066") ) 	{ return "不合法的部门列表   "; }
		else if( errcode.equals("40067") ) { return "标题长度不合法   "; }
		else if( errcode.equals("40068") ) { return "不合法的标签ID "; }
		else if( errcode.equals("40069") ) { return "不合法的标签ID列表   "; }
		else if( errcode.equals("40070") ) { return "列表中所有标签（用户）ID都不合法   "; }
		else if( errcode.equals("40071") ) { return "不合法的标签名字，标签名字已经存在  "; }
		else if( errcode.equals("40072") ) { return "不合法的标签名字长度    "; }
		else if( errcode.equals("40073") ) { return "不合法的openid  "; }
		else if( errcode.equals("40074") ) { return "news消息不支持指定为高保密消息                    "; }
		else if( errcode.equals("40077") ) { return "不合法的预授权码                                  ";}
		else if( errcode.equals("40078") ) { return "不合法的临时授权码                             ";}
		else if( errcode.equals("40079") ) { return "不合法的授权信息                                  ";}
		else if( errcode.equals("40080") ) { return "不合法的suitesecret                               "; }
		else if( errcode.equals("40082") ) { return "不合法的suitetoken                                "; }
		else if( errcode.equals("40083") ) { return "不合法的suiteid                                   "; }
		else if( errcode.equals("40084") ) { return "不合法的永久授权码                                "; }
		else if( errcode.equals("40085") ) { return "不合法的suiteticket                             "; }
		else if( errcode.equals("40086") ) { return "不合法的第三方应用appid                          "; }
		else if( errcode.equals("41001") ) { return "缺少access_token参数                              "; }
		else if( errcode.equals("41002") ) { return "缺少corpid参数                                    "; }
		else if( errcode.equals("41003") ) { return "缺少refresh_token参数                             "; }
		else if( errcode.equals("41004") ) { return "缺少secret参数                                    "; }
		else if( errcode.equals("41005") ) { return "缺少多媒体文件数据                                "; }
		else if( errcode.equals("41006") ) { return "缺少media_id参数                                  "; }
		else if( errcode.equals("41007") ) { return "缺少子菜单数据                                    "; }
		else if( errcode.equals("41008") ) { return "缺少oauth code                                    "; }
		else if( errcode.equals("41009") ) { return "缺少UserID                                        "; }
		else if( errcode.equals("41010") ) { return "缺少url                                           "; }
		else if( errcode.equals("41011") ) { return "缺少agentid                                       "; }
		else if( errcode.equals("41012") ) { return "缺少应用头像mediaid                               "; }
		else if( errcode.equals("41013") ) { return "缺少应用名字                                      "; }
		else if( errcode.equals("41014") ) { return "缺少应用描述                                      "; }
		else if( errcode.equals("41015") ) { return "缺少Content                                       "; }
		else if( errcode.equals("41016") ) { return "缺少标题                                          "; }
		else if( errcode.equals("41017") ) { return "缺少标签ID                                        "; }
		else if( errcode.equals("41018") ) { return "缺少标签名字                                      "; }
		else if( errcode.equals("41021") ) { return "缺少suiteid                                       "; }
		else if( errcode.equals("41022") ) { return "缺少suitetoken                                    "; }
		else if( errcode.equals("41023") ) { return "缺少suiteticket                                   "; }
		else if( errcode.equals("41024") ) { return "缺少suitesecret                                   "; }
		else if( errcode.equals("41025") ) { return "缺少永久授权码                                    "; }
		else if( errcode.equals("42001") ) { return "access_token超时                                  "; }
		else if( errcode.equals("42002") ) { return "refresh_token超时                                 "; }
		else if( errcode.equals("42003") ) { return "oauth_code超时                                    "; }
		else if( errcode.equals("42004") ) { return "插件token超时                                     "; }
		else if( errcode.equals("42007") ) { return "预授权码失效                                      "; }
		else if( errcode.equals("42008") ) { return "临时授权码失效                                    "; }
		else if( errcode.equals("42009") ) { return "suitetoken失效                                    "; }
		else if( errcode.equals("43001") ) { return "需要GET请求                                       "; }
		else if( errcode.equals("43002") ) { return "需要POST请求                                      "; }
		else if( errcode.equals("43003") ) { return "需要HTTPS"; }
		else if( errcode.equals("43004") ) { return "需要接收者关注 "; }
		else if( errcode.equals("43005") ) { return "需要好友关系"; }
		else if( errcode.equals("43006") ) { return "需要订阅  "; }
		else if( errcode.equals("43007") ) { return "需要授权 "; }
		else if( errcode.equals("43008") ) { return "需要支付授权"; }
		else if( errcode.equals("43009") ) { return "需要员工已关注"; }
		else if( errcode.equals("43010") ) { return "需要处于回调模式 "; }
		else if( errcode.equals("43011") ) { return "需要企业授权 "; }
		else if( errcode.equals("44001") ) { return "多媒体文件为空"; }
		else if( errcode.equals("44002") ) { return "POST的数据包为空"; }
		else if( errcode.equals("44003") ) { return "图文消息内容为空"; }
		else if( errcode.equals("44004") ) { return "文本消息内容为空"; }
		else if( errcode.equals("45001") ) { return "多媒体文件大小超过限制"; }
		else if( errcode.equals("45002") ) { return "消息内容超过限制"; }
		else if( errcode.equals("45003") ) { return "标题字段超过限制"; }
		else if( errcode.equals("45004") ) { return "描述字段超过限制"; }
		else if( errcode.equals("45005") ) { return "链接字段超过限制"; }
		else if( errcode.equals("45006") ) { return "图片链接字段超过限制"; }
		else if( errcode.equals("45007") ) { return "语音播放时间超过限制"; }
		else if( errcode.equals("45008") ) { return "图文消息超过限制"; }
		else if( errcode.equals("45009") ) { return "接口调用超过限制"; }
		else if( errcode.equals("45010") ) { return "创建菜单个数超过限制 "; }
		else if( errcode.equals("45015") ) { return "回复时间超过限制"; }
		else if( errcode.equals("45016") ) { return "系统分组，不允许修改"; }
		else if( errcode.equals("45017") ) { return "分组名字过长"; }
		else if( errcode.equals("45018") ) { return "分组数量超过上限"; }
		else if( errcode.equals("45024") ) { return "账号数量超过上限"; }
		else if( errcode.equals("46001") ) { return "不存在媒体数据"; }
		else if( errcode.equals("46002") ) { return "不存在的菜单版本"; }
		else if( errcode.equals("46003") ) { return "不存在的菜单数据"; }
		else if( errcode.equals("46004") ) { return "不存在的员工"; }
		else if( errcode.equals("47001") ) { return "解析JSON/XML内容错误"; }
		else if( errcode.equals("48002") ) { return "Api禁用"; }
		else if( errcode.equals("48003") ) { return "suitetoken无效"; }
		else if( errcode.equals("48004") ) { return "授权关系无效"; }
		else if( errcode.equals("50001") ) { return "redirect_uri未授权"; }
		else if( errcode.equals("50002") ) { return "员工不在权限范围"; }
		else if( errcode.equals("50003") ) { return "应用已停用"; }
		else if( errcode.equals("50004") ) { return "员工状态不正确（未关注状态）"; }
		else if( errcode.equals("50005") ) { return "企业已禁用"; }
		else if( errcode.equals("60001") ) { return "部门长度不符合限制"; }
		else if( errcode.equals("60002") ) { return "部门层级深度超过限制"; }
		else if( errcode.equals("60003") ) { return "部门不存在"; }
		else if( errcode.equals("60004") ) { return "父亲部门不存在"; }
		else if( errcode.equals("60005") ) { return "不允许删除有成员的部门"; }
		else if( errcode.equals("60006") ) { return "不允许删除有子部门的部门"; }
		else if( errcode.equals("60007") ) { return "不允许删除根部门"; }
		else if( errcode.equals("60008") ) { return "部门名称已存在"; }
		else if( errcode.equals("60009") ) { return "部门名称含有非法字符"; }
		else if( errcode.equals("60010") ) { return "部门存在循环关系"; }
		else if( errcode.equals("60011") ) { return "管理员权限不足，（user/department/agent）无权限   "; }
		else if( errcode.equals("60012") ) { return "不允许删除默认应用                                "; }
		else if( errcode.equals("60013") ) { return "不允许关闭应用                                    "; }
		else if( errcode.equals("60014") ) { return "不允许开启应用                                    "; }
		else if( errcode.equals("60015") ) { return "不允许修改默认应用可见范围                        "; }
		else if( errcode.equals("60016") ) { return "不允许删除存在成员的标签                          "; }
		else if( errcode.equals("60017") ) { return "不允许设置企业                                    "; }
		else if( errcode.equals("60019") ) { return "不允许设置应用地理位置上报开关                    "; }
		else if( errcode.equals("60020") ) { return "访问ip不在白名单之中                              "; }
		else if( errcode.equals("60102") ) { return "UserID已存在                                      "; }
		else if( errcode.equals("60103") ) { return "手机号码不合法                                    "; }
		else if( errcode.equals("60104") ) { return "手机号码已存在                                    "; }
		else if( errcode.equals("60105") ) { return "邮箱不合法                                        "; }
		else if( errcode.equals("60106") ) { return "邮箱已存在                                        "; }
		else if( errcode.equals("60107") ) { return "微信号不合法                                      "; }
		else if( errcode.equals("60108") ) { return "微信号已存在                                      "; }
		else if( errcode.equals("60109") ) { return "QQ号已存在                                        "; }
		else if( errcode.equals("60110") ) { return "部门个数超出限制                                  "; }
		else if( errcode.equals("60111") ) { return "UserID不存在                                      "; }
		else if( errcode.equals("60112") ) { return "成员姓名不合法                                    "; }
		else if( errcode.equals("60113") ) { return "身份认证信息（微信号/手机/邮箱）不能同时为空      "; }
		else if( errcode.equals("60114") ) { return "性别不合法                                        "; }
		else { return "未知错误编码:"+errcode; }
	}

	public String processRequest(HttpServletRequest request, String token,String encodingAESKey, String appId, String type) throws Exception 
	{
		// TODO Auto-generated method stub
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
	    requestMap = cn.pudding.weichat.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
	    String encrypt = requestMap.get("Encrypt");
	    requestMap = cn.pudding.weichat.message.MessageUtil.encryptToMap( encrypt, token, encodingAESKey, appId);
	    // 回复不同类型的结构1
	    WcWeiBaseMsgResp respMsg = fenleiReq(requestMap);  
	    //log.error(respMsg.getCreateTime());
	    //log.error(respMsg.getFromUserName());
	    //log.error(respMsg.getMsgType());
	    respMessage = cn.pudding.weichat.message.MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce);
	    //log.error("转完XML:"+respMessage);
	    respMessage = cn.pudding.weichat.message.MessageUtil.getAesXml(respMessage, "",token, encodingAESKey, appId, msg_signature, timestamp, nonce);
	    //log.error("加密XML:"+respMessage);
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
        String fromUserName = requestMap.get("FromUserName")==null?""	:requestMap.get("FromUserName"); 
        log.error("fromUsername:"+fromUserName);
        // 公众帐号  
        String toUserName 	= requestMap.get("ToUserName")==null?""		:requestMap.get("ToUserName");  
        // 消息类型  
        String msgType 		= requestMap.get("MsgType")==null?""		:requestMap.get("MsgType");  
        //发过来的消息
        String content		= requestMap.get("Content")==null?""		:requestMap.get("Content");
        String respContent = "";
        WcWeiBaseMsgResp respMessage = new WcWeiBaseMsgResp();
        respMessage.setToUserName(fromUserName);  
        respMessage.setFromUserName(toUserName);  
        respMessage.setCreateTime(new Date().getTime());  
        respMessage.setFuncFlag(0);
        /**
         * 1、验证用户的合法性
         *  根据用户名验证当前用户是否存在
         * **/
        WcShopAdmin admin = adminService.queryAdminByUsername(fromUserName);
        if(admin==null)
        {
        	//直接返回一个当前用户不存在
        	WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(respMessage);
    		txtMsg.setContent("您的微信号绑定的账户不存在，或者您不是[续费会员]状态!");
    		return txtMsg;
        }
        // 文本消息  
        if(msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) 
        {  
            //在这里将发送过来的消息进行分类处理
//        	if(content.startsWith("会议"))
//        	{
//        		WcWeiTextMsgResp txtMsg = new LzWeiTextMsgResp(respMessage);
//        		txtMsg.setContent("http://meeting.oilchem.net/");
//        		respMessage = txtMsg;
//        	}
//        	else if(content.startsWith("石化通")||content.startsWith("供求")||content.startsWith("供需")||content.startsWith("下载"))
//        	{
//        		//respMessage = com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(contentReq);
//        	}
//        	else
//        	{
        		respMessage = TextMsgUtil.getDefualtTextMsg(respMessage);
//        	}
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
            // 订阅  [关注]
            if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) 
            {  
            	log.info(admin.getWsaUsername()+"关注了微信企业号!");
            	WcAdminWeixin adminWeixin =  adminQiyehaoService.getAdminWeixin(admin.getWsaUsername());
            	if(adminWeixin!=null)
            	{
            		log.info(admin.getWsaUsername()+"修改");
            		adminWeixin.setAweStatus("1");
            		adminWeixin.setAweAdminId(admin.getWsaId());
            		adminQiyehaoService.updAdminWeixin(adminWeixin);
            	}
            	else
            	{
            		log.info(admin.getWsaUsername()+"添加");
            		adminWeixin = new WcAdminWeixin();
                	adminWeixin.setAweUserId(admin.getWsaUsername());
                	adminWeixin.setAweAdminId(admin.getWsaId());
                	adminWeixin.setAweStatus("1");
                	adminWeixin.setAweRegistDate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
                	adminWeixin.setAweRegistor(1);
                	adminQiyehaoService.addAdminWeixin(adminWeixin);
            	}
            	

            }  
            // 取消订阅  [取消关注]
            else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) 
            {  
                // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
            	respMessage.setMsgType(Constant.EVENT_TYPE_UNSUBSCRIBE);
            	//取消订阅后,删掉绑定关系
            	adminQiyehaoService.delAdminWeixin(fromUserName);
            }  
            // 自定义菜单点击事件  
            else if (eventType.toUpperCase().equals(Constant.EVENT_TYPE_CLICK)) 
            {  
                // TODO 自定义菜单处理该类消息
            	
            	respContent = "您点击了按钮";  
            	String eventKey		= requestMap.get("EventKey")==null?"":requestMap.get("EventKey");
            	WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(respMessage);
            	if(eventKey.equals("TONGXUNLU"))
	            {
//  	            	String delFlag = admin.getDelFlag();
//	            	if(delFlag.equals("1"))
//	            	{
//	            		//2、就跳转指定地址
//	                   	String urlTxl = "<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3666a775468948ac&redirect_uri=https%3A%2F%2Fqy.weixin.qq.com%2Fcgi-bin%2Fwap_contact_v4&response_type=code&scope=snsapi_base&state=MTAxMTAwMjY0NA%3D%3D#wechat_redirect\">点击获取通讯录</a>";
//	                   	respContent = urlTxl;
//	                   	txtMsg.setContent(urlTxl);
//	                   	respMessage = txtMsg;
//	                }
//	            	else
//	           		{
//	            		txtMsg.setContent("您还没有转正式员工不能获取内部通讯录");
//	                   	respMessage = txtMsg;
//	            	}
	            }
	            else if(eventKey.equals("FORGETPWD"))
	            {
//	  	           	//2、就跳转指定地址
//	            	String urlTxl = "<a href=\"http://gl.oilchem.net/oilchem/admin/toForgetPwd?tag=1&username="+admin.getUsername()+"\">点击重置密码</a>";
//                   	respContent = urlTxl;
//                   	txtMsg.setContent(urlTxl);
//                   	respMessage = txtMsg;
	            }
	            else
	            {
	            	txtMsg.setContent("未定义的KEY"+eventKey);
	               	respMessage = txtMsg;
	            }
        	} 
            else if (eventType.equals(Constant.EVENT_TYPE_SCAN))
            {
            	
            }
            else if(eventType.equals(Constant.EVENT_TYPE_VIEW))
            {
            	//点击的时候 如果是链接
            	WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(respMessage);
        		txtMsg.setContent("您刚才点击了一条企业号的链接");
        		respMessage = txtMsg;
            }
        }
    	return respMessage;
    }


	
    
    
}

























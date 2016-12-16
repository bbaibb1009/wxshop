package com.wxshop.weichat.qiyehao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.pudding.weichat.http.HttpUtil;

import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.shop.dept.IShopDeptService;
import com.wxshop.shop.dept.WcShopDept;
import com.wxshop.sys.WcShopAdmin;

@Service
@Transactional
public class TxlService implements ITxlService
{
	@Autowired
	private IQiYeHaoService qiYeHaoService;
	
	@Autowired
	private IShopDeptService deptService;
	
	@Autowired
	private IJdbcDao jdbcDao;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	// 测试
	//private String corpid = "wxe6247b0db67090eb";
	//private String corpsecret = "ZYRSEF2lTEQEcsUpqGrF7kUVexxv33yl7nbeFoAS5TJFpIkkRNmeFlmZ2cFGnZ14";
	
	// 生产
	//private String corpid = "wx8ddea39def1dc0ef";
	//private String corpsecret = "v4VIwU3AQQt2_u5zVbDG71eC7j577oqVvEcY7Sy8Lp0u-H-PfLReGXMbgi97O6FX";
	
	private static Logger log = Logger.getLogger(TxlService.class);
	
	@SuppressWarnings("unchecked")
	public void addDept(WcShopDept dept,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", dept.getWdpName());
		if( dept.getWdpLevel().equals("1") )
		{
			map.put("parentid", 1);
		}
		else if( dept.getWdpLevel().equals("2") )
		{
			WcShopDept deptParent = deptService.getShopDeptById(dept.getWdpParentId());
			map.put("parentid", deptParent.getWdpTxlId());
		}
		map.put("order", dept.getWdpOrder());
		String result = qiYeHaoService.addDept(corpid, corpsecret, objectMapper.writeValueAsString(map));
		Map<String, Object> resultMap = objectMapper.readValue(result, Map.class);
		if( resultMap.get("errcode").toString().equals("0") )
		{
			jdbcDao.update("update lz_department set txl_id = ? where dept_id = ?", new Object[]{resultMap.get("id"), dept.getWdpId()});
		}
		else 
		{
			log.error("通讯录同步：创建部门错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
			//throw new NullPointerException("通讯录同步：创建部门错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updDept(WcShopDept dept,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", dept.getWdpTxlId());
		map.put("name", dept.getWdpName());
		if( dept.getWdpLevel().equals("1") )
		{
			map.put("parentid", 1);
		}
		else if( dept.getWdpLevel().equals("2") )
		{
			WcShopDept deptParent = deptService.getShopDeptById(dept.getWdpParentId());
			map.put("parentid", deptParent.getWdpTxlId());
		}
		map.put("order", dept.getWdpOrder());
		String result = qiYeHaoService.updDept(corpid, corpsecret, objectMapper.writeValueAsString(map));
		Map<String, Object> resultMap = objectMapper.readValue(result, Map.class);
		if( ! resultMap.get("errcode").toString().equals("0") )
		{
			log.error("通讯录同步：更新部门错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
			//throw new NullPointerException("通讯录同步：更新部门错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void delDept(List<Map<String, Object>> txlIdList,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException
	{
		String result = null;
		Map<String, Object> resultMap = null;
		for( Map<String, Object> map : txlIdList )
		{
			result = qiYeHaoService.delDept(corpid, corpsecret, (Integer) map.get("txl_id"));
			resultMap = objectMapper.readValue(result, Map.class);
			if( ! resultMap.get("errcode").toString().equals("0") )
			{
				log.error("通讯录同步：删除部门错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
				//throw new NullPointerException("通讯录同步：删除部门错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void delDeptAll(String corpid, String corpsecret) throws JsonParseException, JsonMappingException,	IOException 
	{
		// TODO Auto-generated method stub
		List<Map<String,Object>> txlIdList = this.queryDeptListRemote( corpid,  corpsecret);
		this.delDept(txlIdList, corpid,  corpsecret);
	}

	/**
	 * 
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public void addUser(WcShopAdmin admin,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", admin.getWsaUsername());
		map.put("name", admin.getWsaName());
		WcShopDept dept = deptService.getShopDeptById(admin.getWsaDept());
		map.put("department", dept.getWdpTxlId());
		
		map.put("mobile", admin.getWsaMobile());
		
		String result = qiYeHaoService.addUser(corpid, corpsecret, objectMapper.writeValueAsString(map));
		Map<String, Object> resultMap = objectMapper.readValue(result, Map.class);
		if( ! resultMap.get("errcode").toString().equals("0") )
		{
			log.error("通讯录同步：创建成员错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
			//throw new NullPointerException("通讯录同步：创建成员错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
		}
	}
	
	/**
	 * 功能介绍:根据本地用户的信息更新微信企业号的信息
	 * @author yChoco
	 * @create_time 2014
	 * @upd_history [151008][yChoco][更新单个通讯录的时候增加“座机”字段]
	 ***/
	@SuppressWarnings("unchecked")
	public void updUser(WcShopAdmin admin,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
		// 根据userid获取成员，判断成员是否存在，当成员不存在时（已离职），如果当前操作为离职操作，则不再调用删除成员接口（离职->离职）；如果当前操作为非离职操作，则调用创建成员接口（离职->在职）
		String user = qiYeHaoService.queryUser(corpid, corpsecret, admin.getWsaUsername());
		Map<String, Object> userMap = objectMapper.readValue(user, Map.class);
		if( userMap.get("errcode").toString().equals("0") )
		{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userid", admin.getWsaUsername());
				map.put("name", admin.getWsaName());
				WcShopDept dept = deptService.getShopDeptById(admin.getWsaDept());
				map.put("department", dept.getWdpTxlId());
				map.put("mobile", admin.getWsaMobile());
				String result = qiYeHaoService.updUser(corpid, corpsecret, objectMapper.writeValueAsString(map));
				Map<String, Object> resultMap = objectMapper.readValue(result, Map.class);
				if( ! resultMap.get("errcode").toString().equals("0") )
				{
					log.error("通讯录同步：更新成员错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
					//throw new NullPointerException("通讯录同步：更新成员错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
				}
			}
			else 
			{
				addUser(admin, corpid,  corpsecret);
			}
	}
	
	@SuppressWarnings("unchecked")
	public void delUser(String userid,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException
	{
		String result = qiYeHaoService.delUser(corpid, corpsecret, userid);
		Map<String, Object> resultMap = objectMapper.readValue(result, Map.class);
		if( ! resultMap.get("errcode").toString().equals("0") )
		{
			log.error("通讯录同步：删除成员错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
			//throw new NullPointerException("通讯录同步：删除成员错误，" + qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void delUsers(List<Map<String, Object>> usernameList,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException
	{
		for( Map<String, Object> map : usernameList )
		{
			delUser((String) map.get("username"), corpid,  corpsecret);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void delUsersAll(String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException 
	{
		//1、查出所有的用户
		List<Map<String,Object>> listMap = qiYeHaoService.queryUserAll(corpid, corpsecret);
		this.delUsers(listMap, corpid,  corpsecret);
		
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> sendMessage(Map<String, Object> map,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException
	{
		Map<String, Object> vmap = new HashMap<String, Object>();
		vmap.put("touser", map.get("noti_to"));
		vmap.put("msgtype", "text");
		vmap.put("agentid", "0");
		Map<String, Object> textMap = new HashMap<String, Object>();
		textMap.put("content", map.get("noti_text"));
		vmap.put("text", textMap);
		String result = qiYeHaoService.sendMessage(corpid, corpsecret, objectMapper.writeValueAsString(vmap));
		Map<String, Object> resultMap = objectMapper.readValue(result, Map.class);
		if( ! resultMap.get("errcode").toString().equals("0") )
		{
			resultMap.put("errmsg", qiYeHaoService.getErrmsg(resultMap.get("errcode").toString()));
		}
		return resultMap;
	}
	
	public Map<String,Object> queryUser(WcShopAdmin admin,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException
	{
		String user = qiYeHaoService.queryUser(corpid, corpsecret, admin.getWsaUsername());
		Map<String, Object> userMap = objectMapper.readValue(user, Map.class);
		if(userMap!=null)
		{
			Integer errorCode = (Integer)userMap.get("errcode");
			if(errorCode!=0)
			{
				userMap = null;
			}
		}
		return userMap;
	}
	
	
	public  static void main(String[] args)
	{

		String corpid = "wx8ddea39def1dc0ef";
		String corpsecret = "v4VIwU3AQQt2_u5zVbDG71eC7j577oqVvEcY7Sy8Lp0u-H-PfLReGXMbgi97O6FX";
		String url1 = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
		JSONObject jsonObj1 = HttpUtil.httpRequestJson(url1, "GET", null);
		String result1 = jsonObj1.toString();
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> tokenMap;
		try 
		{
			tokenMap = objectMapper.readValue(result1, Map.class);
			String token = (String) tokenMap.get("access_token");
			String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + token + "&userid=lzdingj" ;
			JSONObject jsonObj = HttpUtil.httpRequestJson(url, "GET", null);
			String result = jsonObj.toString();
			StringBuilder builder = new StringBuilder("\n获取成员\n").append(url).append("\n结果：").append(result);
			System.out.println(builder.toString());
		} 
		catch (JsonParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (JsonMappingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryDeptListRemote(String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		List<Map<String,Object>> listMap = qiYeHaoService.queryDeptListAll(corpid, corpsecret);
		return listMap;
	}
	
	
}


























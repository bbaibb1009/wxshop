package com.wxshop.weichat.qiyehao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public interface IQiYeHaoService 
{
	public String getTokenJson(String corpid, String corpsecret);
	
	public String getToken(String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException;
	
	public String addDept(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException;
	
	public String updDept(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException;
	
	public String delDept(String corpid, String corpsecret, Integer id) throws JsonParseException, JsonMappingException, IOException;
	
	public String queryDept(String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException;
	
	public String addUser(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException;
	
	public String updUser(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException;
	
	public String delUser(String corpid, String corpsecret, String userid) throws JsonParseException, JsonMappingException, IOException;
	
	public String queryUser(String corpid, String corpsecret, String userid) throws JsonParseException, JsonMappingException, IOException;
	
	public String queryDeptUser(String corpid, String corpsecret, Integer departmentId, String fetchChild, String status) 
		throws JsonParseException, JsonMappingException, IOException;
	
	public String sendMessage(String corpid, String corpsecret, String json) throws JsonParseException, JsonMappingException, IOException;
	
	public String getErrmsg(String errcode);
	
	/**2015-09-30 回调函数处理*/
	public String processRequest(HttpServletRequest request,String token,String encodingAESKey,String appId,String type) throws Exception ;
	
	public List<Map<String, Object>> queryDeptListAll(String corpid,String corpsecret) throws JsonParseException, JsonMappingException, IOException ;
	
	public List<Map<String, Object>> queryUserAll(String corpid,String corpsecret) throws JsonParseException, JsonMappingException, IOException ;

	
}

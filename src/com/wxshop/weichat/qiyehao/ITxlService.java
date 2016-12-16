package com.wxshop.weichat.qiyehao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.wxshop.shop.dept.WcShopDept;
import com.wxshop.sys.WcShopAdmin;

public interface ITxlService 
{
	public void addDept(WcShopDept dept,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException;
	
	public void updDept(WcShopDept dept,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException;
	
	public void delDept(List<Map<String, Object>> txlIdList,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException;
	
	public void delDeptAll(String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException;
	
	public void addUser(WcShopAdmin admin,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException;
	
	public void updUser(WcShopAdmin admin,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException;
	
	public void delUser(String userid,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException;
	
	public void delUsers(List<Map<String, Object>> usernameList,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException;
	
	public void delUsersAll(String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException;
	
	public Map<String, Object> sendMessage(Map<String, Object> map,String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException;
	
	public Map<String, Object> queryUser(WcShopAdmin admin ,String corpid, String corpsecret)throws JsonParseException, JsonMappingException, IOException;

	public List<Map<String, Object>> queryDeptListRemote(String corpid, String corpsecret) throws JsonParseException, JsonMappingException, IOException;
	
}

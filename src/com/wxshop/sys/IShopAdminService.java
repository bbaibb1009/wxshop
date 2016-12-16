package com.wxshop.sys;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.wxshop.util.Page;



public interface IShopAdminService {

	public boolean chkShopUsernameUnique(Integer adminId, String username);
	public boolean chkShopUsernameUnique(String username);
	public List<Map<String, Object>> queryShopAdminMenus(Integer adminId, String menuLevel);
	public void updShopLoginTime(Integer adminId);
	public WcShopAdmin getShopAdminById(Integer id);
	public WcShopAdmin adminLogin(WcShopAdmin admin);
	public List<Map<String, Object>> queryShopAdminNameToCache();
	public boolean chkUsernameRandomStr(WcShopAdmin admin);
	public Page queryShopAdmin(WcShopAdmin admin);
	public void updShopAdmin(WcShopAdmin admin)  throws JsonParseException, JsonMappingException, JsonGenerationException, IOException;
	
	public void addShopAdmin(WcShopAdmin admin);
	
	/**
	 * 功能介绍:根据用户名获取认账系统中的账号,离职的、删除的，休假的，未审核的除外
	 * 
	 * @author yChoco
	 * @upd_history [20151109][yChoco][将原来的离职 删除 休假 未审核都排除掉]
	 * */
	public WcShopAdmin queryAdminByUsername(String username);
}

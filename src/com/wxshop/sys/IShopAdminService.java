package com.wxshop.sys;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;



public interface IShopAdminService {

	public boolean chkShopUsernameUnique(Integer adminId, String username);
	public boolean chkShopUsernameUnique(String username);
	public List<Map<String, Object>> queryShopAdminMenus(Integer adminId, String menuLevel);
	public void updShopLoginTime(Integer adminId);
	public WcShopAdmin getShopAdminById(Integer id);
	public WcShopAdmin adminLogin(WcShopAdmin admin);
	public List<Map<String, Object>> queryShopAdminNameToCache();
	public boolean chkUsernameRandomStr(WcShopAdmin admin);
	
	
}

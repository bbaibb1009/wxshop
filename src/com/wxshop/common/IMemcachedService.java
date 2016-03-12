package com.wxshop.common;

import java.util.List;
import java.util.Map;



public interface IMemcachedService 
{
	public void init();
//
//	public void setAdminNameAll();
//	
//	public void setAdminNameAll(List<Map<String, Object>> adminNameList);
	
	public List<Map<String, Object>> getAdminNameAll();
	
	public List<Map<String,Object>> getMenuAll();
	
	public void setShopMenuAll();

	
}

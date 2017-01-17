package com.wxshop.common;

import java.util.List;
import java.util.Map;



public interface IMemcachedService 
{
	public void init();

	public void setShopAdminNameAll();

    public void setShopAdminNameAll(List<Map<String, Object>> adminNameList);
	
	public List<Map<String, Object>> getAdminNameAll();
	
	public List<Map<String,Object>> getMenuAll();
	
	public void setShopMenuAll();
	
	public List<Map<String,Object>> getDeptAll();
	
	public void setDeptAll();

    public void setDeptAll(List<Map<String, Object>> deptList);

    public void setDeptName1All();

	public void setDeptName1All(List<Map<String, Object>> deptName1List);

	public List<Map<String, Object>> getDeptName1All();
	
	public List<Map<String,Object>> getSingleDeptAll();
	
	public String setSceneIdBySession(String sessionID);
	
	
	
}

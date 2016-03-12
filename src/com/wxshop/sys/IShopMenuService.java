package com.wxshop.sys;

import java.util.List;
import java.util.Map;

import com.wxshop.util.Page;



public interface IShopMenuService {


	public WcShopMenu getShopMenuById(Integer menuId);

	public void updShopMenu(WcShopMenu menu);
	
	public List<Map<String, Object>> queryShopMenuToCache();
	
	public Page queryShopMenu(WcShopMenu menu);
	
	public void addMenu(WcShopMenu menu);
	
	public void delMenu(String[] wsmIds);
	
	
	
}

package com.wxshop.sys;

import java.util.List;
import java.util.Map;



public interface IShopMenuService {


	public WcShopMenu getShopMenuById(Integer menuId);

	public void updShopMenu(WcShopMenu menu);
	
	public List<Map<String, Object>> queryShopMenuToCache();
	
}

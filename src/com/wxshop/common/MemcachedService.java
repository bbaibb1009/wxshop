package com.wxshop.common;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
import com.wxshop.sys.IShopAdminService;
import com.wxshop.sys.IShopMenuService;
import com.wxshop.util.SysConstant;

@Service
public class MemcachedService implements IMemcachedService
{
	private static Logger log = Logger.getLogger(MemcachedService.class);
	
	// 缓存时间10天
	private static final int STORE_TIME = 10 * 24 * 60 * 60 * 1000;
	
	@Autowired
	private MemCachedClient memCachedClient;
	
	@Autowired
	private IShopMenuService menuService;
	
	@Autowired
	private IShopAdminService adminService;

	public void init()
	{
//		// 清空缓存
//		if( ! memCachedClient.flushAll() )
//		{
//			log.error("memcached清空失败");
//		}
//		setMenuAll();
//		log.info("微信商家管理-系统菜单存入memcached成功");
//		setAdminNameAll();
//		log.info("微信商家管理员姓名存入memcached成功");
	}

		
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMenuAll()
	{
		List<Map<String, Object>> menuList = 
			(List<Map<String, Object>>) memCachedClient.get(SysConstant.MENU_ALL);
		if( menuList == null )
		{
			menuList = menuService.queryShopMenuToCache();
			//setMenuAll(menuList);
		}
		
		return menuList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAdminNameAll()
	{
		List<Map<String, Object>> adminNameList = 
			(List<Map<String, Object>>) memCachedClient.get(SysConstant.ADMIN_NAME_ALL);
		if( adminNameList == null )
		{
			adminNameList = adminService.queryShopAdminNameToCache();
			//setAdminNameAll(adminNameList);
		}
		
		return adminNameList;
	}

}
















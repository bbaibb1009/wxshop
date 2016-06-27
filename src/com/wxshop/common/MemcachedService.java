package com.wxshop.common;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
import com.wxshop.shop.dept.IShopDeptService;
import com.wxshop.sys.IShopAdminService;
import com.wxshop.sys.IShopMenuService;
import com.wxshop.util.SysConstant;

@Service
public class MemcachedService implements IMemcachedService
{
	private static Logger log = Logger.getLogger(MemcachedService.class);
	
	// ����ʱ��10��
	private static final int STORE_TIME = 10 * 24 * 60 * 60 * 1000;
	
	@Autowired
	private MemCachedClient memCachedClient;
	
	@Autowired
	private IShopMenuService menuService;
	
	@Autowired
	private IShopAdminService adminService;
	
	@Autowired
	private IShopDeptService deptService;

	public void init()
	{
		// ��ջ���
		if( ! memCachedClient.flushAll() )
		{
			log.error("memcached���ʧ��");
		}
		setShopMenuAll();
		log.info("΢���̼ҹ���-ϵͳ�˵�����memcached�ɹ�");
		setShopAdminNameAll();
		log.info("΢���̼ҹ���Ա��������memcached�ɹ�");
		setDeptAll() ;
		log.info("���Ŵ���memcached�ɹ�");
		setSingleDeptAll();
		log.info("�����Ŵ���memcached�ɹ�");
		
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
			setShopAdminNameAll(adminNameList);
		}
		
		return adminNameList;
	}


	public void setShopMenuAll() {
		// TODO Auto-generated method stub
		setShopMenuAll(menuService.queryShopMenuToCache());
	}

	public void setShopMenuAll(List<Map<String, Object>> menuList) {
		if (!memCachedClient.set(SysConstant.MENU_ALL, menuList, new Date(
				System.currentTimeMillis() + STORE_TIME))) {
			log.error("ϵͳ�˵�����memcachedʧ��");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getShopMenuAll() {
		List<Map<String, Object>> menuList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.MENU_ALL);
		if (menuList == null) {
			menuList = menuService.queryShopMenuToCache();
			setShopMenuAll(menuList);
		}

		return menuList;
	}


	public void setShopAdminNameAll() {
		// TODO Auto-generated method stub
		setShopAdminNameAll(adminService.queryShopAdminNameToCache());
	}

	public void setShopAdminNameAll(List<Map<String, Object>> adminNameList) {
		// TODO Auto-generated method stub
		if (!memCachedClient.set(SysConstant.ADMIN_NAME_ALL, adminNameList,
				new Date(System.currentTimeMillis() + STORE_TIME))) {
			log.error("����Ա��������memcachedʧ��");
		}
	}


	public List<Map<String, Object>> getDeptAll() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> deptList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.DEPT_ALL);
		if (deptList == null) {
			deptList = deptService.queryDeptToCache();
			setDeptAll(deptList);
		}
		return deptList;
	}


	public void setDeptAll() {
		// TODO Auto-generated method stub
		setDeptAll(deptService.queryDeptToCache());
	}


	public void setDeptAll(List<Map<String, Object>> deptList) {
		// TODO Auto-generated method stub
		if (!memCachedClient.set(SysConstant.DEPT_ALL, deptList,new Date(System.currentTimeMillis() + STORE_TIME))) 
		{
			log.error("���Ŵ���memcachedʧ��");
		}
	}
	
	public void setDeptName1All() {
		setDeptName1All(deptService.queryDeptName1ToCache());
	}

	public void setDeptName1All(List<Map<String, Object>> deptName1List) {
		if (!memCachedClient.set(SysConstant.DEPT_NAME1_ALL, deptName1List,	new Date(System.currentTimeMillis() + STORE_TIME))) 
		{
			log.error("һ���������ƴ���memcachedʧ��");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDeptName1All() {
		List<Map<String, Object>> deptName1List = (List<Map<String, Object>>) memCachedClient
				.get(SysConstant.DEPT_NAME1_ALL);
		if (deptName1List == null) {
			deptName1List = deptService.queryDeptName1ToCache();
			setDeptName1All(deptName1List);
		}

		return deptName1List;
	}


	public void setSingleDeptAll() {
		setSingleDeptAll(deptService.querySingleDeptToCache());
	}

	public void setSingleDeptAll(List<Map<String, Object>> singleDeptList) {
		if (!memCachedClient.set(SysConstant.SINGLE_DEPT_ALL, singleDeptList,new Date(System.currentTimeMillis() + STORE_TIME))) {
			log.error("����������memcachedʧ��");
		}
	}
	
	public List<Map<String, Object>> getSingleDeptAll() 
	{
		List<Map<String, Object>> singleDeptList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.SINGLE_DEPT_ALL);
		if (singleDeptList == null) {
			singleDeptList = deptService.querySingleDeptToCache();
			setSingleDeptAll(singleDeptList);
		}
		return singleDeptList;
	}
	
}
















package com.wxshop.sys;

import java.util.List;


public interface IShopRoleService {


	
	public void addShopRole(WcShopRole role);
	
	public WcShopRole getShopRoleById(Integer roleId);
	
	public void delShopRole(String[] roleIds);
	
	public void updShopRole(WcShopRole role);
	 
	public List<String> queryShopRoleMenusById(Integer roleId);
	
	public List<WcShopRole> queryShopRoleForAdminUpd1(Integer adminId);
	
	public List<WcShopRole> queryShopRoleForAdminUpd0(Integer adminId);
}

package com.wxshop.sys;

import java.util.List;

import com.wxshop.util.Page;


public interface IShopRoleService {


	
	public void addShopRole(WcShopRole role);
	
	public WcShopRole getShopRoleById(Integer roleId);
	
	public void delShopRole(String[] roleIds);
	
	public void updShopRole(WcShopRole role);
	 
	public List<String> queryShopRoleMenusById(Integer roleId);
	
	public List<String>	queryShopRoleAdminsById(Integer roleId);
	
	public List<WcShopRole> queryShopRoleForAdminUpd1(Integer adminId);
						    
	public List<WcShopRole> queryShopRoleForAdminUpd0(Integer adminId);
	
	public List<WcShopRole> queryShopRoleForAdminUpd2(Integer adminId);
	
	public List<String> queryShopRoleMenusForAdmin(String roleIds);
	
	public List<String> queryShopRoleMenusForAdmin(String roleIds, Integer adminId);
	
	public Page queryShopRole(WcShopRole role);
	
	
	public List<WcShopRole> queryShopRoleForAdminAdd();
	
}

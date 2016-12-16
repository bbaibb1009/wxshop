package com.wxshop.weichat.qiyehao;

import com.wxshop.util.Page;

public interface IShopAdminQiyehaoService {
public void addAdminWeixin(WcAdminWeixin adminWeixin);
	
	public void delAdminWeixin(String username);
	
	/***
	 * 功能介绍:查询企业号的管理员绑定情况
	 * */
	public Page queryAdminWeixin(WcAdminWeixin adminWeixin);
	
	public WcAdminWeixin getAdminWeixin(String username);
	
	public boolean isQyhBinded(String username);
	
	public void updAdminWeixin(WcAdminWeixin adminWeixin);
}

package com.wxshop.weichat.qiyehao;

import com.wxshop.util.Page;

public interface IShopAdminQiyehaoService {
public void addAdminWeixin(WcAdminWeixin adminWeixin);
	
	public void delAdminWeixin(String username);
	
	/***
	 * ���ܽ���:��ѯ��ҵ�ŵĹ���Ա�����
	 * */
	public Page queryAdminWeixin(WcAdminWeixin adminWeixin);
	
	public WcAdminWeixin getAdminWeixin(String username);
	
	public boolean isQyhBinded(String username);
	
	public void updAdminWeixin(WcAdminWeixin adminWeixin);
}

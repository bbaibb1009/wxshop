package com.wxshop.weichat.menu;

import com.wxshop.util.Page;

public interface IWeixinMenuService {
	
	public Page queryMenu(WcWeiMenu menu);
	public int addMenu(WcWeiMenu menu,String accessToken);
	public WcWeiMenu getMenuById(Integer wmuId);
	public WcWeiMenu getMenuByAppId(String appId);

}

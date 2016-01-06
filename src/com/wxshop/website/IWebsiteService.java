package com.wxshop.website;

import java.util.List;

import com.wxshop.util.Page;

public interface IWebsiteService {

	public Page queryWebsite(WcWebsite webSite);
	public void addWebsite(WcWebsite webSite);
	public WcWebsite getWebSiteById(Integer id);
	public void updWebSite(WcWebsite webSite);
	public WcWebsite queryMySiteByAdmin(Integer wadId);
	public List<WcWebsite> getWebSiteByAdminId(Integer wadId);
}

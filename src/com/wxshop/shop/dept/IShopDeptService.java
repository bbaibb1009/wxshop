package com.wxshop.shop.dept;

import java.util.List;
import java.util.Map;

import com.wxshop.util.Page;

public interface IShopDeptService {

	public Page queryShopDept(WcShopDept dept);
	public void addDept(WcShopDept dept);
	public void delDept(String [] wdpIds);
	public WcShopDept getShopDeptById(Integer id);
	public void updShopDept(WcShopDept dept_Q);
	public List<Map<String,Object>> queryDeptToCache();
	public List<Map<String,Object>> queryDeptName1ToCache();
	public List<Map<String,Object>> querySingleDeptToCache();
}

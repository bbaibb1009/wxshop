package com.wxshop.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wxshop.common.IMemcachedService;
import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.util.Page;

@Service
@Transactional
public class ShopMenuService implements IShopMenuService {
	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	@Autowired 
	private IMemcachedService memcachedservice;

		
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryShopMenuToCache()
	{
		String sql = 
			  " select " +
			  " WSM_ID id,WSM_NAME name,WSM_LEVEL menuLevel,WSM_PARENT_ID pId, "
			+ " case when WSM_LEVEL = '1' then 'true' "
			+ "      when WSM_LEVEL = '2' then 'true' "
			+ "      else 'false' end isParent,        "
			+ " case when WSM_LEVEL = '1' then 'true' "
			+ "      else 'false' end 'open'           "
			+ " from WC_SHOP_MENU "
			+ " order by WSM_LEVEL asc, WSM_ORDER asc ";
		return jdbcDao.queryForList(sql);
	}

	
	public void delShopMenu(String[] menuIds) {
		// TODO Auto-generated method stub
		String menuIdsStr = StringUtils.arrayToCommaDelimitedString(menuIds);
		jdbcDao.delete("delete from WC_SHOP_MENU where WSM_ID in (" + menuIdsStr + ")");
		jdbcDao.delete("delete from WC_SHOP_ROLE_MENU where WSRM_MENU_ID in (" + menuIdsStr + ")");
		jdbcDao.delete("delete from WC_SHOP_ADMIN_MENU where WSAM_MENU_ID in (" + menuIdsStr + ")");
	}

	public WcShopMenu getShopMenuById(Integer menuId) {
		// TODO Auto-generated method stub
		return hibernateDao.get(WcShopMenu.class, menuId);
	}
	
	public void updShopMenu(WcShopMenu menu)
	{
		hibernateDao.update(menu);
		hibernateDao.flush();
	}


	public Page queryShopMenu(WcShopMenu menu) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(	
			" SELECT" +
			" a.WSM_ID, a.WSM_NAME, a.WSM_URL," +
			" a.WSM_LEVEL, a.WSM_ORDER, " +
			" a.WSM_DESC,  a.WSM_PARENT_ID, " +
			" a.WSM_REGISTOR, DATE_FORMAT(a.WSM_REGIST_DATE ,'%Y-%c-%d') AS WSM_REGIST_DATE, " +
			" c.WSA_NAME, " +
			" b.WSM_NAME as parent_menu_name ");
		StringBuilder sqlCnt = new StringBuilder(
			" select count(*) ");
		StringBuilder sqlConf = new StringBuilder(
			" from WC_SHOP_MENU a " +
			" left join WC_SHOP_MENU b on a.WSM_PARENT_ID = b.WSM_ID " +
			" left join WC_SHOP_ADMIN c on c.WSA_ID = a.WSM_REGISTOR "
		);
		List<Object> paraList = new ArrayList<Object>();
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		sql.append(" order by a.WSM_ID asc ");
		Page page = new Page(sql.toString(),sqlCnt.toString(),menu.getCurrentPage(),menu.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}


	public void addMenu(WcShopMenu menu) {
		// TODO Auto-generated method stub
		hibernateDao.add(menu);
		hibernateDao.flush();
		
		// 更新缓存
		memcachedservice.setShopMenuAll();
	}


	public void delMenu(String[] wsmIds) {
		// TODO Auto-generated method stub
		String wsmIdStr = StringUtils.arrayToCommaDelimitedString(wsmIds);
		jdbcDao.delete("delete from WC_SHOP_MENU  where WSM_ID in ("+ wsmIdStr +")");
		jdbcDao.delete("delete from WC_SHOP_ROLE_MENU where WSRM_MENU_ID in (" + wsmIdStr + ")");
		jdbcDao.delete("delete from WC_SHOP_ADMIN_MENU where WSAM_MENU_ID in (" + wsmIdStr + ")");
		
		// 更新缓存
		memcachedservice.setShopMenuAll();
		
	}
	
	
	

	
	
	
}

package com.wxshop.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;

@Service
@Transactional
public class ShopMenuService implements IShopMenuService {
	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;

		
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


	
	
	
}

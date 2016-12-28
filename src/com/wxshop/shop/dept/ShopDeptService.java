package com.wxshop.shop.dept;

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
public class ShopDeptService implements IShopDeptService{

	@Autowired
	private IMemcachedService memcachedservice;

	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	public void addDept(WcShopDept dept) {
		// TODO Auto-generated method stub
		hibernateDao.add(dept);
		hibernateDao.flush();
		memcachedservice.setDeptAll() ;
	}

	public void delDept(String[] wdpIds) {
		// TODO Auto-generated method stub
		String wdpIdStr = StringUtils.arrayToCommaDelimitedString(wdpIds);
		String sql = "delete from WC_SHOP_DEPT where WDP_ID in ("+wdpIdStr+")";
		jdbcDao.delete(sql);
		

		memcachedservice.setDeptAll() ;
	}

	public WcShopDept getShopDeptById(Integer id) {
		// TODO Auto-generated method stub
		return hibernateDao.get(WcShopDept.class, id);
	}

	public Page queryShopDept(WcShopDept dept) {
		// TODO Auto-generated method stub
		StringBuilder sql 		= new StringBuilder(
			" select " +
			" a.WDP_ID, a.WDP_NAME, a.WDP_LEVEL, a.WDP_ORDER, a.WDP_PARENT_ID," +
			" case when a.WDP_LEVEL = '1' then a.WDP_ORDER when a.WDP_LEVEL = '2' then b.WDP_ORDER else 0 end temp_order, " +
			" a.WDP_ADMIN_ID,a.WDP_TXL_ID ,a.WDP_STATUS, b.WDP_NAME PARENT_NAME,a.WDP_DESC, a.WDP_REGISTOR," +
			" date_format(a.WDP_REGISTDATE,'%Y-%m-%d') as WDP_REGISTDATE, " +
			" c.WSA_NAME as ADMIN_NAME,d.WSA_NAME as WDP_REGISTOR_NAME ");
		StringBuilder sqlCnt 	= new StringBuilder(" select count(*) ");
		StringBuilder sqlConf 	= new StringBuilder(
			" from WC_SHOP_DEPT a " +
			" left join WC_SHOP_DEPT b on a.WDP_PARENT_ID = b.WDP_ID " +
			" left join WC_SHOP_ADMIN c on a.WDP_ADMIN_ID = c.WSA_ID " +
			" left join WC_SHOP_ADMIN d on a.WDP_REGISTOR = d.WSA_ID " +
			" where 1=1  "
		);
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		sql.append(" order by temp_order asc, a.WDP_LEVEL asc, a.WDP_ORDER asc ");
		List<Object> paraList = new ArrayList<Object>();
		Page page = new Page(sql.toString(),sqlCnt.toString(),dept.getCurrentPage(),dept.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}

	public void updShopDept(WcShopDept deptQ) {
		// TODO Auto-generated method stub
		hibernateDao.update(deptQ);
		hibernateDao.flush();
		memcachedservice.setDeptAll() ;
	}

	public List<Map<String, Object>> queryDeptToCache() {
		// TODO Auto-generated method stub
		String sql = new String(
			  " select WDP_ID id, WDP_NAME name, WDP_LEVEL deptLevel, 		"  
			+ " WDP_ORDER deptOrder, WDP_PARENT_ID pId, 'true' isParent,    "
			+ " case when WDP_LEVEL = 1 then 'true'  else 'false' end 'open'"
			+ " from WC_SHOP_DEPT                                       	"
			+ " union all                                                	"
			+ " select -1 * WSA_ID id, WSA_NAME name, '3' deptLevel,        "
			+ " WSA_ID deptOrder, WSA_DEPT pId,                         	"
			+ " 'false' isParent, 'false' 'open'                         	"
			+ " from WC_SHOP_ADMIN                                          "
			+ " where  WSA_DEPT > 0  										" 
			+ " order by deptLevel asc, deptOrder asc                    	"	
		);
		
		

		return jdbcDao.queryForList(sql);
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryDeptName1ToCache()
	{
		String sql = 
			" select WDP_ID data, WDP_NAME value " 
			+ " from WC_SHOP_DEPT "
			+ " where WDP_LEVEL = '1' "
			+ " order by WDP_NAME asc ";
		
		return jdbcDao.queryForList(sql);
	}

	public List<Map<String, Object>> querySingleDeptToCache() {
		// TODO Auto-generated method stub
		String sql = 
			  " select WDP_ID id, WDP_NAME name,WDP_LEVEL deptLevel, WDP_ORDER deptOrder," 
			+ " WDP_PARENT_ID pId, 'true' isParent,'true' open " 
			+ " from WC_SHOP_DEPT "
			+ " order by deptLevel asc, deptOrder asc";
		return jdbcDao.queryForList(sql);

	}
	
	
}

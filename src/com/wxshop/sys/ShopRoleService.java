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
import com.wxshop.util.Page;
import com.wxshop.util.StringUtil;


@Service
@Transactional
public class ShopRoleService implements IShopRoleService {

	@Autowired
	private IJdbcDao jdbcDao;

	@Autowired
	private IHibernateDao hibernateDao;


	@Transactional(readOnly = true)
	public List<WcShopRole> queryShopRoleForAdminAdd()
	{
		return hibernateDao.query("from WcShopRole order by wsrRoleId desc");
	}


	public void addShopRole(WcShopRole role) {
		// TODO Auto-generated method stub
		hibernateDao.add(role);
		addShopRoleOther(role);
		
	}

	private void addShopRoleOther(WcShopRole role)
	{
		String roleId = String.valueOf(role.getWsrRoleId());
		String[] menuIds = role.getMenuIds().split(",");
		jdbcDao.batchUpdate("insert into WC_SHOP_ROLE_MENU (WSRM_ROLE_ID, WSRM_MENU_ID) values (?, ?)", StringUtil.getObjAryList(roleId, menuIds)); 
		if( role.getAdminIds().length() > 0 )
		{
			String[] adminIds = role.getAdminIds().split(",");
			jdbcDao.batchUpdate("insert into WC_SHOP_ADMIN_ROLE (WSAR_ADMIN_ID, WSAR_ROLE_ID) values (?, ?)", StringUtil.getObjAryList(adminIds, roleId)); 
		}
	}
	
	
	
	public void delShopRole(String[] roleIds) {
		// TODO Auto-generated method stub
		String roleIdsStr = StringUtils.arrayToCommaDelimitedString(roleIds);
		jdbcDao.delete("delete from WC_SHOP_ROLE where WSR_ROLE_ID in (" + roleIdsStr + ")");
		jdbcDao.delete("delete from WC_SHOP_ROLE_MENU where WSRM_ROLE_ID in (" + roleIdsStr + ")");
		jdbcDao.delete("delete from WC_SHOP_ADMIN_ROLE where WSRM_ROLE_ID in (" + roleIdsStr + ")");
	}


	public WcShopRole getShopRoleById(Integer roleId) {
		// TODO Auto-generated method stub
		return hibernateDao.get(WcShopRole.class, roleId);
	}





	public void updShopRole(WcShopRole role) {
		// TODO Auto-generated method stub
		hibernateDao.update(role);
		Object[] obj = new Object[]{role.getWsrRoleId()};
		jdbcDao.delete("delete from WC_SHOP_ROLE_MENU where WSRM_ROLE_ID = ?", obj);
		// 没有设置部门的管理员不能删除，因为在部门树中没有这部分管理员
//		jdbcDao.delete(" delete a from WC_ADMIN_ROLE a where a.WAR_ROLE_ID = ? ",obj);
//		
//		jdbcDao.delete(
//			  " delete a from lz_admin_role a where a.role_id = ? "
//			+ " and exists ( "
//			+ " 	select 1 from lz_admin b "
//			+ " 	where a.admin_id = b.admin_id "
//			+ "		and b.dept_id > 0 "
//			+ " ) "
//			, obj);
		addShopRoleOther(role);
	}

	@Transactional(readOnly = true)
	public List<String> queryShopRoleMenusById(Integer roleId)
	{
		List<Map<String, Object>> menuList =
			jdbcDao.queryForList("select WSRM_ROLE_ID, WSRM_MENU_ID from WC_SHOP_ROLE_MENU where WSRM_ROLE_ID = ?", 
					new Object[]{roleId});
		return chgList(menuList, "WSRM_MENU_ID");
	}
	
	
	private List<String> chgList(List<Map<String, Object>> list, String colName)
	{
		List<String> resultList = new ArrayList<String>();
		for( Map<String, Object> map : list )
		{
			resultList.add(map.get(colName).toString());
		}
		return resultList;
	}
	
	@Transactional(readOnly = true)
	public List<WcShopRole> queryShopRoleForAdminUpd1(Integer adminId)
	{
		String sql =
			" select b.WSR_ROLE_ID, b.WSR_ROLE_NAME "
			+ " from WC_SHOP_ADMIN_ROLE a "
			+ " join WC_SHOP_ROLE b on a.WSAR_ROLE_ID = b.WSR_ROLE_ID "
			+ " where a.WSAR_ADMIN_ID = ? "
			+ " order by b.WSR_ROLE_ID desc ";
		List<Map<String, Object>> list = jdbcDao.queryForList(sql, new Object[]{adminId});
		return chgShopRoleList(list);
	}
	
	@Transactional(readOnly = true)
	public List<WcShopRole> queryShopRoleForAdminUpd0(Integer adminId)
	{
		String sql =
			  " select a.WSR_ROLE_ID, a.WSR_ROLE_NAME "
			+ " from WC_SHOP_ROLE a "
			+ " where not exists ( "
			+ " select 1 from WC_SHOP_ADMIN_ROLE b "
			+ " where a.WSR_ROLE_ID = b.WSAR_ROLE_ID and b.WSAR_ADMIN_ID = ? ) "
			+ " order by a.WSR_ROLE_ID desc ";
		List<Map<String, Object>> list = jdbcDao.queryForList(sql, new Object[]{adminId});
		return chgShopRoleList(list);
	}
	
	private List<WcShopRole> chgShopRoleList(List<Map<String, Object>> list)
	{
		List<WcShopRole> roleList = new ArrayList<WcShopRole>();
		WcShopRole role = null;
		for( Map<String, Object> map : list )
		{
			role = new WcShopRole();
			role.setWsrRoleId(Integer.valueOf(map.get("WSR_ROLE_ID").toString()));
			role.setWsrRoleName(map.get("WSR_ROLE_NAME").toString());
			roleList.add(role);
		}
		
		return roleList;
	}
	
	@Transactional(readOnly = true)
	public List<WcShopRole> queryShopRoleForAdminUpd2(Integer adminId)
	{
		String sql =
			" select b.WSR_ROLE_ID, b.WSR_ROLE_NAME , IFNULL(a.WSAR_ROLE_ID,0) as selected  "
			+ " from WC_SHOP_ROLE b  "
			+ " left join WC_SHOP_ADMIN_ROLE a on a.WSAR_ROLE_ID = b.WSR_ROLE_ID "
			+ " where a.WSAR_ADMIN_ID = ? "
			+ " order by b.WSR_ROLE_ID desc ";
		List<Map<String, Object>> list = jdbcDao.queryForList(sql, new Object[]{adminId});
		return chgShopRoleList1(list);
	}
	
	private List<WcShopRole> chgShopRoleList1(List<Map<String, Object>> list)
	{
		List<WcShopRole> roleList = new ArrayList<WcShopRole>();
		WcShopRole role = null;
		for( Map<String, Object> map : list )
		{
			role = new WcShopRole();
			role.setWsrRoleId(Integer.valueOf(map.get("WSR_ROLE_ID").toString()));
			role.setWsrRoleName(map.get("WSR_ROLE_NAME").toString());
			role.setSelected(((Integer)map.get("selected")).toString());
			roleList.add(role);
		}
		
		return roleList;
	}


	public List<String> queryShopRoleMenusForAdmin(String roleIds) {
		List<Map<String, Object>> menuList = 
			jdbcDao.queryForList("select distinct WSRM_MENU_ID menu_id from WC_SHOP_ROLE_MENU where WSRM_ROLE_ID in (" + roleIds + ")");
		
		return chgList(menuList, "menu_id");
	}
	
	@Transactional(readOnly = true)
	public List<String> queryShopRoleMenusForAdmin(String roleIds, Integer adminId)
	{
		StringBuilder sql = new StringBuilder();
		if( ! roleIds.equals("-1") )
		{
			sql.append(" select distinct WSRM_MENU_ID menu_id from WC_SHOP_ROLE_MENU where WSRM_ROLE_ID in (" + roleIds + ") ");
		}
		if( ! roleIds.equals("-1") && adminId != -1 )
		{
			sql.append(" union ");
		}
		if( adminId != -1 )
		{
			sql.append(" select WSAM_MENU_ID menu_id from WC_SHOP_ADMIN_MENU where WSAM_ADMIN_ID = " + adminId );
		}
		
		List<Map<String, Object>> menuList = jdbcDao.queryForList(sql.toString());
		
		return chgList(menuList, "menu_id");
	}


	public Page queryShopRole(WcShopRole role) 
	{
		// TODO Auto-generated method stub
		StringBuilder sql 	= new StringBuilder("select a.WSR_ROLE_ID,a.WSR_ROLE_NAME,a.WSR_ROLE_DESC,a.WSR_REGISTOR,a.WSR_REGIST_DATE"); 
		StringBuilder sqlCnt = new StringBuilder("select count(*) ");
		StringBuilder sqlConf = new StringBuilder(" from WC_SHOP_ROLE a where 1=1  ");
		List<Object> paraList = new ArrayList<Object>();
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		Page page = new Page(sql.toString(),sqlCnt.toString(),role.getCurrentPage(),role.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}
	
	
	
	
}

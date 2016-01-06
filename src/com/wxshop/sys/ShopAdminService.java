package com.wxshop.sys;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.util.StringUtil;
@Service
@Transactional
public class ShopAdminService implements IShopAdminService {

	

	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	
	@Transactional(readOnly = true)
	public boolean chkShopUsernameUnique(Integer adminId, String username)
	{
		int cnt = jdbcDao.queryForInt("select count(*) from WC_SHOP_ADMIN where WSA_ID != ? and WSA_USERNAME = ?", 
				new Object[]{adminId, username});
		if( cnt == 0 )
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	@Transactional(readOnly = true)
	public WcShopAdmin adminLogin(WcShopAdmin admin)
	{
		WcShopAdmin result = null;
		List<WcShopAdmin> list = hibernateDao.query(
			"from WcShopAdmin where wsaUsername = ? and wsaPwd = ?", 
			new Object[]{admin.getWsaUsername(), admin.getWsaPwdMd5()});
		if( list.size() > 0 )
		{
			result = list.get(0);
		}
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public boolean chkShopUsernameUnique(String username)
	{
		int cnt = jdbcDao.queryForInt("select count(*) from WC_SHOP_ADMIN where WSA_USERNAME = ?", new Object[]{username});
		if( cnt == 0 )
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public void addShopAdmin(WcShopAdmin admin) 
	{
		admin.setWsaPwd(DigestUtils.md5DigestAsHex(admin.getWsaPwd().getBytes()));
		hibernateDao.add(admin);
		addShopAdminOther(admin);
	
		// ���»���
		hibernateDao.flush();

	}
	
	private void addShopAdminOther(WcShopAdmin admin)
	{
		String adminId = String.valueOf(admin.getWsaId());
		String[] roleIds = admin.getRoleIds();
		if( roleIds != null && roleIds.length > 0 )
		{
			jdbcDao.batchUpdate("insert into WC_SHOP_ADMIN_ROLE (WSAR_ADMIN_ID, WSAR_ROLE_ID) values (?, ?)", StringUtil.getObjAryList(adminId, roleIds)); 
		}
		if( admin.getMenuIds() != null && admin.getMenuIds().length() > 0 )
		{
			String[] menuIds = admin.getMenuIds().split(",");
			jdbcDao.batchUpdate("insert into WC_SHOP_ADMIN_MENU (WSAM_ADMIN_ID, WSAM_MENU_ID) values (?, ?)", StringUtil.getObjAryList(adminId, menuIds)); 
		}
	}
	
	
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryShopAdminMenus(Integer adminId, String menuLevel)
	{
		String sql =
			  " select distinct c.WSM_ID, c.WSM_NAME, c.WSM_URL, "
			+ " c.WSM_LEVEL, c.WSM_ORDER, c.WSM_PARENT_ID  , (select case when count(*)>0 then '1' else '0' end  from WC_SHOP_MENU b where b.WSM_PARENT_ID = c.WSM_ID  ) as  WSM_ISPARENT            "
			+ " from WC_SHOP_ADMIN_ROLE a                                "
			+ " join WC_SHOP_ROLE_MENU b on a.WSAR_ROLE_ID = b.WSRM_ROLE_ID        "
			+ " join WC_SHOP_MENU c on b.WSRM_MENU_ID = c.WSM_ID             "
			+ " where a.WSAR_ADMIN_ID = ?                                "
			+ " and c.WSM_LEVEL = ?                                "
			+ " union                                               "
			+ " select e.WSM_ID, e.WSM_NAME, e.WSM_URL,          "
			+ " e.WSM_LEVEL, e.WSM_ORDER, e.WSM_PARENT_ID  , (select case when count(*)>0 then '1' else '0' end  from WC_SHOP_MENU b where b.WSM_PARENT_ID = e.WSM_ID  ) as  WSM_ISPARENT            "
			+ " from WC_SHOP_ADMIN_MENU d                                "
			+ " join WC_SHOP_MENU e on d.WSAM_MENU_ID = e.WSM_ID             "
			+ " where d.WSAM_ADMIN_ID = ?                                "
			+ " and e.WSM_LEVEL = ?                                "
			+ " order by WSM_ORDER asc                             ";
		List<Map<String, Object>>  list1 = jdbcDao.queryForList(sql, new Object[]{adminId, menuLevel, adminId, menuLevel});
		return list1;
	}
	
	public void updShopLoginTime(Integer adminId)
	{
		Date dateNow = new Date();
		jdbcDao.update("update WC_SHOP_ADMIN set WSA_LOGINDATE = ? where WSA_ID = ?",new Object[]{dateNow,adminId});
	}

	public WcShopAdmin getShopAdminById(Integer id) {
		// TODO Auto-generated method stub
		WcShopAdmin admin =  hibernateDao.get(WcShopAdmin.class, id);
		admin.setWsaPwdMd5(admin.getWsaPwd());
		return admin;
	}
	
	public void updShopAdmin(WcShopAdmin admin) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
		if( admin.getWsaPwd().length() == 0 )
		{
			admin.setWsaPwd(admin.getWsaPwdMd5());
		}
		else
		{
			admin.setWsaPwd(DigestUtils.md5DigestAsHex(admin.getWsaPwd().getBytes()));
		}
		
		
		hibernateDao.update(admin);
		Object[] obj = new Object[]{admin.getWsaId()};
		jdbcDao.delete("delete from WC_SHOP_ADMIN_ROLE where WSAR_ADMIN_ID = ?", obj);
		jdbcDao.delete("delete from WC_SHOP_ADMIN_MENU where WSAM_ADMIN_ID = ?", obj);
		addShopAdminOther(admin);
		// ���»��� ����Ա�������в����޸�Ϊ�޲��ţ���ʱҲ����»���
		hibernateDao.flush();
	
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryShopAdminNameToCache()
	{
		String sql = 
			  " select WSA_ID data, WSA_NAME value "
			+ " from WC_SHOP_ADMIN "
			+ " where 1=1"
			+ " order by WSA_NAME asc ";
		return jdbcDao.queryForList(sql);
	}
	
	@Transactional(readOnly = true)
	public boolean chkUsernameRandomStr(WcShopAdmin admin) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from WC_SHOP_ADMIN join WC_WEBSITE on WCS_ADMIN_ID = WSA_ID where WCS_RANDOM_NUM = ? and WSA_USERNAME = ?";
		return jdbcDao.queryForInt(sql, new Object[]{admin.getRandomStr(),admin.getWsaUsername()})>0;
	}
	
	
	
}

package com.wxshop.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.util.Page;

@Service
@Transactional
public class WeiMemberService implements IWeiMemberService {

	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	private static Logger log = Logger.getLogger(WeiMemberService.class);
	
	public void addWeiMember(LzWeiMember member) {
		// TODO Auto-generated method stub
		log.error("成功添加一条记录:"+hibernateDao.add(member));	
	}

	public Page queryWeiMember(LzWeiMember member) {
		// TODO Auto-generated method stub
		String wmbOpenid_Q=member.getWmbOpenid_Q();//openid
		String wmbType_Q=member.getWmbType_Q();//会员类型
		String wmbName_Q=member.getWmbName_Q();//会员名称
		String wmbMobule_Q=member.getWmbMobule_Q();//手机号
		Integer adminId_Q = member.getShopAdminId_Q();//
		log.error("adminId_Q:"+adminId_Q);
		if(adminId_Q==null)
		{
			adminId_Q = 0;
		}
		List<Object> paraList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(
			" select " +
			" a.WMB_ID,a.WMB_WEC_ID,a.WMB_CARD_ID,a.WMB_OPENID,a.WMB_TYPE,a.WMB_NAME," +
			" a.WMB_MOBULE,a.WMB_STATUS,a.WMB_DESC,a.WMB_REGISTOR,a.WMB_REGISTDATE," +
			" a.WEB_WCS_ID,b.WEC_APP_NAME,c.WCS_WEBSITE_NAME,c.WCS_APP_NAME,d.WSA_NAME " 
		);
		StringBuilder sqlCnt = new StringBuilder(
			" select count(*) " 
		);
		StringBuilder sqlConf = new StringBuilder(
			" from LZ_WEI_MEMBER a " +
			" left join LZ_WEI_ENTER b on b.WEC_ID =a.WMB_WEC_ID " +
			" left join WC_WEBSITE c on c.WCS_ID =a.WEB_WCS_ID  " +
			" left join WC_SHOP_ADMIN d on d.WSA_ID = c.WCS_ADMIN_ID   " +
			" where 1=1  "	
		);
		if(adminId_Q>0)
		{
			sqlConf.append(" and d.WSA_ID = ? ");
			paraList.add(adminId_Q);
		}
		//会员姓名
		if(wmbName_Q!=null&&wmbName_Q.length()>0)
		{

			sqlConf.append(" and a.WMB_NAME like ? ");
			paraList.add("%"+wmbName_Q+"%");
		}
		//openId
		if(wmbOpenid_Q!=null&&wmbOpenid_Q.length()>0)
		{
			sqlConf.append(" and a.WMB_OPENID = ? ");
			paraList.add(wmbOpenid_Q);
		}
		//手机号
		if(wmbMobule_Q!=null&&wmbMobule_Q.length()>0)
		{
			sqlConf.append(" and a.WMB_MOBULE = ? ");
			paraList.add(wmbMobule_Q);
		}
		//会员类型
		if(wmbType_Q!=null&&wmbType_Q.length()>0)
		{
			sqlConf.append(" and a.WMB_TYPE like ? ");
			paraList.add("%"+wmbType_Q+"%");
		}
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		
		System.out.println(sql.toString());
		Page page = new Page(sql.toString(),sqlCnt.toString(),member.getCurrentPage(),member.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}

	public boolean checkOpenIdExsit(String openId, Integer wecId) {
		// TODO 检查当前openid 是否已经绑定
		String sql = "select count(*) from LZ_WEI_MEMBER a where a.WMB_OPENID = ? and a.WMB_WEC_ID = ?";
		int cnt = jdbcDao.queryForInt(sql,new Object[]{openId,wecId});
		return cnt>0;
	}

	public void delWeiMember(String[] wmbIds) {
		// TODO Auto-generated method stub
		String strWmbId = StringUtils.arrayToCommaDelimitedString(wmbIds);
		String sql = " delete from LZ_WEI_MEMBER where WMB_ID in ("+strWmbId+")";
		jdbcDao.delete(sql);
	}

	public LzWeiMember getWeiMemberByOpenId(String openid, String appId) {
		// TODO Auto-generated method stub
		String sql  = 
			" select a.WMB_ID from LZ_WEI_MEMBER a join LZ_WEI_ENTER b on a.WMB_WEC_ID = b.WEC_ID " +
			" where a.WMB_OPENID = ? and b.WEC_APP_ID = ? ";
		List<Map<String,Object>> list = jdbcDao.queryForList(sql, new Object[]{openid,appId});
		if(list.size()>0)
		{
			Map<String,Object> map = list.get(0);
			Integer wmbId = (Integer)map.get("WMB_ID");
			return getWeiMemberByid(wmbId);
		}
		else
		{
			return null;
		}

	}
	
	public LzWeiMember getWeiMemberByid(Integer wmbId)
	{
		return hibernateDao.get(LzWeiMember.class, wmbId);
	}

	public String getMemberCardId() {
		// TODO Auto-generated method stub
		String sql = "select max(a.WMB_CARD_ID) as cardId from LZ_WEI_MEMBER a ";
		String cardId = jdbcDao.queryForString(sql);
		if(cardId == null||cardId.length() ==0)
		{
			return "1000";
		}
		else
		{
			Long cardIdLong = Long.parseLong(cardId)+1;
			return  cardIdLong.toString();
		}
	}

	public void updateWeiMember(LzWeiMember weimember) {
		// TODO Auto-generated method stub
		hibernateDao.update(weimember);
		
	}
	
	public static void main (String[] args)
	{
		String a1 = "3276911111111";
		System.out.println(Long.parseLong(a1));
		String a2 = "3276911111111";
		System.out.println(Integer.parseInt(a2));
	}


	public List<Map<String, Object>> getMemNameAllByWei(Integer wcsId) {
		// TODO Auto-generated method stub
		String sql  = new String(" select a.WMB_ID as data, a.WMB_NAME as value from LZ_WEI_MEMBER a where a.WEB_WCS_ID = ? ");
		return jdbcDao.queryForList(sql, new Object[]{wcsId});
	}

	public Page queryWeiMemberByWcsId(LzWeiMember member) {
		
		// TODO Auto-generated method stub
		Integer wcsId = member.getWmbWcsId_Q();
		
		StringBuilder sql  = new StringBuilder( 
			" select " +
			" a.WMB_ID,a.WMB_WEC_ID,a.WMB_CARD_ID,a.WMB_OPENID,a.WMB_TYPE,a.WMB_NAME," +
			" a.WMB_MOBULE,a.WMB_STATUS,a.WMB_DESC,a.WMB_REGISTOR,a.WMB_REGISTDATE," +
			" a.WEB_WCS_ID,b.WEC_APP_NAME,c.WCS_WEBSITE_NAME,d.WSA_NAME " 
		);
		StringBuilder sqlCnt = new StringBuilder(
			" select count(*) "
		);
		StringBuilder sqlConf = new StringBuilder(
			" from LZ_WEI_MEMBER a " +
			" left join LZ_WEI_ENTER b on b.WEC_ID =a.WMB_WEC_ID " +
			" left join WC_WEBSITE c on c.WCS_ID =a.WEB_WCS_ID  " +
			" left join WC_SHOP_ADMIN d on d.WSA_ID = c.WCS_ADMIN_ID   " +
			" where a.WEB_WCS_ID = ? "
		);
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(wcsId);
		Page page = new Page(sql.toString(),sqlCnt.toString(),member.getCurrentPage(),member.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}
	
	

	
	
}

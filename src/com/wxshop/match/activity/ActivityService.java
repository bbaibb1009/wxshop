package com.wxshop.match.activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.match.WcMacthActivity;
import com.wxshop.util.Page;

@Service
@Transactional
public class ActivityService implements IActivityService {
	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;

	public Page queryActivity(WcMacthActivity activity) {
		// TODO Auto-generated method stub
		
		StringBuilder sql = new StringBuilder(
				" select " +
				"a.WTA_ID,a.WTA_TYPE,a.WTA_WMA_ID,a.WTA_DEPT,a.WTA_LOC_JOSN,	" +
				"a.WTA_IMG,	a.WTA_TITLE,a.WTA_CONTENT,	a.WTA_SORT,	a.WTA_REGIST_END_DATE,	" +
				"a.WTA_DIDIAN_START,		a.WTA_JIHE_STARTTIME,	a.WTA_DIDIAN_END,	" +
				"a.WTA_JIHE_ENDTIME,	a.WTA_FEE_TYPE,	a.WTA_FEE,	a.WTA_ADMIN,	" +
				"a.WTA_FEE_START_TIME,a.WTA_FEE_END_TIME,a.WTA_DESC,	" +
				"a.WTA_STATUS,a.WTA_REGISTOR,a.WTA_REGIST_DATE");
		StringBuilder sqlCnt = new  StringBuilder("select count(*) ");
		StringBuilder sqlConf = new StringBuilder(" from WC_MACTH_ACTIVITY a where 1=1 ");
		
		List<Object> paraList = new ArrayList<Object>();
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		Page page = new Page(sql.toString(),sqlCnt.toString(),activity.getCurrentPage(),activity.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		
		return page;
	}
	
	
	
	
}

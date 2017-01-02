package com.wxshop.match;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.util.Page;


@Service
@Transactional
public class MatchService implements IMatchService {

	
	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	public Page queryMatch(WcMatch match) 
	{
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(
			" select " +
			" a.WMA_ID," +
			" a.WMA_NAME," +
			" a.WMA_PLACE," +
			" a.WMA_RUNTIME," +
			" a.WMA_GAME_PROJECT," +
			" a.WMA_YEAR," +
			" a.WMA_SESSION_INDEX," +
			" a.WMA_LAST_SESSION_ID," +
			" a.WMA_EMERGENCY_CONTRACT,	a.WMA_EMERGENCY_PHONE," +
			" a.WMA_STATUS,	a.WMA_DESC,	a.WMA_REGISTOR,a.WMA_REGIST_DATE ");
		StringBuilder sqlCnt = new StringBuilder(
			" select count(*) "
		);
		StringBuilder sqlConf = new StringBuilder(
			" from WC_MATCH a where 1=1 "
		);
		List<Object> paraList = new ArrayList<Object>();
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		sql.append(" order by a.WMA_RUNTIME desc ");
		Page page = new Page(sql.toString(),sqlCnt.toString(),match.getCurrentPage(),match.getPageSize());
		jdbcDao.queryForPage(page);
		return page;
	}

}

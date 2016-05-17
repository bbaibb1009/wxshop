package com.wxshop.weixin;

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
public class WeiFuwuhaoService implements IWeiFuwuhaoService {
	
	@Autowired 
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;

	public Page queryFuwuhao(WcWeiFuwuhao fuwuhao) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(
			" select a.FWH_ID,a.FWH_APP_NAME,a.FWH_APP_ID," +
			" a.FWH_APP_SECRET,a.FWH_REDERECT_URL,a.FWH_TOKEN, "+
			" a.FWH_ENCODING_AES_KEY,a.FWH_AES_TYPE, a.FWH_APP_TYPE,a.FWH_CUS_TYPE, " +
			" a.FWH_ACCOUNT_TYPE, a.FWH_ENTER_ID, a.FWH_DEFAULT_MSG, "+
			" a.FWH_SUBSCRIBE_MSG,a.FWH_STATUS, a.FWH_DESC,a.FWH_REGISTOR, a.FWH_REGISTDATE ");
		StringBuilder sqlCnt = new StringBuilder(" select count(*) ");
		StringBuilder sqlConf = new StringBuilder(" from WC_WEI_FUWUHAO a where 1=1  ");
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		List<Object> paraList = new ArrayList<Object>();
		Page page = new Page(sql.toString(),sqlCnt.toString(),fuwuhao.getCurrentPage(),fuwuhao.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}
	
	

}

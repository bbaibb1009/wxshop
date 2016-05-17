package com.wxshop.weixin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;

@Service
@Transactional
public class WeixinService implements IWeixinService {

	@Autowired 
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;

	public WcWeiFuwuhao getWeiFwhByAppId(String appId) {
		// TODO Auto-generated method stub
		String sql = new String(" select a.FWH_ID from WC_WEI_FUWUHAO a where a.FWH_APP_ID = ? ");
		List<Map<String,Object>> list = jdbcDao.queryForList(sql, new Object[]{appId});
		if(list.size()>0)
		{
			Map<String,Object> map = list.get(0);
			Integer fwhId = (Integer)map.get("FWH_ID");
			return this.getWeiFwhByid(fwhId);
		}
		else
		{
			return null;
		}
	}

	public WcWeiFuwuhao getWeiFwhByid(Integer id)
	{
		return hibernateDao.get(WcWeiFuwuhao.class, id);
	}
	
}

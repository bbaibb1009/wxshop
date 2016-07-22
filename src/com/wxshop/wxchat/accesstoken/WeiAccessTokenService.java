package com.wxshop.wxchat.accesstoken;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.util.DateUtil;
import com.wxshop.weichat.fuwuhao.IWeiFuwuhaoService;
import com.wxshop.weichat.fuwuhao.WcWeiFuwuhao;

@Service
@Transactional
public class WeiAccessTokenService implements IWeiAccessTokenService {

	@Autowired 
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	@Autowired
	private IWeiFuwuhaoService fuwuhaoService;
	
	public String getCurrentAccessTokenStr(String appId) throws ParseException
	{
		String hql = "from WcWeiAccesstoken a where a.watAppId = ? and a.watStatus  = 1 ";
		List<WcWeiAccesstoken> tokenList = hibernateDao.query(hql,new Object[]{appId});
		if(tokenList.size()>0)
		{
			WcWeiAccesstoken token = tokenList.get(0);
			String tokenStr = token.getWatToken();
			Date expireIn = DateUtil.parseDate(token.getWatExpiresIn(), "yyyy-MM-dd HH:mm:ss");
			if(expireIn.getTime()<=(new Date()).getTime())
			{
				tokenStr = this.updAccessToken(token.getWatAppid());
			}
			return tokenStr;
		}
		else
		{
			return null;
		}
	}
	
	
	public String updAccessToken(String appId) 
	{
		try
		{
			WcWeiFuwuhao fuwuhao = fuwuhaoService.getWeiFwhByAppId(appId);
			JSONObject jsonObject = cn.pudding.weichat.accesstoken.AccessTokenUtil.getAccessTokenJson(fuwuhao.getFwhAppId(), fuwuhao.getFwhAppSecret());
			String accessToken 	= jsonObject.getString("access_token");
			int expires_in  	= jsonObject.getInt("expires_in");
			if(accessToken!=null&&accessToken.length()>0)
			{
				this.disabledAccessToken(appId);
				this.addAccessToken(accessToken,expires_in,fuwuhao.getFwhId(),fuwuhao.getFwhAppId());
				return accessToken;
			}
			else
			{
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void disabledAccessToken(String appId)
	{
		String sql = " update WC_WEI_ACCESSTOKEN set WAT_STATUS = '0' where WAT_APPID = ? ";
		jdbcDao.update(sql,new Object[]{appId});
	}
	
	public void addAccessToken(String accessToken,int expires_in,Integer wecId,String appid)
	{
		WcWeiAccesstoken accToken = new WcWeiAccesstoken();
		Date now = new Date();
		now.setTime(now.getTime()+(expires_in*1000)); 
		accToken.setWatToken(accessToken);
		accToken.setWatExpiresIn(DateUtil.parseString(now, "yyyy-MM-dd HH:mm:ss"));
		accToken.setWatWecId(wecId);
		accToken.setWatAppid(appid);
		accToken.setWatStatus("1");
		hibernateDao.add(accToken);
	}
	
	
}

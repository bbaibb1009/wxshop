package com.wxshop.weichat.fans;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.util.DateUtil;
import com.wxshop.weixin.IWeiFuwuhaoService;
import com.wxshop.weixin.WcWeiFuwuhao;
@Service
@Transactional
public class WeiFansService implements IWeiFansService {
	
	@Autowired 
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	@Autowired
	private IWeiFuwuhaoService weiFuwuhaoService;
	
	
	

	public void addFansBySubscribe(String openId,String appId) 
	{
	   	try
	   	{
	    	//先根据appId 查出企业微信对象来
	        WcWeiFuwuhao fuwuhao= weiFuwuhaoService.getWeiFwhByAppId(appId);
	        WcWeiFans fans = this.getFansByOpenId(openId,fuwuhao.getFwhAppId());
	        //现在本地查出该粉丝存不存在，如果存在 就 取出 不存在先新建 再拉取 然后更新本地。
	    	if(fans==null)
	    	{
	    		fans = new WcWeiFans();
	    		fans.setWacOpenid(openId);
	    		fans.setWacStatus("0");
	    		fans.setWacAppid(fuwuhao.getFwhAppId());
	    		fans.setWacWecId(fuwuhao.getFwhId());
	    		this.addFans(fans);
	    	}
	    	Map<String,Object> userMap = fuwuhao.queryUserInfo(openId);
	    	if(userMap!=null)
	    	{
	    		fans.setWacSubscribe(Integer.parseInt((String)userMap.get("subscribe")));
	    		fans.setWacNickName((String)userMap.get("nickname"));
	    		fans.setWacSex(userMap.get("sex").toString());
	    		fans.setWacLanguage((String)userMap.get("language"));
	    		fans.setWacCity((String)userMap.get("city"));
	    		fans.setWacProvince((String)userMap.get("province"));
	    		fans.setWacCountry((String)userMap.get("country"));
	    		fans.setWacHeadImgUrl((String)userMap.get("headimgurl"));
	    		fans.setWacStatus("1");
	    		Date subscribeTime = new Date();
	    		subscribeTime.setTime((Long.parseLong((String)userMap.get("subscribe_time")))*(long)1000);
	    		fans.setWacSubscribeTime(DateUtil.parseString(subscribeTime,"yyyy-MM-dd HH:mm:ss"));
	    		this.updFans(fans);
	    	}
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}

	public void addFans(WcWeiFans fans) {
		// TODO Auto-generated method stub
		hibernateDao.add(fans);
	}

	public WcWeiFans getFansByOpenId(String openId, String appId) {
		// TODO Auto-generated method stub
		return null;
	}



	public void updFans(WcWeiFans fans) {
		// TODO Auto-generated method stub
		hibernateDao.update(fans);
	}
	 
	 
}

package com.wxshop.weichat.fans;

import java.util.Date;
import java.util.Map;

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
	    	//�ȸ���appId �����ҵ΢�Ŷ�����
	        WcWeiFuwuhao fuwuhao= weiFuwuhaoService.getWeiFwhByAppId(appId);
	        WcWeiFans fans = this.getFansByOpenId(openId,fuwuhao.getFwhAppId());
	        //���ڱ��ز���÷�˿�治���ڣ�������� �� ȡ�� ���������½� ����ȡ Ȼ����±��ء�
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

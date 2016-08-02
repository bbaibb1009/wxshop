package com.wxshop.weichat.msg.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.pudding.weichat.message.response.WcWeiBaseMsgResp;
import cn.pudding.weichat.message.response.WcWeiTextMsgResp;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.util.DateUtil;
import com.wxshop.util.Page;
import com.wxshop.wxchat.msg.WcWeiMessage;
@Service
@Transactional
public class WeixinMsgManageService implements IWeixinMsgManageService {

	@Autowired 
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	   
	private static Logger log = Logger.getLogger(WeixinMsgManageService.class);
	
    public WcWeiBaseMsgResp queryDefaultMsgByAppId(WcWeiBaseMsgResp respMessage,String content,String appId)
    {
    	WcWeiMessage msg = this.getKeyWordMsgByContent(content, appId);
    	//先判断content是不是关键字
    	if(msg!=null)
    	{
    		String msgType = msg.getWmgMsgType();
    		if(msgType.equals("2"))
    		{
    			//log.error("***********************318行：是关键字且是文本信息***********************");
    			WcWeiTextMsgResp msgResp = new WcWeiTextMsgResp(respMessage);
    			msgResp.setContent(msg.getWmgContent());
    			return msgResp;
    		}
    		else if(msgType.equals("2"))
    		{
    			//log.error("***********************318行：是关键字且是图文信息***********************");
    			//LzNewsMsgResp msgResp = new LzNewsMsgResp(respMessage);
    			WcWeiTextMsgResp msgResp = new WcWeiTextMsgResp(respMessage);
    			msgResp.setContent(msg.getWmgContent());
    			return msgResp;
    		}
    		else
    		{
    			//log.error("***********************318行：是关键字 但不是文本信息***********************");
    			return cn.pudding.weichat.message.TextMsgUtil.getDefualtTextMsg(respMessage);
    		}
    	}
    	//不是关键字的就返回默认回复信息
    	else
    	{
        	String sql = " SELECT b.WMG_ID, b.WMG_MSG_TYPE,b.WMG_CONTENT  FROM WC_WEI_FUWUHAO a JOIN WC_WEI_MESSAGE b ON a.FWH_APP_ID = b.WMG_APP_ID WHERE a.FWH_APP_ID = ? AND b.WMG_REPLY_TYPE = '2' ";
        	List<Map<String,Object>> list = jdbcDao.queryForList(sql,new Object[]{appId});
        	if(list.size()>0)
        	{
        		Map<String,Object> map = list.get(0);
        		Integer wmgId = (Integer)map.get("WMG_ID");
        		msg = this.getWcWeiMessageById(wmgId);
        		String msgType = msg.getWmgMsgType() ;
        		if(msgType.equals("2"))
        		{
        			//log.error("***********************338行***********************");
        			WcWeiTextMsgResp msgResp = new WcWeiTextMsgResp(respMessage);
        			msgResp.setContent(msg.getWmgContent());
        			return msgResp;
        		}
        		else
        		{
        			//log.error("***********************345行：不是返回文本信息***********************");
        			return cn.pudding.weichat.message.TextMsgUtil.getDefualtTextMsg(respMessage);
        		}
        	}
        	else
        	{
        		return cn.pudding.weichat.message.TextMsgUtil.getDefualtTextMsg(respMessage);
        	}
    	}
    }
    
//    
//    
//    public LzWeiBaseMsgResp  querySubscribeMsgByAppId(LzWeiBaseMsgResp respMessage,String appId)
//    {
//    	LzWeiMessage msg = this.getSubScribeMsgByApp(appId);
//    	//先判断content是不是关键字
//    	if(msg!=null)
//    	{
//    		String msgType = msg.getWmgMsgType();
//    		if(msgType.equals("2"))
//    		{
//    			LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
//    			msgResp.setContent(msg.getWmgContent());
//    			return msgResp;
//    		}
//    		else
//    		{
//    			//log.error("***********************318行：有关注回复 但不是文字消息***********************");
//    			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
//    		}
//    	}
//    	else
//    	{
//    		//log.error("***********************387行：没有设置关注回复***********************");
//			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
//    		
//    	}
//     }
//    
//    
    public Page queryWcWeiMsg(WcWeiMessage msg) {
		// TODO Auto-generated method stub
    	String wmgReplyType_Q = msg.getWmgReplyType_Q();
    	String wmgMsgType_Q = msg.getWmgMsgType_Q();
    	String wmgAesType_Q = msg.getWmgAesType_Q();
    	String wmgAppId_Q = msg.getWmgAppId_Q();
    	StringBuilder sql =new StringBuilder(
  			
    		" select " +
    		" a.WMG_APP_ID, " +
    		" a.WMG_ID," +
    		" a.WMG_CONTENT," +
    		" a.WMG_REPLY_TYPE," +
    		" a.WMG_MSG_TYPE," +
    		" a.WMG_AES_TYPE," +
    		" a.WMG_STATUS," +
    		" a.WMG_DESC," +
    		" b.WSA_NAME WMG_REGISTOR," +
    		" date_format(a.WMG_REGISTDATE, '%Y-%m-%d %H:%i:%s') as WMG_REGISTDATE" +
    		" from WC_WEI_MESSAGE a " +
    		" left join WC_SHOP_ADMIN b on a.WMG_REGISTOR = b.WSA_ID " +
    		" where 1=1 "
    	);
    	StringBuilder sqlCnt = new StringBuilder(
    		" select count(*) from WC_WEI_MESSAGE a " +
    		" where 1=1 "
    	);
    	List<Object> paraList = new ArrayList<Object>(){};
    	if(wmgAppId_Q!=null && wmgAppId_Q.length()>0)
    	{
    		sql.append(" and a.WMG_APP_ID =  ? ");
    		sqlCnt.append(" and a.WMG_APP_ID = ? ");
    		paraList.add(wmgAppId_Q);
    	}
    	if(wmgReplyType_Q!=null && wmgReplyType_Q.length()>0)
    	{
    		sql.append(" and a.WMG_REPLY_TYPE =  ? ");
    		sqlCnt.append(" and a.WMG_REPLY_TYPE = ? ");
    		paraList.add(wmgReplyType_Q);
    	}
    	if(wmgMsgType_Q!=null && wmgMsgType_Q.length()>0)
    	{
    		sql.append(" and a.WMG_MSG_TYPE =  ? ");
    		sqlCnt.append(" and a.WMG_MSG_TYPE = ? ");
    		paraList.add(wmgMsgType_Q);
    	}
    	if(wmgAesType_Q!=null && wmgAesType_Q.length()>0)
    	{
    		sql.append(" and a.WMG_AES_TYPE =  ? ");
    		sqlCnt.append(" and a.WMG_AES_TYPE = ? ");
    		paraList.add(wmgAesType_Q);
    	}
    	Page page = new Page(sql.toString(),sqlCnt.toString(),msg.getCurrentPage(),msg.getPageSize(),paraList.toArray());
    	jdbcDao.queryForPage(page);
		return page;
	}

	public void addWcWeiMessage(WcWeiMessage msg) 
	{
		// TODO Auto-generated method stub
		hibernateDao.add(msg);
	}
//
	public WcWeiMessage getWcWeiMessageById(Integer id)
	{
		return hibernateDao.get(WcWeiMessage.class, id);
	}
//	
	public WcWeiMessage getKeyWordMsgByContent(String content,String appId)
	{
		String sql = 
			" select a.WKG_WMG_ID " +
			" from WC_WEI_KEYWORD_MESSAGE a " +
			" join WC_WEI_FUWUHAO b on a.WKG_WEC_ID = b.FWH_ID  " +
			" where b.FWH_APP_ID = ? and a.WKG_KEYWORDS = ? ";
		List<Map<String,Object>> list = jdbcDao.queryForList(sql, new Object[]{appId,content});
		if(list.size()>0)
		{
			Map<String,Object> map = list.get(0);
			Integer wmgId = (Integer)map.get("WKG_WMG_ID");
			WcWeiMessage msgWei = this.getWcWeiMessageById(wmgId); 
			return msgWei;
		}
		else
		{
			return null;
		}
	}
	
	public WcWeiMessage getSubScribeMsgByApp(String appId)
	{
		String sql = 
			" select a.WMG_ID from WC_WEI_MESSAGE a " +
			" where a.WMG_APP_ID = ? and a.WMG_REPLY_TYPE = '3' ";
		List<Map<String,Object>> list = jdbcDao.queryForList(sql, new Object[]{appId});
		if(list.size()>0)
		{
			Map<String,Object> map = list.get(0);
			Integer wmgId = (Integer)map.get("WMG_ID");
			WcWeiMessage msgWei = this.getWcWeiMessageById(wmgId); 
			return msgWei;
		}
		else
		{
			return null;
		}
	}
	
	public WcWeiBaseMsgResp  querySubscribeMsgByAppId(WcWeiBaseMsgResp respMessage,String appId)
    {
    	WcWeiMessage msg = this.getSubScribeMsgByApp(appId);
    	//先判断content是不是关键字
    	if(msg!=null)
    	{
    		String msgType = msg.getWmgMsgType();
    		if(msgType.equals("2"))
    		{
    			WcWeiTextMsgResp msgResp = new WcWeiTextMsgResp(respMessage);
    			msgResp.setContent(msg.getWmgContent());
    			return msgResp;
    		}
    		else
    		{
    			log.error("***********************318行：有关注回复 但不是文字消息***********************");
    			return cn.pudding.weichat.message.TextMsgUtil.getDefualtTextMsg(respMessage);
    		}
    	}
    	else
    	{
    		log.error("***********************387行：没有设置关注回复***********************");
			return cn.pudding.weichat.message.TextMsgUtil.getDefualtTextMsg(respMessage);
    	}
     }
	
	

	public Page queryKeyWordMsgByAppId(WcWeiMessage msg)
	{
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder( 
			" 	select  " +
			"  	b.WMG_CONTENT,b.WMG_ID ,c.WKG_APP_ID,c.WKG_KEYWORDS " 
		);
		StringBuilder sqlCnt = new StringBuilder(
			"	select count(*) "
		);
		StringBuilder sqlConf = new StringBuilder(
			"  	from WC_WEI_MESSAGE b   " +
			" 	join WC_WEI_KEYWORD_MESSAGE c on  c.WKG_WMG_ID = b.WMG_ID   " +
			" 	where c.WKG_APP_ID = ? "
		);
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(msg.getWmgAppId_Q());
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		Page page = new Page(sql.toString(),sqlCnt.toString(),msg.getCurrentPage(),msg.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}

	public void updWxMsg(WcWeiMessage msg) {
		// TODO Auto-generated method stub
		hibernateDao.update(msg);
	}

	public void addWcKeywordMessage(WcWeiMessage msg, String[] keyWordArray,Integer adminId) {
		// TODO Auto-generated method stub
		String sql = new String(
			" insert into 	WC_WEI_KEYWORD_MESSAGE 	(WKG_APP_ID,	WKG_KEYWORDS,	WKG_WMG_ID,	WKG_STATUS,	WKG_DESC,	WKG_REGISTOR,	WKG_REGISTDATE) " +
			" 								values 	(			?,				?,			?,			?,			?,		?,			?	) "
		);
		List<Object[]> paralist = new ArrayList<Object[]>();
		for(int i = 0;i<keyWordArray.length;i++)
		{
			String keyWord = keyWordArray[i];
			paralist.add(new Object[]{msg.getWmgAppId(),keyWord,msg.getWmgId(),"1000","",adminId,DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss")});
		}
		jdbcDao.batchUpdate(sql, paralist);
		
	}
	
	
}

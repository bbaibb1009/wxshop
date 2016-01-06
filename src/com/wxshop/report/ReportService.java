package com.wxshop.report;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.goods.WcGoodsOut;
import com.wxshop.util.Page;

@Service
@Transactional
public class ReportService  implements IReportService {

	@Autowired
	private IJdbcDao jdbcDao;

	@Autowired
	private IHibernateDao hibernateDao;
	
	public Page queryGoodsLirun(WcGoodsOut goodsOut) {
		// TODO Auto-generated method stub
		String wgoType_Q = goodsOut.getWgoType_Q();
		String wgoWgsId_Q = goodsOut.getWgoWgsId_Q();
		String wgoWgfId_Q = goodsOut.getWgoWgfId_Q();
		String wgoWmeId_Q = goodsOut.getWgoWmeId_Q();
		StringBuilder sql = new StringBuilder(
			" select " +
			" a.WGO_TYPE, a.WGO_OUT_NUM, a.WGO_OUT_PRICE," +
			" a.WGO_OUT_ADMIN, a.WGO_OUT_TIME, a.WGO_WMB_ID ," +
			" b.WMB_NAME ,c.WGS_NAME,c.WGS_BZ_PRICE, d.WGF_NAME, e.WIO_NUMBER , " +
			" f.WGI_IN_PRICE,(a.WGO_OUT_PRICE *e.WIO_NUMBER)   as zongjia, " +
			" (a.WGO_OUT_PRICE -  f.WGI_IN_PRICE)* e.WIO_NUMBER as zonglirun," +
			" (a.WGO_OUT_PRICE -  f.WGI_IN_PRICE) as danweilirun "
		);
		StringBuilder sqlCnt = new StringBuilder(" select count(*) ");
		StringBuilder sqlConf = new StringBuilder(
			"  from WC_GOODS_OUT a 		" +
			"  join WC_GOODS_IN_OUT e 		on e.WIO_WGO_ID = a.WGO_ID " +
			"  join WC_GOODS_IN f 			on e.WIO_WGI_ID = f.WGI_ID " +
			"  left join LZ_WEI_MEMBER b 	on b.WMB_ID = a.WGO_WMB_ID " +
			"  left join WC_GOODS c 		on c.WGS_ID = a.WGO_WGS_ID " +
			"  left join WC_GOODS_FENLEI d 	on d.WGF_ID = c.WGS_WGF_ID " +
			"  where a.WGO_WCS_ID = ?  									"
		);
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(goodsOut.getWgoWcsId());
		if(wgoType_Q!=null&&wgoType_Q.length()>0)
		{
			
		}
		if(wgoWgsId_Q!=null&&wgoWgsId_Q.length()>0)
		{
			
		}
		if(wgoWgfId_Q!=null&&wgoType_Q.length()>0)
		{
			
		}
		if(wgoWmeId_Q!=null&&wgoWmeId_Q.length()>0)
		{
			
		}
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		sql.append(" order by a.WGO_TYPE");
		
		Page page =  new Page(sql.toString(),sqlCnt.toString(),goodsOut.getCurrentPage(),goodsOut.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}
	

}

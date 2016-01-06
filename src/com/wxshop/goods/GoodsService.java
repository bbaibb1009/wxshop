package com.wxshop.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wxshop.common.dao.IHibernateDao;
import com.wxshop.common.dao.IJdbcDao;
import com.wxshop.util.DateUtil;
import com.wxshop.util.Page;

@Service
@Transactional
public class GoodsService implements IGoodsService {
	@Autowired
	private IJdbcDao jdbcDao;

	@Autowired
	private IHibernateDao hibernateDao;

	private static Logger log = Logger.getLogger(GoodsService.class);

	public Page queryGoodsFeilei(WcGoodsFenlei feilei) {
		// TODO Auto-generated method stub
		Integer wcsId = feilei.getWgfWcsId();

		StringBuilder sql = new StringBuilder(
			" select "
			+ " a.WGF_ID,a.WGF_NAME,a.WGF_WCS_ID,a.WGF_STATUS,a.WGF_REGISTOR,a.WGF_REGISTDATE,b.WSA_NAME " 
			+ " from WC_GOODS_FENLEI a " 
			+ " left join WC_SHOP_ADMIN b on b.WSA_ID = a.WGF_REGISTOR "
			+ " where 1=1 "
		);

		StringBuilder sqlCnt = new StringBuilder(
			" select count(*) from WC_GOODS_FENLEI a " +
			" left join WC_SHOP_ADMIN b on b.WSA_ID = a.WGF_REGISTOR " +
			" where 1=1 ");
		List<Object> paraList = new ArrayList<Object>();
		if (wcsId != null && wcsId > 0) {
			sql.append(" and a.WGF_WCS_ID = ? ");
			sqlCnt.append(" and a.WGF_WCS_ID = ? ");
			paraList.add(wcsId);
		}
		Page page = new Page(sql.toString(), sqlCnt.toString(), feilei
				.getCurrentPage(), feilei.getPageSize(), paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}

	public void addGoodsFeilei(WcGoodsFenlei feilei) {
		// TODO Auto-generated method stub
		hibernateDao.add(feilei);
	}

	public WcGoodsFenlei getFeileiById(Integer id) {
		// TODO Auto-generated method stub
		return hibernateDao.get(WcGoodsFenlei.class, id);
	}

	public void updGoodsFenlei(WcGoodsFenlei feilei) {
		// TODO Auto-generated method stub
		hibernateDao.update(feilei);
	}

	public void addGoodsFenlei(WcGoodsFenlei fenlei) {
		// TODO Auto-generated method stub
		hibernateDao.add(fenlei);
	}

	public Page queryGoods(WcGoods goods) {
		// TODO 查询商品
		Integer wfgId = goods.getWgsWgfId();
		StringBuilder sql = new StringBuilder(" select " + " a.WGS_ID,"
				+ " a.WGS_NAME," + " a.WGS_WGF_ID," + " a.WGS_WCS_ID,"
				+ " a.WGS_BZ_PRICE," + " a.WGS_LS_PRICE," + " a.WGS_KUCUN,"
				+ " a.WGS_STATUS," + " a.WGS_REGISTOR," + " a.WGS_REGISTDATE,"
				+ " b.WGF_NAME " + " from WC_GOODS a "
				+ " left join WC_GOODS_FENLEI b on a.WGS_WGF_ID = b.WGF_ID "
				+ " where 1=1 ");
		StringBuilder sqlCnt = new StringBuilder(" select count(*) "
				+ " from WC_GOODS a "
				+ " left join WC_GOODS_FENLEI b on a.WGS_WGF_ID = b.WGF_ID "
				+ " where 1=1 ");
		List<Object> paraList = new ArrayList<Object>();
		if (wfgId != null && wfgId > 0) {
			sql.append(" and a.WGS_WGF_ID = ? ");
			sqlCnt.append(" and a.WGS_WGF_ID = ? ");
			paraList.add(wfgId);
		}
		Page page = new Page(sql.toString(), sqlCnt.toString(), goods
				.getCurrentPage(), goods.getPageSize(), paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}

	public void addGoods(WcGoods goods) {
		// TODO Auto-generated method stub
		hibernateDao.add(goods);
	}

	public WcGoods getGoodsById(Integer wgsId) {
		// TODO Auto-generated method stub
		return hibernateDao.get(WcGoods.class, wgsId);
	}

	public void updGoods(WcGoods goods) {
		// TODO Auto-generated method stub
		hibernateDao.update(goods);
	}

	public void delGoods(WcGoods goods) {
		// TODO Auto-generated method stub
		String[] wgsIds = goods.getWgsIds();
		String wgsIdStr = StringUtils.arrayToCommaDelimitedString(wgsIds);
		String sql1 = "delete from WC_GOODS_IN where WGI_WGS_ID in ("
				+ wgsIdStr + ")";// 将分类下所有的商品对应入库记录删除
		String sql2 = "delete from WC_GOODS_OUT where WGO_WGS_ID in ("
				+ wgsIdStr + ")";// 将分类下所有的商品对应出库记录删除
		String sql = "delete from WC_GOODS where WGS_ID in (" + wgsIdStr + ")";// 删除所有商品
		jdbcDao.delete(sql1);
		jdbcDao.delete(sql2);
		jdbcDao.delete(sql);
	}

	public void delGoodsFenlei(WcGoodsFenlei fenlei) {
		// TODO Auto-generated method stub
		log.error("删除2");
		for (int i = 0; i < fenlei.getWgfIds().length; i++) {
			log.error("删除3:" + i);
			String wfgId = fenlei.getWgfIds()[i];
			List<Map<String, Object>> listMap = this.jdbcDao
					.queryForList("select WGS_ID from WC_GOODS where WGS_WGF_ID = "
							+ wfgId);
			List<String> strList = new ArrayList<String>();
			for (Map<String, Object> map : listMap) {
				strList.add(Integer.toString((Integer) map.get("WGS_ID")));
			}
			String[] wgsIds = strList.toArray(new String[0]);
			WcGoods goods = new WcGoods();
			goods.setWgsIds(wgsIds);
			this.delGoods(goods);
		}
		String wfgIdstr = StringUtils.arrayToCommaDelimitedString(fenlei
				.getWgfIds());
		String sql4 = "delete from WC_GOODS_FENLEI where WGF_ID in ("
				+ wfgIdstr + ")";// 所选分类删除
		jdbcDao.delete(sql4);
	}

	public void addGoodsIn(WcGoodsIn goodsIn) {
		// TODO Auto-generated method stub
		// 添加一条入库记录
		hibernateDao.add(goodsIn);
		// 将对应商品的库存量进行增加
		WcGoods goods = this.getGoodsById(goodsIn.getWgiWgsId());
		Double num = goods.getWgsKucun() + goodsIn.getWgiInNum();
		goods.setWgsKucun(num);
		hibernateDao.update(goodsIn);
	}

	public void addGoodsOut(WcGoodsOut goodsOut) {
		// TODO 出库操作
		hibernateDao.add(goodsOut);
		hibernateDao.flush();
		Double outNum = goodsOut.getWgoOutNum();
		while (outNum > 0) {
			outNum = updGoodsInByOut(goodsOut, outNum);
		};
	}

	public WcGoodsIn getGoodsInById(Integer wgiId) {
		// TODO Auto-generated method stub
		return hibernateDao.get(WcGoodsIn.class, wgiId);
	}

	public Double updGoodsInByOut(WcGoodsOut goodsOut, Double num) {
		// 找出所有库存量大于零 且商品编号相等的 按照入库时间从小到大排列的 最前面的入库记录 更新出库量
		String sql = 
			"	select a.WGI_ID from WC_GOODS_IN a " +
			"	where a.WGI_WGS_ID = ? " +
			"	and a.WGI_WCS_ID = ? and a.WGI_SURPLUS_NUM > 0 " +
			"   order by a.WGI_ID asc,a.WGI_IN_TIME asc limit 1 ";
		List<Map<String, Object>> list = jdbcDao
				.queryForList(sql, new Object[] { goodsOut.getWgoWgsId(),
						goodsOut.getWgoWcsId() });
		if (list.size() > 0) {
			Map<String, Object> map = list.get(0);
			Integer wgiId = (Integer) map.get("WGI_ID");
			WcGoodsIn goodsIn = this.getGoodsInById(wgiId);
			Double inNum = (goodsIn.getWgiSurplusNum() - num) > 0 ? (goodsIn
					.getWgiSurplusNum() - num) : 0;
			String sql1 = "update  WC_GOODS_IN set WGI_SURPLUS_NUM = ? where WGI_ID = ?";
			jdbcDao.update(sql1, new Object[] { inNum, wgiId });
			String sql2 = " insert into WC_GOODS_IN_OUT (WIO_WGO_ID,WIO_WGI_ID,WIO_NUMBER,WIO_REGISTOR,WIO_REGISTDATE) values (?,?,?,?,?)";
			jdbcDao.add(sql2, new Object[]{goodsOut.getWgoId(),goodsIn.getWgiId(),num,goodsOut.getWgoRegistor(),DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss")});
			num = num - goodsIn.getWgiSurplusNum();
		}
		return num;
	}

	public Page queryGoodsIn(WcGoodsIn goodsIn) {
		// TODO Auto-generated method stub
		String wgiWgfId_Q=goodsIn.getWgiWgfId_Q();
		String wgiWgsId_Q=goodsIn.getWgiWgsId_Q();
		String wgiInNumMin_Q=goodsIn.getWgiInNumMin_Q();
		String wgiInNumMax_Q=goodsIn.getWgiInNumMax_Q();
		String wgiSurplusNumMin_Q=goodsIn.getWgiSurplusNumMin_Q();
		String wgiSurplusNumMax_Q=goodsIn.getWgiSurplusNumMax_Q();
		String wgiLoc_Q=goodsIn.getWgiLoc_Q();
		String wgiInTimeStart_Q=goodsIn.getWgiInTimeStart_Q();
		String wgiInTimeEnd_Q=goodsIn.getWgiInTimeEnd_Q();
		StringBuilder sql = new StringBuilder("select " + " a.WGI_ID,"
				+ " a.WGI_WGS_ID," + " a.WGI_WCS_ID," + " a.WGI_IN_TIME,"
				+ " a.WGI_IN_NUM," + " a.WGI_IN_PRICE," + " a.WGI_IN_ADMIN,"
				+ " a.WGI_SURPLUS_NUM," + " a.WGI_LOC," + " a.WGI_DESC,"
				+ " a.WGI_STATUS," + " a.WGI_REGISTOR," + " a.WGI_REGISTDATE,"
				+ " c.WGS_NAME," + " b.WGF_NAME, " + " d.WSA_NAME as inName,"
				+ " e.WSA_NAME as regName");
		StringBuilder sqlCnt = new StringBuilder(" select count(*)  ");
		StringBuilder sqlConf = new StringBuilder(" from WC_GOODS_IN a "
				+ " left join WC_GOODS c on c.WGS_ID = a.WGI_WGS_ID  "
				+ " left join WC_GOODS_FENLEI b on c.WGS_WGF_ID = b.WGF_ID "
				+ " left join WC_SHOP_ADMIN d on d.WSA_ID = a.WGI_IN_ADMIN"
				+ " left join WC_SHOP_ADMIN e on e.WSA_ID = a.WGI_REGISTOR"
				+ " where 1=1 and a.WGI_WCS_ID = ? ");
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(goodsIn.getWgiWcsId());
		if(wgiWgfId_Q!=null && wgiWgfId_Q.length()>0)
		{
			sqlConf.append(" and b.WGF_ID = ? ");
			paraList.add(wgiWgfId_Q);
		}
		if(wgiWgsId_Q!=null && wgiWgsId_Q.length()>0)
		{
			sqlConf.append(" and c.WGS_ID = ? ");
			paraList.add(wgiWgsId_Q);
		}
		if(wgiInNumMin_Q!=null && wgiInNumMin_Q.length()>0)
		{
			sqlConf.append(" and a.WGI_IN_NUM >= ? ");
			paraList.add(wgiInNumMin_Q);
		}
		if(wgiInNumMax_Q!=null && wgiInNumMax_Q.length()>0)
		{
			sqlConf.append(" and a.WGI_IN_NUM <= ? ");
			paraList.add(wgiInNumMax_Q);
		}
		if(wgiSurplusNumMin_Q!=null && wgiSurplusNumMin_Q.length()>0)
		{
			sqlConf.append(" and a.WGI_SURPLUS_NUM >= ? ");
			paraList.add(wgiSurplusNumMin_Q);
		}
		if(wgiSurplusNumMax_Q!=null && wgiSurplusNumMax_Q.length()>0)
		{
			sqlConf.append(" and a.WGI_SURPLUS_NUM <= ? ");
			paraList.add(wgiSurplusNumMax_Q);
		}
		if(wgiLoc_Q!=null && wgiLoc_Q.length()>0)
		{
			sqlConf.append(" and a.WGI_LOC = ?");
			paraList.add(wgiLoc_Q);
		}
		if(wgiInTimeStart_Q!=null && wgiInTimeStart_Q.length()>0)
		{
			sqlConf.append(" and a.WGI_IN_TIME >= ? ");
			paraList.add(wgiInTimeStart_Q);
		}
		if(wgiInTimeEnd_Q!=null && wgiInTimeEnd_Q.length()>0)
		{
			sqlConf.append(" and a.WGI_IN_TIME <= ? ");
			paraList.add(wgiInTimeEnd_Q);
		}
		
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		Page page = new Page(sql.toString(), sqlCnt.toString(), goodsIn
				.getCurrentPage(), goodsIn.getPageSize(), paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}

	public Page queryGoodsOut(WcGoodsOut goodsOut) {
		// TODO Auto-generated method stub
		String wgoWgfId_Q = goodsOut.getWgoWgfId_Q();
		String wgoWgsId_Q = goodsOut.getWgoWgsId_Q();
		String wgoType_Q = goodsOut.getWgoType_Q();

		StringBuilder sql = new StringBuilder("select " + " a.WGO_ID,"
				+ " a.WGO_WGS_ID," + " a.WGO_WCS_ID," + " a.WGO_TYPE,"
				+ " a.WGO_OUT_TIME," + " a.WGO_OUT_NUM," + " a.WGO_OUT_PRICE,"
				+ " a.WGO_OUT_ADMIN," + " a.WGO_DESC," + " a.WGO_STATUS,"
				+ " a.WGO_REGISTOR," + " a.WGO_REGISTDATE," + " c.WGS_NAME,"
				+ " b.WGF_NAME," + " d.WSA_NAME as outer1 , "
				+ " e.WSA_NAME as registor," + " f.WMB_NAME ");
		StringBuilder sqlCnt = new StringBuilder(" select count(*)  ");

		StringBuilder sqlConf = new StringBuilder(" from WC_GOODS_OUT a "
				+ " left join WC_GOODS c on c.WGS_ID = a.WGO_WGS_ID  "
				+ " left join WC_GOODS_FENLEI b on c.WGS_WGF_ID = b.WGF_ID "
				+ " left join WC_SHOP_ADMIN d on d.WSA_ID = a.WGO_OUT_ADMIN "
				+ " left join WC_SHOP_ADMIN e on e.WSA_ID = a.WGO_REGISTOR "
				+ " left join LZ_WEI_MEMBER f on f.WMB_ID = a.WGO_WMB_ID "
				+ " where 1=1 and a.WGO_WCS_ID = ? ");
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(goodsOut.getWgoWcsId());
		if (wgoWgfId_Q != null && wgoWgfId_Q.length() > 0) {
			sqlConf.append(" and b.WGF_ID = ? ");
			paraList.add(wgoWgfId_Q);
		}
		if (wgoWgsId_Q != null && wgoWgsId_Q.length() > 0) {
			sqlConf.append(" and a.WGO_WGS_ID = ? ");
			paraList.add(wgoWgsId_Q);
		}
		if (wgoType_Q != null && wgoType_Q.length() > 0) {
			sqlConf.append(" and a.WGO_TYPE = ? ");
			paraList.add(wgoType_Q);
		}
		sql.append(sqlConf);
		sqlCnt.append(sqlConf);
		Page page = new Page(sql.toString(), sqlCnt.toString(), goodsOut
				.getCurrentPage(), goodsOut.getPageSize(), paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}

	public boolean chkChukuEnough(WcGoodsOut goodsOut) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(
				"select sum(WGI_SURPLUS_NUM) from WC_GOODS_IN where WGI_WGS_ID = ? and WGI_WCS_ID = ? ");
		return goodsOut.getWgoOutNum() <= jdbcDao.queryForDouble(
				sql.toString(), new Object[] { goodsOut.getWgoWgsId(),
						goodsOut.getWgoWcsId() });
	}

	public List<WcGoods> queryGoodsByShop(Integer wcsId) {
		// TODO Auto-generated method stub
		String hql = new String(
				" select goods from WcGoods goods where goods.wgsWcsId = ? ");
		return hibernateDao.query(hql, new Object[] { wcsId });
	}

	public List<WcGoods> queryGoodsListByFenlei(String wgfId) {
		// TODO Auto-generated method stub
		String hql = new String(
				" select goods from WcGoods goods where goods.wgsWgfId = ? ");
		return hibernateDao.query(hql, new Object[] { Integer.parseInt(wgfId) });
	}

	public List<Map<String, Object>> queryGoodsByFenlei(Integer wfgId) {
		// TODO Auto-generated method stub
		String sql = "select WGS_ID,WGS_NAME from WC_GOODS where WGS_WGF_ID = ?";

		return jdbcDao.queryForList(sql, new Object[] { wfgId });
	}

	public List<WcGoodsFenlei> queryGoodsFenleiByShop(Integer wcsId) {
		// TODO Auto-generated method stub
		String hql = new String(
				" select fenlei from WcGoodsFenlei fenlei where fenlei.wgfWcsId = ? ");
		return hibernateDao.query(hql, new Object[] { wcsId });
	}

}

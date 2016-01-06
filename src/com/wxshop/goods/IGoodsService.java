package com.wxshop.goods;

import java.util.List;
import java.util.Map;

import com.wxshop.util.Page;

public interface IGoodsService 
{
	public Page queryGoodsFeilei(WcGoodsFenlei feilei);
	public void addGoodsFeilei(WcGoodsFenlei feilei);
	public WcGoodsFenlei getFeileiById(Integer id);
	public void updGoodsFenlei(WcGoodsFenlei feilei);
	public void addGoodsFenlei(WcGoodsFenlei fenlei);
	public Page queryGoods(WcGoods goods);
	public void addGoods(WcGoods goods);
	public WcGoods getGoodsById(Integer wgsId);
	public void updGoods(WcGoods goods);
	public void delGoods(WcGoods goods);
	public void delGoodsFenlei(WcGoodsFenlei fenlei);
	public void addGoodsIn(WcGoodsIn goodsIn);
	public void addGoodsOut(WcGoodsOut goodsOut);
	public WcGoodsIn getGoodsInById(Integer wgiId);
	public Page queryGoodsIn(WcGoodsIn goodsIn);
	public Page queryGoodsOut(WcGoodsOut goodsOut);
	public boolean chkChukuEnough(WcGoodsOut goodsOut);
	public List<WcGoods> queryGoodsByShop(Integer wcsId);
	
	public List<WcGoodsFenlei> queryGoodsFenleiByShop(Integer wcsId);
	public List<Map<String,Object>> queryGoodsByFenlei(Integer wfgId);
	public List<WcGoods> queryGoodsListByFenlei(String wgfId);
	
}

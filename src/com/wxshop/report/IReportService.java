package com.wxshop.report;

import com.wxshop.goods.WcGoodsOut;
import com.wxshop.util.Page;

public interface IReportService {
	public Page queryGoodsLirun(WcGoodsOut goodsOut);
}

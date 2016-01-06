package com.wxshop.report;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxshop.goods.IGoodsService;
import com.wxshop.goods.WcGoods;
import com.wxshop.goods.WcGoodsFenlei;
import com.wxshop.goods.WcGoodsOut;
import com.wxshop.util.SysConstant;
import com.wxshop.website.WcWebsite;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private IReportService reportService;
	
	@Autowired
	private IGoodsService goodsService;

	private static Logger log = Logger.getLogger(ReportController.class);

	@RequestMapping(value = "/queryGoodsLirun")
	public String queryGoodsLirun(@ModelAttribute("command") WcGoodsOut goodsOut, HttpSession session, Model model) {
		WcWebsite webSite = (WcWebsite)session.getAttribute(SysConstant.WEBSITE_INFO);
		List<WcGoodsFenlei> listFenlei = goodsService.queryGoodsFenleiByShop(webSite.getWcsId());
		if(goodsOut.getWgoWgfId_Q()!=null&&goodsOut.getWgoWgfId_Q().length()>0)
		{
			List<WcGoods> listGoods = goodsService.queryGoodsListByFenlei(goodsOut.getWgoWgfId_Q());
			model.addAttribute("goods", listGoods);
		}
		goodsOut.setWgoWcsId(webSite.getWcsId());
		model.addAttribute("goodsFenlei", listFenlei);
		model.addAttribute(SysConstant.PAGE_RESULT, reportService.queryGoodsLirun(goodsOut));
		return "/report/queryGoodsLirun";
	}

}

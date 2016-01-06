package com.wxshop.goods;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxshop.sys.IShopAdminService;
import com.wxshop.sys.WcShopAdmin;
import com.wxshop.util.DateUtil;
import com.wxshop.util.StringUtil;
import com.wxshop.util.SysConstant;
import com.wxshop.website.WcWebsite;

@Controller
@RequestMapping("/goods")
public class GoodsController 
{
	@Autowired
	private IGoodsService goodsService;
	
	@Autowired
	private IShopAdminService adminservice;
	
	private static Logger log = Logger.getLogger(GoodsController.class);
	
	/**
	 * 商品分类管理
	 * */
	@RequestMapping(value = "/queryGoodsFenlei")
	public String queryGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei feilei,HttpSession session,Model model)
	{
		WcWebsite website = (WcWebsite)session.getAttribute(SysConstant.WEBSITE_INFO);
		feilei.setWgfWcsId(website.getWcsId());
		model.addAttribute(SysConstant.PAGE_RESULT, goodsService.queryGoodsFeilei(feilei));
		return "/goods/queryGoodsFenlei";
	}
	
	@RequestMapping(value = "/toAddGoodsFenlei/{wcsId}")
	public String toAddGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei feilei,@PathVariable Integer wcsId,Model model)
	{
		feilei.setWgfWcsId(wcsId);
		return "/goods/addGoodsFenlei";
	}

	@RequestMapping(value = "/addGoodsFenlei")
	public String addGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei fenlei,HttpSession session,RedirectAttributes redirectAttribute)
	{
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		fenlei.setWgfRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		fenlei.setWgfStatus("1000");
		fenlei.setWgfRegistor(admin.getWsaId());
		goodsService.addGoodsFenlei(fenlei);
		redirectAttribute.addFlashAttribute("alertMsg", "添加商品分类成功!");
		return "redirect:/goods/queryGoodsFenlei";
	}
	
	@RequestMapping(value = "/toUpdGoodsFenlei" ,method = RequestMethod.POST)
	public String toUpdGoodsFenlei(WcGoodsFenlei Fenlei_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcGoodsFenlei fenlei = goodsService.getFeileiById(Fenlei_Q.getWgfId());
		StringUtil.copyProperties(Fenlei_Q, fenlei);
		model.addAttribute("command", fenlei);
		return "/goods/updGoodsFenlei";
	} 
	
	@RequestMapping("/updGoodsFenlei")
	public String updGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei Fenlei,HttpServletRequest request,RedirectAttributes redirectAttribute) throws IllegalArgumentException, IllegalAccessException
	{

		goodsService.updGoodsFenlei(Fenlei);
		redirectAttribute.addFlashAttribute("msgCode", "2");
		redirectAttribute.addFlashAttribute("alertMsg", "站点修改成功");
		redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/goods/queryGoodsFenlei", Fenlei));
		return "redirect:/admin/toMsg";
	}
	

	@RequestMapping(value = "/delGoodsFenlei/{wcsId}")
	public String delGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei fenlei,@PathVariable Integer wcsId,HttpServletRequest request,RedirectAttributes redirectAttribute) throws IllegalArgumentException, IllegalAccessException
	{
		fenlei.setWgfWcsId(wcsId);
		goodsService.delGoodsFenlei(fenlei);
		redirectAttribute.addFlashAttribute("msgCode", "2");
		redirectAttribute.addFlashAttribute("alertMsg", "商品分类删除成功");
		redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/goods/queryGoodsFenlei", fenlei));
		return "redirect:/admin/toMsg";
	}
	
	@RequestMapping(value = "/queryGoods/{wfgId}")
	public String queryGoods(@ModelAttribute("command") WcGoods goods,@PathVariable Integer wfgId,Model model)
	{
		goods.setWgsWgfId(wfgId);
		WcGoodsFenlei fenlei = goodsService.getFeileiById(wfgId);
		model.addAttribute("fenlei", fenlei);
		model.addAttribute(SysConstant.PAGE_RESULT, goodsService.queryGoods(goods));
		return "/goods/queryGoods";
	}
	
	@RequestMapping(value = "/getGoodsByFenlei/{wfgId}")
	@ResponseBody
	public List<Map<String,Object>> getGoodsByFenlei(@PathVariable Integer wfgId,Model model)
	{
		List<Map<String,Object>> resList =goodsService.queryGoodsByFenlei(wfgId);
		return resList;
	}
	
	@RequestMapping(value = "/toAddGoods/{wgfId}")
	public String toAddGoods(@ModelAttribute("command") WcGoods goods,@PathVariable Integer wgfId,Model model)
	{
		WcGoodsFenlei fenlei = goodsService.getFeileiById(wgfId);
		goods.setWgsWgfId(wgfId);
		goods.setWgsWcsId(fenlei.getWgfWcsId());
		goods.setWgfName(fenlei.getWgfName());
		return "/goods/addGoods";
	}
	
	@RequestMapping(value = "/addGoods")
	public String addGoodsFenlei(@ModelAttribute("command") WcGoods goods,HttpSession session,RedirectAttributes redirectAttribute)
	{
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		goods.setWgsKucun(0.0);
		goods.setWgsRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		goods.setWgsStatus("1000");
		goods.setWgsRegistor(admin.getWsaId());
		goodsService.addGoods(goods);
		redirectAttribute.addFlashAttribute("alertMsg", "添加商品成功!");
		return "redirect:/goods/queryGoods/"+goods.getWgsWgfId();
	}

	@RequestMapping(value = "/toUpdGoods" ,method = RequestMethod.POST)
	public String toUpdGoods( WcGoods goods_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcGoods goods = goodsService.getGoodsById(goods_Q.getWgsId());
		WcGoodsFenlei fenlei = goodsService.getFeileiById(goods.getWgsWgfId());
		StringUtil.copyProperties(goods_Q, goods);
		model.addAttribute("fenlei", fenlei);
		model.addAttribute("command", goods);
		return "/goods/updGoods";
	} 
	
	@RequestMapping(value="/updGoods",method = RequestMethod.POST)
	public String updGoods(@ModelAttribute("command") WcGoods goods,HttpServletRequest request,RedirectAttributes redirectAttribute) throws IllegalArgumentException, IllegalAccessException
	{
		goodsService.updGoods(goods);
		redirectAttribute.addFlashAttribute("msgCode", "2");
		redirectAttribute.addFlashAttribute("alertMsg", "商品修改成功");
		redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/goods/queryGoods/"+goods.getWgsWgfId(), goods));
		return "redirect:/admin/toMsg";
	}
	
	@RequestMapping(value = "/delGoods/{wgfId}")
	public String delGoods(@ModelAttribute("command") WcGoods goods,@PathVariable Integer wgfId,HttpServletRequest request,RedirectAttributes redirectAttribute) throws IllegalArgumentException, IllegalAccessException
	{
		goods.setWgsWgfId(wgfId);
		goodsService.delGoods(goods);
		redirectAttribute.addFlashAttribute("msgCode", "2");
		redirectAttribute.addFlashAttribute("alertMsg", "商品删除成功");
		redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/goods/queryGoods/"+goods.getWgsWgfId(), goods));
		return "redirect:/admin/toMsg";
	}

	@RequestMapping(value = "/toInGoods" ,method = RequestMethod.POST)
	public String toInGoods(WcGoods goods_Q,Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcGoods goods = goodsService.getGoodsById(goods_Q.getWgsId());
		WcGoodsIn goodsIn = new WcGoodsIn();
		goodsIn.setWgiInTime(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		goodsIn.setWgiWgsId(goods.getWgsId());
		goodsIn.setWgiWcsId(goods.getWgsWcsId());
		model.addAttribute("goods", goods);
		model.addAttribute("command", goodsIn);
		return "/goods/addInGoods";
	} 
	
	@RequestMapping(value = "/addInGoods")
	public String addInGoods(@ModelAttribute("command") WcGoodsIn goodsIn,HttpSession session,RedirectAttributes redirectAttribute)
	{
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		goodsIn.setWgiInAdmin(admin.getWsaId());
		goodsIn.setWgiSurplusNum(goodsIn.getWgiInNum());
		goodsIn.setWgiStatus("1000");
		goodsIn.setWgiRegistor(admin.getWsaId());
		goodsIn.setWgiRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		goodsService.addGoodsIn(goodsIn);
		redirectAttribute.addFlashAttribute("alertMsg", "入库成功!");
		WcGoods goods = goodsService.getGoodsById(goodsIn.getWgiWgsId());
		return "redirect:/goods/queryGoods/"+goods.getWgsWgfId();
	}

	@RequestMapping(value = "/toOutGoods" ,method = RequestMethod.POST)
	public String toOutGoods(WcGoods goods_Q,Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcGoods goods = goodsService.getGoodsById(goods_Q.getWgsId());
		WcGoodsOut goodsOut = new WcGoodsOut();
		goodsOut.setWgoOutTime(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		goodsOut.setWgoWgsId(goods.getWgsId());
		goodsOut.setWgoWcsId(goods.getWgsWcsId());
		goodsOut.setWgoType(SysConstant.chuku);
		goodsOut.setWgoWmbId(0);//先默认都是0
		model.addAttribute("goods", goods);
		model.addAttribute("command", goodsOut);
		return "/goods/addOutGoods";
	} 
	
	
	
	@RequestMapping(value = "/toBadGoods" )
	public String toBadGoods(WcGoods goods_Q,Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcGoods goods = goodsService.getGoodsById(goods_Q.getWgsId());
		WcGoodsOut goodsOut = new WcGoodsOut();
		goodsOut.setWgoOutTime(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		goodsOut.setWgoWgsId(goods.getWgsId());
		goodsOut.setWgoWcsId(goods.getWgsWcsId());
		goodsOut.setWgoType(SysConstant.huaizhang);
		goodsOut.setWgoWmbId(0);//先默认都是0
		model.addAttribute("goods", goods);
		model.addAttribute("command", goodsOut);
		return "/goods/addBadGoods";
	} 
	
	
	@RequestMapping(value = "/addOutGoods")
	public String addOutGoods(@ModelAttribute("command") WcGoodsOut goodsOut,HttpSession session,RedirectAttributes redirectAttribute)
	{
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		WcGoods goods = goodsService.getGoodsById(goodsOut.getWgoWgsId());
		//检查销售 出库 坏账的量是否大于库存量
		if(!goodsService.chkChukuEnough(goodsOut))
		{
			redirectAttribute.addFlashAttribute("alertMsg", "出库量大于库存量，请重新核实出库量!");
			return "redirect:/goods/queryGoods/"+goods.getWgsWgfId();
			
		}
		goodsOut.setWgoOutAdmin(admin.getWsaId());
		goodsOut.setWgoStatus("1000");
		goodsOut.setWgoRegistor(admin.getWsaId());
		goodsOut.setWgoRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		//如果出库 就必须关联会员
		
		goodsService.addGoodsOut(goodsOut);
		//找到对应的商品 将商品的库存量做相应的更新
		
		if(goods.getWgsKucun()-goodsOut.getWgoOutNum()<=0)
		{
			goods.setWgsKucun(0.0);
		}else {
			goods.setWgsKucun(goods.getWgsKucun()-goodsOut.getWgoOutNum());
		}
		goodsService.updGoods(goods);
		String msg = "";
		if(goodsOut.getWgoType().equals(SysConstant.huaizhang))
		{
			msg = "坏账添加完成";
		}
		else if(goodsOut.getWgoType().equals(SysConstant.xiaoshou))
		{
			
			msg = "销售完成!";
		}
		else
		{
			
			msg = "出库成功!";
		}
		redirectAttribute.addFlashAttribute("alertMsg", msg);
		return "redirect:/goods/queryGoods/"+goods.getWgsWgfId();
	}
	
	@RequestMapping(value= "/queryGoodsIn")
	public String queryGoodsIn(@ModelAttribute("command") WcGoodsIn goodsIn,HttpSession session,Model model)
	{
		WcWebsite webSite = (WcWebsite)session.getAttribute(SysConstant.WEBSITE_INFO);
		List<WcGoodsFenlei> listFenlei = goodsService.queryGoodsFenleiByShop(webSite.getWcsId());
		if(goodsIn.getWgiWgfId_Q()!=null && goodsIn.getWgiWgfId_Q().length()>0)
		{
			List<WcGoods> listGoods = goodsService.queryGoodsListByFenlei(goodsIn.getWgiWgfId_Q());
			model.addAttribute("goods", listGoods);
		}
		goodsIn.setWgiWcsId(webSite.getWcsId());
		model.addAttribute("goodsFenlei", listFenlei);
		model.addAttribute(SysConstant.PAGE_RESULT, goodsService.queryGoodsIn(goodsIn));
		return "/goods/queryGoodsIn";
	}

	@RequestMapping(value= "/queryGoodsOut")
	public String queryGoodsOut(@ModelAttribute("command") WcGoodsOut goodsOut,HttpSession session,Model model)
	{
		WcWebsite webSite = (WcWebsite)session.getAttribute(SysConstant.WEBSITE_INFO);
		List<WcGoodsFenlei> listFenlei = goodsService.queryGoodsFenleiByShop(webSite.getWcsId());
		if(goodsOut.getWgoWgfId_Q()!=null&&goodsOut.getWgoWgfId_Q().length()>0)
		{
			List<WcGoods> listGoods = goodsService.queryGoodsListByFenlei(goodsOut.getWgoWgfId_Q());
			model.addAttribute("goods", listGoods);
		}
		goodsOut.setWgoWcsId(webSite.getWcsId());
		model.addAttribute("goodsFenlei", listFenlei);
		model.addAttribute(SysConstant.PAGE_RESULT, goodsService.queryGoodsOut(goodsOut));
		return "/goods/queryGoodsOut";
	}
	

	@RequestMapping(value = "/toSale")
	public String toSale(WcGoods goods_Q,HttpSession session,Model model) throws IllegalArgumentException, IllegalAccessException
	{
		//先把该商家所有的商品查出来

		WcWebsite shop = (WcWebsite)session.getAttribute(SysConstant.WEBSITE_INFO);
		List<WcGoodsFenlei> listFenlei = goodsService.queryGoodsFenleiByShop(shop.getWcsId());
		//List<WcGoods> listGoods = goodsService.queryGoodsByShop(shop.getWcsId());
		WcGoodsOut goodsOut = new WcGoodsOut();
		goodsOut.setWgoOutTime(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		goodsOut.setWgoWcsId(shop.getWcsId());
		goodsOut.setWgoType(SysConstant.xiaoshou);
		goodsOut.setWgoWmbId(0);//先默认都是0
		//model.addAttribute("goods", listGoods);
		model.addAttribute("goodsFenlei", listFenlei);
		model.addAttribute("command", goodsOut);
		return "/goods/addSaleGoods";
	} 
	
	
	@RequestMapping("/getKucunByGoods/{goodsId}")
	@ResponseBody
	public Map<String,String> getKucunByGoods(@PathVariable Integer goodsId ,Model model)
	{
		Map<String ,String> obj = new HashMap<String,String>();
		WcGoods goods = goodsService.getGoodsById(goodsId);
		obj.put("kucun", goods.getWgsKucun().toString());
		obj.put("wgsBiaozhunjia", goods.getWgsBzPrice().toString());
		return obj;
		
	}
	
}

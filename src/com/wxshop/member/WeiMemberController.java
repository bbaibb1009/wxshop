package com.wxshop.member;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxshop.sys.WcShopAdmin;
import com.wxshop.util.StringUtil;
import com.wxshop.util.SysConstant;
import com.wxshop.website.IWebsiteService;
import com.wxshop.website.WcWebsite;


@Controller
@RequestMapping("/member")
public class WeiMemberController 
{
	@Autowired
	private IWeiMemberService weimemberService;
	
	@Autowired
	private IWebsiteService websiteService;
	
	private static Logger log = Logger.getLogger(WeiMemberController.class);
	
	@RequestMapping(value = "/queryWeiMember")
	public String queryTest(@ModelAttribute("command") LzWeiMember weimember,HttpSession session, Model model)
	{
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		log.error(admin.getWsaId());
		weimember.setShopAdminId_Q(admin.getWsaId());
		model.addAttribute(SysConstant.PAGE_RESULT, weimemberService.queryWeiMember(weimember));
		return "/member/queryMember";
	}

	@RequestMapping("/toMemberIndex/{memberId}")
	public String toMemberIndex(@ModelAttribute("command") LzWeiMember weimember,@PathVariable Integer memberId ,Model model)
	{
		weimember=weimemberService.getWeiMemberByid(memberId);
		model.addAttribute("command", weimember);
		return "/weimember/index";
	}
	
	
	@RequestMapping("/getMemNameList")
	@ResponseBody
	public List<Map<String, Object>> getMemNameList(HttpSession session)
	{
		WcWebsite wcs = (WcWebsite)session.getAttribute(SysConstant.WEBSITE_INFO);
		return weimemberService.getMemNameAllByWei(wcs.getWcsId());
	}
	
	@RequestMapping("/selMember/{wcsId}")
	public String selMember(@ModelAttribute("command") LzWeiMember weimember,@PathVariable Integer wcsId,Model model )
	{
		weimember.setWmbWcsId_Q(wcsId);
		model.addAttribute(SysConstant.PAGE_RESULT, weimemberService.queryWeiMemberByWcsId(weimember));
		return "/member/selmember";
	}
	
	
	@RequestMapping(value ="/toUpdMember",method = RequestMethod.POST)
	public String toUpdRole(LzWeiMember weimember_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		LzWeiMember weimember = weimemberService.getWeiMemberByid(weimember_Q.getWmbId());
		StringUtil.copyProperties(weimember_Q, weimember);
		model.addAttribute("command", weimember);
		return "/member/updMember";
	} 
	
	@RequestMapping("/updMember")
	public String updMember(@ModelAttribute("command") LzWeiMember weimember,HttpServletRequest request,RedirectAttributes redirectAttribute) throws IllegalArgumentException, IllegalAccessException
	{
		weimemberService.updateWeiMember(weimember);
		redirectAttribute.addFlashAttribute("msgCode", "2");
		redirectAttribute.addFlashAttribute("alertMsg", "修改会员资料成功");
		redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/member/queryWeiMember", weimember));
		return "redirect:/admin/toMsg";
	}
	
	
	
	@RequestMapping(value ="/delMember", method = RequestMethod.POST)
	public String delMember(LzWeiMember member, HttpServletRequest request, RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, IOException
	{
		weimemberService.delWeiMember(member.getWmbIds());
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "会员删除成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/member/queryWeiMember", member));
		return "redirect:/admin/toMsg";
	}
}

package com.wxshop.shop.dept;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxshop.common.IMemcachedService;
import com.wxshop.sys.IShopAdminService;
import com.wxshop.sys.IShopRoleService;
import com.wxshop.sys.WcShopAdmin;
import com.wxshop.util.DateUtil;
import com.wxshop.util.StringUtil;
import com.wxshop.util.SysConstant;

@Controller
@RequestMapping("/shop/dept")
public class ShopDeptController 
{
	@Autowired
	private IShopDeptService deptService;
	
	@Autowired
	private IShopRoleService roleService;
	
	@Autowired
	private IShopAdminService adminService;
	
	
	@Autowired 
	private IMemcachedService memcachedservice;
		
	@RequestMapping("/queryDept")
	public String queryDept(@ModelAttribute("command") WcShopDept dept, HttpServletResponse response, HttpSession session, Model model)
	{
		//先把部门查出来
		model.addAttribute(SysConstant.PAGE_RESULT,deptService.queryShopDept(dept));
		return "/dept/queryShopDept";
	}
	
	@RequestMapping("/toAddShopDept")
	public String toAddShopDept(@ModelAttribute("command") WcShopDept dept,Model mode)
	{
		//跳转到添加部门
		return "/dept/addShopDept";
	}

	@RequestMapping(value = "/addDept", method = RequestMethod.POST)
	public String addDept(WcShopDept dept, HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException
	{
		//跳转到添加菜单
		WcShopAdmin admin = (WcShopAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		dept.setWdpRegistor(admin.getWsaId());
		dept.setWdpStatus("1000");
		dept.setWdpRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		deptService.addDept(dept);
		redirectAttributes.addFlashAttribute("success", "团队添加成功!");
		return "redirect:/shop/dept/queryDept";
	}
	
	
	@RequestMapping(value = "/delShopDept", method = RequestMethod.POST)
	public String delAccount(WcShopDept dept, HttpServletRequest request,RedirectAttributes redirectAttributes)	throws IllegalArgumentException, IllegalAccessException {
		deptService.delDept(dept.getWdpIds());
		redirectAttributes.addFlashAttribute("success", "团队删除成功!");
		return "redirect:/shop/dept/queryDept";
	}
	
	/**
	 * @功能介绍 跳转至修改菜单
	 * 
	 * */
	@RequestMapping(value ="/toUpdShopDept",method = RequestMethod.POST)
	public String toUpdShopMenu(WcShopDept dept_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcShopDept dept = deptService.getShopDeptById(dept_Q.getWdpId());
		StringUtil.copyProperties(dept_Q, dept);
		
		WcShopAdmin admin = adminService.getShopAdminById(dept.getWdpAdminId());
		dept.setWdpAdminName(admin.getWsaName());
		model.addAttribute("command", dept);
		return "/dept/updShopDept";
	} 
	
	/**
	 * @功能介绍 修改团队保存
	 * */
	@RequestMapping(value="/updShopDept",method = RequestMethod.POST)
	public String updShopDept(WcShopDept dept_Q, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IllegalArgumentException, IllegalAccessException 
	{
		deptService.updShopDept(dept_Q);
		redirectAttributes.addFlashAttribute("success", "团队修改成功!");
		return "redirect:/shop/dept/queryDept";
	}
	

	/**
	 * @author yChoco
	 * created on Sep 13, 2013 10:29:32 AM
	 * @param disabled 若是0则无操作，若是1则全部置灰，若是2则disabledList中的置灰
	 * @param disabledList 置灰的菜单id列表
	 * @param checkedList 默认选中的菜单id列表
	 * @return
	 */
	@RequestMapping("/getDeptTreeForAdmin/{adminId}")
	@ResponseBody
	public List<Map<String, Object>> getDeptTree(@PathVariable Integer adminId)
	{
		List<Map<String, Object>> deptList = memcachedservice.getSingleDeptAll();
		WcShopAdmin admin = adminService.getShopAdminById(adminId);
		if(admin!=null)
		{
			
		
			WcShopDept dept = deptService.getShopDeptById(admin.getWsaDept());
			for(int i =0;i<deptList.size();i++)
			{
				Map<String, Object> map = deptList.get(i);
				if(admin.getWsaDept().compareTo((Integer)map.get("id"))==0)
				{
					map.put("checked", true);
					deptList.set(i, map);
				}
				else
				{
					map.put("checked", false);
					deptList.set(i, map);
				}
			}
		}
		return deptList;
	}
	
	
	
	@RequestMapping("/getDeptName1List")
	@ResponseBody
	public List<Map<String, Object>> getDeptName1List() 
	{
		return memcachedservice.getDeptName1All();
	}
	
	@RequestMapping("/getDeptTreeForRole/{roleId}")
	@ResponseBody
	public List<Map<String, Object>> getDeptTreeForRole(@PathVariable Integer roleId)
	{
		List<String> checkedList = null;
		if( roleId != -1 )
		{
			checkedList = roleService.queryShopRoleAdminsById(roleId);
		}
		
		return getDeptTree("3", checkedList);
	}
	
	private List<Map<String, Object>> getDeptTree(String deptLevel, List<String> checkedList)
	{
		List<Map<String, Object>> deptList = memcachedservice.getDeptAll();
		for( Map<String, Object> map : deptList )
		{
			if( map.get("deptLevel").toString().equals(deptLevel) )
			{
				if( checkedList != null && checkedList.contains(map.get("id").toString()) )
				{
					map.put("checked", true);
				}
			}
			else
			{
				map.put("nocheck", true);
			}
		}
		
		return deptList;
	}
}

package com.wxshop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wxshop.sys.WcShopAdmin;
import com.wxshop.util.SysConstant;

public class SysFilter extends OncePerRequestFilter
{
	private String except;
	
	private String[] excepts;

	public String getExcept() {
		return except;
	}

	public void setExcept(String except) {
		this.except = except;
		excepts = except.split(",");
		for( int i = 0; i < excepts.length; i++ )
		{
			excepts[i] = excepts[i].trim();
		}
	}

	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)	throws ServletException, IOException
	{
		String reqUri = request.getRequestURI();
		//��鱨��
		
		if( reqUri.startsWith("/wxshop") )
		{
			reqUri = reqUri.substring(reqUri.indexOf("/", 1), reqUri.length());
		}
		//ǰ̨�Ĺ�����ʱ����
		if(reqUri.startsWith("/my"))
		{
			filterChain.doFilter(request, response);
		}
		else
		{

			WcShopAdmin admin = null;
			admin = (WcShopAdmin) request.getSession().getAttribute(SysConstant.ADMIN_INFO);
			if( admin != null || PatternMatchUtils.simpleMatch(excepts, reqUri) || (reqUri.contains(".") && ! reqUri.toLowerCase().endsWith(".jsp")) )
			{
				// ��request�������ֲ߳̾�������
//				SysConstant.REQUEST_LOCAL.set(request);
				// ִ��Ŀ�귽��
				filterChain.doFilter(request, response);
				// ����ֲ߳̾�����
//				SysConstant.REQUEST_LOCAL.remove();
			}
			else 
			{
				if( request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest") )
				{
					response.setStatus(999);
				}
				else 
				{
					//��ʾδ��¼
					request.setAttribute("msgCode", "1");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view1/common/msg.jsp");
					dispatcher.forward(request, response);
					
				}
			}
			
		}
		
	}
}

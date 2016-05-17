package com.wxshop.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionHandler implements HandlerExceptionResolver {

	private static Logger log = Logger.getLogger(MyExceptionHandler.class);
	
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) 
	{
		Map<String, Object> model = new HashMap<String, Object>();  
	    model.put("exception", ex);  
	   
	    // 根据不同错误转向不同页面  
	    if(ex instanceof java.lang.NullPointerException) 
	    {  
	    	log.error("系统空指针异常!");
	        return new ModelAndView("error-null", model);  
	    }
	    else if(ex instanceof java.sql.SQLException) 
	    {  
	    	log.error(ex.getMessage());
	        return new ModelAndView("error-sql", model);  
	    } 
	    else 
	    {  
	    	log.error("未知异常:"+ex.getCause().toString().replace("'", "\""));
	        return new ModelAndView("error", model);  
	    }  
	}

}

<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <title>活动列表</title>
    <link href="${pageContext.request.contextPath}/view1/my/fonts/iconfont.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/view1/my/css/bass.css" 		rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/view1/my/css/index.css" 		rel="stylesheet"/>
</head>
<body>
    <nav class="nav-bar nav-bar-tab">
        <a href="index.html" class="nav-tab-item">
            <span class="nav-icon icon-uniE90A"></span>
            <span class="nav-tab-label">首页</span>
        </a>
        <a href="contact.html" class="nav-tab-item">
            <span class="nav-icon icon-uniE919"></span>
            <span class="nav-tab-label">通讯录</span>
        </a>
        <a href="chatrecord.html" class="nav-tab-item">
            <span class="nav-icon icon-uniE903"><span class="nav-badge">1</span></span>
            <span class="nav-tab-label">会话</span>
        </a>
        <a href="userPu.html" class="nav-tab-item">
            <span class="nav-icon icon-uniE90B"></span>
            <span class="nav-tab-label">我的</span>
        </a>
    </nav>
    <div class="supply-header">
        <div class="contact-search">
            <i class="icon-uniE90F"></i>
            <input type="search" placeholder="搜索">
        </div>
        <ul id="tab_btn" class="supply-tab Activity-tab">
            <li class="pick">全部</li>
            <li>收费</li>
            <li>免费</li>
        </ul>
    </div>
    <div class="all_box">
        <!--活动列表-->   
			<ul class="eventreminder eventreminder-b">
				<li>  		
		    		<a href="activity.html">
		    			<div class="A-cimg">
                            <img src="${pageContext.request.contextPath}/view1/my/images/hd1.jpg">
                            <span class="i-activity p-free">免费</span>
                            <span class="i-activity p-number">246人报名</span>
                            <span class="activity-s activity-s-1">正在报名</span>
		    			</div>
	                    <div class="eventreminder-list">
	                        <p class="activity-h1">齐盛湖跨年约跑活动</p>
	                        <p class="activity-TimeXh"><span class="fr">5分钟前</span> <span class="event-xh">广州-徒步社</span></p>
	                    </div>  
					</a>
				</li>  
				<li>        
                    <a href="activity.html">
                        <div class="A-cimg">
                                <img src="${pageContext.request.contextPath}/view1/my/images/hd1.jpg">
                                <span class="i-activity p-free">免费</span>
                                <span class="i-activity p-number">246人报名</span>
                                <span class="activity-s activity-s-1">正在报名</span>
                        </div>
                        <div class="eventreminder-list">
                            <p class="activity-h1">广州50Km徒步活动</p>
                            <p class="activity-TimeXh"><span class="fr">5分钟前</span> <span class="event-xh">广州-徒步社</span></p>
                        </div>  
                    </a>
                </li> 
                <li>        
                    <a href="activity.html">
                        <div class="A-cimg">
                                <img src="${pageContext.request.contextPath}/view1/my/images/hd1.jpg">
                                <span class="i-activity p-free">免费</span>
                                <span class="i-activity p-number">246人报名</span>
                                <span class="activity-s activity-s-1">正在报名</span>
                        </div>
                        <div class="eventreminder-list">
                            <p class="activity-h1">广州50Km徒步活动</p>
                            <p class="activity-TimeXh"><span class="fr">5分钟前</span> <span class="event-xh">广州-徒步社</span></p>
                        </div>  
                    </a>
                </li> 
                <li>        
                    <a href="activity.html">
                        <div class="A-cimg">
                                <img src="${pageContext.request.contextPath}/view1/my/images/hd1.jpg">
                                <span class="i-activity p-free">免费</span>
                                <span class="i-activity p-number">246人报名</span>
                                <span class="activity-s activity-s-1">正在报名</span>
                        </div>
                        <div class="eventreminder-list">
                            <p class="activity-h1">广州50Km徒步活动</p>
                            <p class="activity-TimeXh"><span class="fr">5分钟前</span> <span class="event-xh">广州-徒步社</span></p>
                        </div>  
                    </a>
                </li> 
	        </ul>
   <!--活动列表end-->
    </div>

</div>
</body>
</html>

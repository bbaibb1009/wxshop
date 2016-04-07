<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!--Header-part-->
<div id="header"><h1>52pudding</h1></div>
<!--close-Header-part-->
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" >
    	<a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
    		<i class="icon icon-user"></i>  
    		<span class="text"> 欢迎你，管理员</span><b class="caret"></b>
    	</a>
      	<ul class="dropdown-menu">
        <li><a href="#"><i class="icon-user"></i>  我的配置文件</a>	</li>
        <li class="divider"></li>
        <li><a href="#"><i class="icon-check"></i> 我的任务</a>	</li>
        <li class="divider"></li>
        <li><a href="login.html"><i class="icon-key"></i> 注销</a></li>
      	</ul>
    </li>
    <li class="dropdown" id="menu-messages">
    <a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">
    <i class="icon icon-envelope"></i> 
    	<span class="text">消息 </span> 
    	<span class="label label-important">5</span> 
    	<b class="caret"></b>
    	</a>
      <ul class="dropdown-menu">
        <li><a class="sAdd" title="" href="#"><i class="icon-plus"></i> 未读   <span class="label label-important">5</span></a></li>
        <li class="divider"></li>
        <li><a class="sInbox" title="" href="#"><i class="icon-envelope"></i> 已读</a></li>
        <li class="divider"></li>
        <li><a class="sTrash" title="" href="#"><i class="icon-trash"></i> 回收站</a></li>
      </ul>
    </li>
    <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text"> 个人设置</span></a></li>
    <li class=""><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span class="text"> 注销</span></a></li>
  </ul>
</div>
<!--close-top-Header-menu-->
<!--start-top-serch-->
<div id="search">
  <input type="text" placeholder=" 搜索..."/>
  <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div>
<!--close-top-serch-->
<!--sidebar-menu-->
<div id="sidebar">
  <a href="#" class="visible-phone"><i class="icon icon-home">系统管理</i></a>
  <ul >
	<c:forEach var="menu1" items="${adminMenusLevel1Cust}">
		<c:set var="menu1Class" value=""></c:set>
		<c:if test="${menu1.WSM_ISPARENT == '1'}">
		<c:set var="menu1Class" value="submenu"></c:set>
		</c:if>
		<li class="${menu1Class}">
			<a href="#" onclick=""><i class="icon icon-home"></i><span> ${menu1.WSM_NAME}</span></a>
			<c:if test="${menu1.WSM_ISPARENT == '1'}">
				<ul id="${menu1.WSM_ID}">
					<c:forEach items="${adminMenusLevel2Cust}" var="menu2">
						<c:if test="${menu2.WSM_PARENT_ID==menu1.WSM_ID}">
							<c:set var="menu2Class" value=""></c:set>
							<c:if test="${menu2.WSM_ISPARENT == '1'}">
							<c:set var="menu2Class" value="submenu"></c:set>
							</c:if>
							<li class="${menu2Class}">
								<a href="javascript: goUrl(${menu2.WSM_ID},'${menu2.WSM_URL }');">${menu2.WSM_NAME}</a>
								<c:if test="${menu2.WSM_ISPARENT == '1'}">
									<ul id="${menu2.WSM_ID}">
										<c:forEach items="${adminMenusLevel3Cust}" var="menu3">
										<c:if test="${menu3.WSM_PARENT_ID==menu2.WSM_ID}">
											<li>
												<a href="${pageContext.request.contextPath}${menu3.WSM_URL}">${menu3.WSM_NAME}</a>
											</li>
										</c:if>
										</c:forEach>
									</ul>
								</c:if>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</c:if>
		</li>
	</c:forEach>
   
  </ul>
</div>
<!--sidebar-menu-->

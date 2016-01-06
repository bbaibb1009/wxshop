<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>会员查询</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/member/js/member.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery.autocomplete.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	var alertMsg = "${alertMsg}";
	$(document).ready(function(){
		if( alertMsg.length>0)
		{
			alert(alertMsg);
		}
	});
	</script>
  </head>
  <body>
  	<div class="wrapper">
  	<jsp:include page="/view/login/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  	<f:form action="${pageContext.request.contextPath}/member/queryWeiMember" onsubmit="return false;">
  	<f:hidden path="wmbId"/>
	
	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td>姓名:</td>
    		<td><f:input path="wmbName_Q"/></td>
    		<td>手机:</td>
    		<td><f:input path="wmbMobule_Q"/></td>
    		<td>类型:</td>
    		<td>
	    		<f:select path="wmbType_Q" onchange="javascript:querySubmit();">
	    			<f:option value="">请选择</f:option>
	    			<f:option value="1">关注者</f:option>
	    			<f:option value="2">会员</f:option>
	    		</f:select>
    		</td>
    		<td><input type="button" value="查询" onclick="javascript:querySubmit();"></td>
    	</tr>
    </table>
	<input type="button" value="删除" onclick="delChk('wmbIds', '${pageContext.request.contextPath}/member/delMember');"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%"><input type="checkbox" onclick="chkall(this.checked, 'wmbIds');"/></th>
    		<th width="8%">微信名称</th>
    		<th width="8%">商家名称</th>
    		<th width="3%">卡号</th>
    		<th width="8%">openid</th>
    		<th width="5%">会员类型</th>
    		<th width="4%">姓名</th>
    		<th width="8%">手机号码</th>
    		<th width="3%">状态</th>
    		<th width="8%">备注</th>
    		<th width="5%">录入时间</th>
    		<th width="4%">操作</th>
    	</tr>
    	<c:forEach var="member" items="${pageResult.resultList}">
	    	<tr>
	    		<td><input type="checkbox" name="wmbIds" value="${member.WMB_ID}" /></td>
	    		<td>${member.WCS_APP_NAME}</td>
	    		<td>${member.WCS_WEBSITE_NAME}</td>
	    		<td>${member.WMB_CARD_ID}</td>
	    		<td>${member.WMB_OPENID}</td>
	    		<td>
	    		<c:if test="${member.WMB_TYPE==1}">关注者</c:if>
	    		<c:if test="${member.WMB_TYPE==2}">会员</c:if>
	    		</td>
	    		<td>${member.WMB_NAME}</td>
	    		<td>${member.WMB_MOBULE}</td>
	    		<td>
	    		<c:if test="${member.WMB_STATUS=='1000'}">可用</c:if>
	    		<c:if test="${member.WMB_STATUS=='2000'}">不可用</c:if>
	    		</td>
	    		<td>${member.WMB_DESC}</td>
	    		<td><fmt:formatDate value="${member.WMB_REGISTDATE}" type="date" pattern="yyyy-MM-dd"/></td>
	    		<td>
	    			<a href="javascript:toUpd('wmbId', ${member.WMB_ID}, '${pageContext.request.contextPath}/member/toUpdMember')">修改</a>
	    		</td>
	    	</tr>
    	</c:forEach>
    </table>
    <jsp:include page="/view/sys/page.jsp"></jsp:include>
    </f:form>
    </div>
    </div>
    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>

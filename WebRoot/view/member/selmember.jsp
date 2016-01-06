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
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/goods/js/goods.js?v=${applicationScope.sysStartUpTime}"></script>
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

  	<div class="content">
  	<f:form action="${pageContext.request.contextPath}/member/selMember" onsubmit="return false;">
  	<f:hidden path="wmbId"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%"></th>
    		<th width="3%">卡号</th>
    		<th width="5%">会员类型</th>
    		<th width="4%">姓名</th>
    		<th width="8%">手机号码</th>
    		<th width="3%">状态</th>
    		<th width="8%">备注</th>
    	</tr>
    	<c:forEach var="member" items="${pageResult.resultList}">
	    	<tr>
	    		<td><input type="checkbox" name="memIds" value="${member.WMB_ID}_${member.WMB_NAME}" /></td>
	    		<td>${member.WMB_CARD_ID}</td>
	    		<td><c:if test="${member.WMB_TYPE==1}">关注者</c:if>
	    		<c:if test="${member.WMB_TYPE==2}">会员</c:if></td>
	    		<td>${member.WMB_NAME}</td>
	    		<td>${member.WMB_MOBULE}</td>
	    		<td><c:if test="${member.WMB_STATUS==1000}">可用</c:if>
	    		<c:if test="${member.WMB_STATUS==2000}">不可用</c:if></td>
	    		<td>${member.WMB_DESC}</td>
	    	</tr>
    	</c:forEach>
    	<tr><td colspan=7><input type="button" value="确定" onclick="javascript:addMemForSale();"/><input type="button" value="关闭" onclick="javascript:window.close();"/></td></tr>
    </table>
    <jsp:include page="/view/sys/page.jsp"></jsp:include>
    </f:form>
    </div>

    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>商品出库</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/My97DatePicker/WdatePicker.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/goods/js/goods.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/member/js/memberName.js?v=${applicationScope.sysStartUpTime}"></script>
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery.autocomplete.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	var alertMsg = "${alertMsg}";
	$(document).ready(function(){
		if( alertMsg.length>0)
		{
			alert(alertMsg);
		}
		getMemberNameList("wgoWmbName");
		
	});
	function selMemName(suggestion)
	{
	$("#wgoWmbId").val(suggestion.data);
	}
	</script>
  </head>
  <body>
  	<div class="wrapper">
  	<jsp:include page="/view/login/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  	<f:form action="${pageContext.request.contextPath}/goods/addOutGoods" onsubmit="return false;">
  	<f:hidden path="wgoWgsId"/>
  	<f:hidden path="wgoWcsId"/>
  	<f:hidden path="wgoType"/>
  	<f:hidden path= "wgoWmbId"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
 		<tr><th width="8%">出库类型</th>
 		<td>
 			<c:if test="${command.wgoType=='1'}">出库</c:if>
	    	<c:if test="${command.wgoType=='2'}">坏账</c:if>
	    	<c:if test="${command.wgoType=='3'}">销售</c:if>
 		</td>
 		</tr>
 		<tr><th width="8%">出库商品</th><td>${goods.wgsName}</td></tr>
    	<tr><th width="8%">出库时间</th><td><f:input path="wgoOutTime" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" cssClass="Wdate" cssStyle="cursor: pointer;"/></td></tr>
    	<tr><th width="8%">出库量</th><td><f:input path="wgoOutNum"/></td></tr>
    	<tr><th width="8%">销售价</th><td><f:input path="wgoOutPrice"/></td></tr>
    	<tr><th width="8%">备注</th><td><f:textarea path="wgoDesc"/></td></tr>
   		<tr><td colspan=2><input type="button" value="出库" onclick="javascript:addOutGoods();"/></td></tr>
    </table>
    </f:form>
    </div>
    </div>
    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>

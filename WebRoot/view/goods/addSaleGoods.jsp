<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>销售</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/My97DatePicker/WdatePicker.js?v=${applicationScope.sysStartUpTime}"></script>
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
  	<jsp:include page="/view/login/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  	<f:form action="${pageContext.request.contextPath}/goods/addOutGoods" onsubmit="return false;">
  	<f:hidden path="wgoWcsId"/>
  	<f:hidden path="wgoType"/>
  	<f:hidden path="wgoOutTime"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
 		<tr>
 			<th width="8%">出库类型</th>
 			<td style="text-align: left;">
 				<c:if test="${command.wgoType==1}">出库</c:if>
 				<c:if test="${command.wgoType==2}">坏账</c:if>
 				<c:if test="${command.wgoType==3}">销售</c:if>
 			</td>
 		</tr>
 		<tr><th width="8%">出库商品</th>
 		<td style="text-align: left;">
 		类别:
 		<f:select path="wgoWgfId" onchange="javascript:setGoods('wgoWgsId',this);">
 			<f:option value="">请选择</f:option>
 			<c:forEach items="${goodsFenlei}" var="fenlei">
 				<f:option value="${fenlei.wgfId}">${fenlei.wgfName}</f:option>
 			</c:forEach>
 		</f:select>
 		商品:
 		<f:select path="wgoWgsId" onchange="javascript:getGoodsKucun();">
 		<f:option value="">请选择</f:option>
 		<c:forEach items="${goods}" var="good">
 			<f:option value="${good.wgsId}">${good.wgsName}</f:option>
 		</c:forEach>
 		</f:select>
 		</td>
 		</tr>
 		<tr><th width="8%">当前库存</th><td style="text-align: left;"><span id="wgsKucun"></span></td></tr>
    	<tr><th width="8%">标准价</th><td style="text-align: left;"><span id="wgsBiaozhunjia"></span></td></tr>
    	<tr><th width="8%">出库量</th><td style="text-align: left;"><f:input path="wgoOutNum"/></td></tr>
    	<tr><th width="8%">卖给谁</th><td style="text-align: left;"><f:input path="wgoWmbName_Q"/><f:hidden path="wgoWmbId"/><input type="button" value="选择" onclick="javascript:selMember(${command.wgoWcsId});"/></td></tr>
    	<tr><th width="8%">销售价</th><td style="text-align: left;"><f:input path="wgoOutPrice" onblur="javascrit:computeZhekou();" /></td></tr>
    	<tr><th width="8%">折扣</th><td style="text-align: left;"><input id="wgoZhekou" name="wgoZhekou" style="width:40px;" onblur="javascrit:computePrice();"/>折 <font color="red">*直接输入折扣系统自动计算价格，如9折输入"9",85折输入"8.5"</font></td></tr>
    	<tr><th width="8%">备注</th><td style="text-align: left;"><f:textarea path="wgoDesc"/></td></tr>
   		<tr><td colspan=2><input type="button" value="出库" onclick="javascript:addOutGoods();"/></td></tr>
    </table>
    </f:form>
    </div>
    </div>
    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>

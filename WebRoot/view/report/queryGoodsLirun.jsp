<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>产品利润分析</title>
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
  	<jsp:include page="/view/login/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  	<f:form action="${pageContext.request.contextPath}/report/queryGoodsLirun" onsubmit="return false;">
  	<f:hidden path="wgoId"/>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
  		<tr>
	  		<td>商品分类:</td>
	  		<td>
	  			<f:select path="wgoWgfId_Q" onchange="javascript:queryBysel('wgoWgsId_Q',this);">
	  				<f:option value="">请选择</f:option>
	  				<c:forEach items="${goodsFenlei}" var="fenlei">
 						<f:option value="${fenlei.wgfId}">${fenlei.wgfName}</f:option>
 					</c:forEach>
	  			</f:select>
	  		</td>
  		
  			<td>商品:</td>
  			<td>
  				<f:select path="wgoWgsId_Q" onchange="javascript:querySubmit();">
  					<f:option value="">请选择</f:option>
  					<c:forEach items="${goods}" var="good">
 						<f:option value="${good.wgsId}">${good.wgsName}</f:option>
 					</c:forEach>
  				</f:select>
  			</td>
  		
  			<td>出库类型:</td>
  			<td>
  				<f:select path="wgoType_Q" onchange="javascript:querySubmit();">
	  				<f:option value="">请选择</f:option>
	  				<f:option value="1">出库</f:option>
	  				<f:option value="2">坏账</f:option>
	  				<f:option value="3">销售</f:option>
  				</f:select>
  			</td>
  			<td>会员:</td>
  			<td>
  				
  			</td>
  			<td><input type="button" onclick="javascript:querySubmit();" value="查询"/></td>
  		</tr>
  	</table>
  	<!--
    <input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/goods/toAddGoods/${fenlei.wgfId}';"/>
    <input type="button" value="删除" onclick="delChk('wgiIds', '${pageContext.request.contextPath}/goods/delGoods/${fenlei.wgfId}');"/>
 	  -->
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="4%">出库类型</th>
    		<th width="10%">商品名称</th>
    		<th width="8%">商品分类</th>
			<th width="4%">进价</th>
    		<th width="4%">标准销售价</th>
    		<th width="4%">销售价</th>
    		<th width="4%">数量</th>
    		<th width="4%">总价</th>
    		<th width="5%">总利润</th>
    		<th width="4%">单位利润</th>
    		<th width="4%">会员</th>
    		<th width="10%">出库时间</th>

    	</tr>
    	<c:forEach var="goodsOut" items="${pageResult.resultList}">
	    	<tr>
	    		<td>
	    		<c:if test="${goodsOut.WGO_TYPE=='1'}">出库</c:if>
	    		<c:if test="${goodsOut.WGO_TYPE=='2'}">坏账</c:if>
	    		<c:if test="${goodsOut.WGO_TYPE=='3'}">销售</c:if>
	    		</td>
	    		<td>${goodsOut.WGS_NAME}</td>
	    		<td>${goodsOut.WGF_NAME}</td>
	    		<td>${goodsOut.WGI_IN_PRICE}</td>
	    		<td>${goodsOut.WGS_BZ_PRICE}</td>
	    		<td>${goodsOut.WGO_OUT_PRICE}</td>
	    		<td>${goodsOut.WIO_NUMBER}</td>
	    		<td>${goodsOut.zongjia}</td>
	    		<td>${goodsOut.zonglirun}</td>
	    		<td>${goodsOut.danweilirun}</td>
	    		<td>${goodsOut.WMB_NAME}</td>
	    		<td><fmt:formatDate value="${goodsOut.WGO_OUT_TIME}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>																												
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

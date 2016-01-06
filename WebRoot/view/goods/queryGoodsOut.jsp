<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>商品出库查询</title>
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
  	<f:form action="${pageContext.request.contextPath}/goods/queryGoodsOut" onsubmit="return false;">
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
  			<td><input type="button" onclick="javascript:querySubmit();" value="查询"/></td>
  		</tr>
  	</table>
  	<!--
    <input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/goods/toAddGoods/${fenlei.wgfId}';"/>
    <input type="button" value="删除" onclick="delChk('wgiIds', '${pageContext.request.contextPath}/goods/delGoods/${fenlei.wgfId}');"/>
 	  -->
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%"><input type="checkbox" onclick="chkall(this.checked, 'wgiIds');"/></th>
    		<th width="10%">商品名称</th>
    		<th width="8%">商品分类</th>
    		<th width="4%">类型</th>
    		<th width="4%">会员</th>
    		<th width="4%">销售价</th>
    		<th width="4%">出库量</th>
    		<th width="10%">出库时间</th>
    		<th width="4%">出库人</th>
    		<th width="4%">录入人</th>
    		<th width="5%">录入时间</th>
    		<th width="8%">操作</th>
    	</tr>
    	<c:forEach var="goodsOut" items="${pageResult.resultList}">
	    	<tr>
	    		<td><input type="checkbox" name="wgsIds" value="${goodsOut.WGO_ID}" /></td>
	    		<td>${goodsOut.WGS_NAME}</td>
	    		<td>${goodsOut.WGF_NAME}</td>
	    		<td>
	    		<c:if test="${goodsOut.WGO_TYPE=='1'}">出库</c:if>
	    		<c:if test="${goodsOut.WGO_TYPE=='2'}">坏账</c:if>
	    		<c:if test="${goodsOut.WGO_TYPE=='3'}">销售</c:if>
	    		</td>
	    		<td>${goodsOut.WMB_NAME}</td>
	    		<td>${goodsOut.WGO_OUT_PRICE}</td>
	    		<td>${goodsOut.WGO_OUT_NUM}</td>
	    		<td><fmt:formatDate value="${goodsOut.WGO_OUT_TIME}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>${goodsOut.outer1}</td>
	    		<td>${goodsOut.registor}</td>
	    		<td><fmt:formatDate value="${goodsOut.WGO_REGISTDATE}" type="date" pattern="yyyy-MM-dd"/></td>
	    		<td>
	    			<!-- <a href="javascript:toUpd('wgsId', ${goodsIn.WGS_ID}, '${pageContext.request.contextPath}/goods/toUpdGoods')">修改</a> -->
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

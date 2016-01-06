<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>商品入库查询</title>
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
  	<f:form action="${pageContext.request.contextPath}/goods/queryGoodsIn" onsubmit="return false;">
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
  		<tr>
  		<td style="width:10%;">商品分类:</td>
  		<td style="width:15%;text-align:left;">
  			<f:select path="wgiWgfId_Q" onchange="javascript:queryBysel('wgiWgsId_Q',this);">
	  			<f:option value="">请选择</f:option>
	  				<c:forEach items="${goodsFenlei}" var="fenlei">
 						<f:option value="${fenlei.wgfId}">${fenlei.wgfName}</f:option>
 					</c:forEach>
	  			</f:select>
	  	</td>
	  	<td style="width:10%;">商品:</td>
	  	<td style="width:15%;text-align:left;">
	  		<f:select path="wgiWgsId_Q" onchange="javascript:querySubmit();">
  					<f:option value="">请选择</f:option>
  					<c:forEach items="${goods}" var="good">
 						<f:option value="${good.wgsId}">${good.wgsName}</f:option>
 					</c:forEach>
  			</f:select>
	  	</td>
	  	<td style="width:10%;">入库量:</td>
	  	<td style="text-align:left;" colspan=2><f:input path="wgiInNumMin_Q" cssStyle="width:80px;"/> 至 <f:input path="wgiInNumMax_Q" cssStyle="width:80px;"/></td></tr>
  		<tr>
  		<td>库存量:</td><td style="text-align:left;"><f:input path="wgiSurplusNumMin_Q" cssStyle="width:80px;"/> 至 <f:input path="wgiSurplusNumMax_Q" cssStyle="width:80px;"/></td>
  		<td>入库时间:</td><td style="text-align:left;">
  		<f:input path="wgiInTimeStart_Q" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" cssClass="Wdate" cssStyle="cursor: pointer;width:80px;"/>至
  		<f:input path="wgiInTimeEnd_Q" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" cssClass="Wdate" cssStyle="cursor: pointer;width:80px;"/></td>
  		<td>存放位置:</td><td style="text-align:left;"><f:input path="wgiLoc_Q"/></td>
  		<td><input type="button" value="查询" onclick="javascript:querySubmit();"/></td>
  		</tr>

  	</table>
  	<f:hidden path="wgiId"/>
  	<!--
    <input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/goods/toAddGoods/${fenlei.wgfId}';"/>
    <input type="button" value="删除" onclick="delChk('wgiIds', '${pageContext.request.contextPath}/goods/delGoods/${fenlei.wgfId}');"/>
 	  -->
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%"><input type="checkbox" onclick="chkall(this.checked, 'wgiIds');"/></th>
    		<th width="10%">商品名称</th>
    		<th width="6%">商品分类</th>
    		<th width="4%">进价</th>
    		<th width="4%">入库量</th>
    		<th width="10%">入库时间</th>
    		<th width="4%">入库人</th>
    		<th width="4%">剩余</th>
    		<th width="6%">存放位置</th>
    		<th width="4%">录入人</th>
    		<th width="5%">录入时间</th>
    		<th width="8%">操作</th>
    	</tr>
    	<c:forEach var="goodsIn" items="${pageResult.resultList}">
	    	<tr>
	    		<td><input type="checkbox" name="wgsIds" value="${goodsIn.WGS_ID}" /></td>
	    		<td>${goodsIn.WGS_NAME}</td>
	    		<td>${goodsIn.WGF_NAME}</td>
	    		<td>${goodsIn.WGI_IN_PRICE}</td>
	    		<td>${goodsIn.WGI_IN_NUM}</td>
	    		<td><fmt:formatDate value="${goodsIn.WGI_IN_TIME}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>${goodsIn.inName}</td>
	    		<td>${goodsIn.WGI_SURPLUS_NUM}</td>
	    		<td>${goodsIn.WGI_LOC}</td>
	    		<td>${goodsIn.regName}</td>
	    		<td><fmt:formatDate value="${goodsIn.WGI_REGISTDATE}" type="date"/></td>
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

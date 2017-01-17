<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<title>${applicationScope.globalTitle}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/bootstrap.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-style.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-media.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/uniform.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/jquery.gritter.css" >

<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 
<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
</script>
</head>
<body>
<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  
  <div id="content-header">
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">团队查询</a></div>
  </div>
  <jsp:include page="/view1/common/alertMsg.jsp"></jsp:include>
  <div class="container-fluid">
     
    <div class="row-fluid">
    	
	   <input type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/shop/dept/toAddShopDept'" value="添加"/> 
	   <input type="button" class="btn btn-primary" onclick="javascript:delChk('wdpIds', '${pageContext.request.contextPath}/shop/dept/delShopDept');" value="删除"/>
	   
    </div>
    <div class="row-fluid">
        <div class="widget-box">
          <div class="widget-content nopadding">
          	<f:form action="${pageContext.request.contextPath}/shop/dept/queryDept" onsubmit="return false;">
          	<f:hidden path="wdpId"/>
          	<f:hidden path="wdpRegistdate"/>
          	<f:hidden path="wdpRegistor"/>
          	
          	
            <table class="table table-bordered with-check">
                <tr>
                  <th class="small" width="2%"><input type="checkbox" onclick="chkall(this.checked, 'wdpIds');"/></th>
                  <th class="small" width="10%">团队名称</th>
                  <th class="small" width="10%">上级团队</th>
                  <th class="small" width="5%">团队级别</th>
                  <th class="small" width="5%">顺序</th>
                  <th class="small" width="5%">负责人</th>
                  <th class="small" width="5%">状态</th>
                  <th class="small" width="40%">备注</th>
                  <th class="small" width="5%">录入人</th>
                  <th class="small" width="5%">录入时间</th>
                  <th class="small" width="10%">操作</th>
                </tr>
                <c:forEach items="${pageResult.resultList}" var="dept">
                <tr>
                	<td class="text-center small"><input type="checkbox"  name="wdpIds" value="${dept.WDP_ID}"/></td>
                  	<td class="text-center small">${dept.WDP_NAME}</td>
                  	<td class="text-center small">${dept.PARENT_NAME == null ?'' : dept.PARENT_NAME}</td>
                  	<td class="text-center small"><c:if test="${dept.WDP_LEVEL=='1'}">协会</c:if><c:if test="${dept.WDP_LEVEL=='2'}">分部</c:if></td>
                    <td class="text-center small">${dept.WDP_ORDER}</td>
                    <td class="text-center small">${dept.ADMIN_NAME}</td>
                    <td class="text-center small">
                    <c:if test="${dept.WDP_STATUS == '1000'}">可用</c:if>
                    <c:if test="${dept.WDP_STATUS == '2000'}">不可用</c:if>
                    </td>
                    <td class="text-center small">${dept.WDP_DESC}</td>
                    <td class="text-center small">${dept.WDP_REGISTOR_NAME}</td>
                  	<td class="text-center small">${dept.WDP_REGISTDATE}</td>
                  	<td class="text-center small">
                  		<a href="#" onclick="javascript:toUpd('wdpId', ${dept.WDP_ID}, '${pageContext.request.contextPath}/shop/dept/toUpdShopDept');" class="btn btn-primary btn-xs">修改</a>
                  	</td>
                </tr>
                </c:forEach> 

            </table>
            <jsp:include page="/view1/sys/page.jsp"></jsp:include>
            </f:form>
            
          </div>
        </div>
      </div>
  </div>
</div>

<!--end-main-container-part-->
<!--Footer-part-->

<jsp:include page="/view1/common/footer.jsp"></jsp:include>
<!--end-Footer-part-->

<script src="${pageContext.request.contextPath}/view1/js/jquery-browser.js"></script> 		
<script src="${pageContext.request.contextPath}/view1/js/basic.js"></script>
<script src="${pageContext.request.contextPath}/view1/js/tabList.js"></script>
<script src="${pageContext.request.contextPath}/view1/js/jquery.ui.custom.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap-typeahead.js"></script> <!-- 自动补全 -->
<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.resize.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.peity.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.gritter.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.wizard.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.uniform.js"></script><!-- 美化checkBox的js -->
<script src="${pageContext.request.contextPath}/view1/js/select2.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.dataTables.1.10.9.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.tables.js"></script> 
<script src="${pageContext.request.contextPath}/view1/dept/js/dept.js" ></script>
</body>
</html>

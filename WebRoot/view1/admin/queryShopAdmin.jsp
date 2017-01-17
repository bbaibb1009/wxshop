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
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a> <a href="#" class="current">客户端管理员查询</a></div>
  </div>
 <jsp:include page="/view1/common/alertMsg.jsp"></jsp:include>
  <div class="container-fluid">
     
    <div class="row-fluid">
    	
	   <input type="button" class="btn btn-primary" onclick="toAdd('${pageContext.request.contextPath}/shop/admin/toAddShopAdmin');" value="添加"/> 
	   <input type="button" class="btn btn-primary" onclick="javascript:delChk('wsaIds', '${pageContext.request.contextPath}/shop/admin/delShopAdmin');" value="删除"/>
	   
    </div>
    <div class="row-fluid">
        <div class="widget-box">
          <div class="widget-content nopadding">
          	<f:form action="${pageContext.request.contextPath}/shop/admin/queryShopAdmin" onsubmit="return false;">
          	<f:hidden path="wsaId"/>
            <table class="table table-bordered with-check">
                <tr>
                  <th class="small" width="2%"><input type="checkbox" onclick="chkall(this.checked, 'wsaIds');"/></th>
                  <th class="small" width="7%">用户名</th>
                  <th class="small" width="7%">姓名</th>
                  <th class="small" width="15%">分部</th>
                  <th class="small" width="4%">性别</th>
                  <th class="small" width="7%">录入人</th>
                  <th class="small" width="10%">录入时间</th>
                  <th class="small" width="50%">操作</th>
                </tr>
                <c:forEach items="${pageResult.resultList}" var="admin">
                <tr>
                	<td class="text-center small"><input type="checkbox"  name="wsaIds" value="${admin.WSA_ID}"/></td>
                  	<td class="text-center small">${admin.WSA_USERNAME}</td>
                  	<td class="text-center small">${admin.WSA_NAME}</td>
                  	<td class="text-center small">${admin.deptname}</td>
                  	
                    <td class="text-center small"><c:if test="${admin.WSA_SEX=='1'}">男</c:if><c:if test="${admin.WSA_SEX=='2'}">女</c:if></td>
                    <td class="text-center small">${admin.registor}</td>
                  	<td class="text-center small">${admin.WSA_REGISTDATE}</td>
                  	<td class="text-center small">
                  	<a href="#" onclick="javascript:toUpd('wsaId', ${admin.WSA_ID}, '${pageContext.request.contextPath}/shop/admin/toUpdShopAdmin');" class="btn btn-primary btn-xs">修改</a>
                  	<a href="#" onclick="javascript:toUpdRole(${admin.WSA_ID});" class="btn btn-primary btn-xs">菜单权限</a>
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
<script src="${pageContext.request.contextPath}/view1/admin/js/admin.js" ></script>
<jsp:include page="/view1/common/modal-dialog.jsp"></jsp:include>
</body>
</html>

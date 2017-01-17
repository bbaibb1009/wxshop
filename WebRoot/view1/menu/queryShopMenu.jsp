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
<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'>
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
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a> <a href="#" class="current">客户端菜单查询</a></div>
  </div>
  <div class="container-fluid">
    <hr>
    <div class="btn-group">
	   <input type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/shop/menu/toAddShopMenu'" value="添加"/> 
	   <input type="button" class="btn btn-primary" onclick="javascript:delChk('wsmIds', '${pageContext.request.contextPath}/shop/menu/delShopMenu');" value="删除"/>
    </div>
    <div class="row-fluid">
        <div class="widget-box">
 		 
		  
          <div class="widget-content nopadding">
          	<f:form action="${pageContext.request.contextPath}/shop/menu/queryMenu" onsubmit="return false;">
          	<f:hidden path="wsmId"/>
            <table class="table table-bordered with-check">
                <tr>
                  <th class="small" width="2%"><input type="checkbox" onclick="chkall(this.checked, 'wsmIds');"/></th>
                  <th class="small" width="20%">菜单名称</th>
                  <th class="small" width="25%">菜单URL</th>
                  <th class="small" width="5%">级别</th>
                  <th class="small" width="5%">顺序</th>
                  <th class="small" width="10%">上级菜单</th>
                  <th class="small" width="5%">录入人</th>
                  <th class="small" width="8%">录入时间</th>
                  <th class="small" width="20%">操作</th>
                </tr>
                <c:forEach items="${pageResult.resultList}" var="menu">
                <tr>
                	<td class="text-center small"><input type="checkbox"  name="wsmIds" value="${menu.WSM_ID}"/></td>
                  	<td class="text-center small">${menu.WSM_NAME}</td>
                  	<td class="text-center small">${menu.WSM_URL}</td>
                  	<td class="text-center small">${menu.WSM_LEVEL}</td>
                  	<td class="text-center small">${menu.WSM_ORDER}</td>
                  	<td class="text-center small">${menu.parent_menu_name}</td>
                  	<td class="text-center small">${menu.WSA_NAME}</td>
                  	<td class="text-center small">${menu.WSM_REGIST_DATE}</td>
                  	<td class="text-center small"><a href="javascript:toUpd('wsmId', ${menu.WSM_ID}, '${pageContext.request.contextPath}/shop/menu/toUpdShopMenu');" class="btn btn-primary btn-xs">修改</a></td>
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
<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 
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
<script src="${pageContext.request.contextPath}/view1/menu/js/menu.js" ></script>
<jsp:include page="/view1/common/modal-dialog.jsp"></jsp:include>
</body>
</html>

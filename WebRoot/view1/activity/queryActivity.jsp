<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<title>微商城管理后台</title>
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
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a> <a href="#" class="current">活动查询</a></div>
  </div>
  <div class="container-fluid">
    <hr>
    <div class="btn-group">
	   <input type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/activity/toAddActivity'" value="添加"/> 
	   <input type="button" class="btn btn-primary" onclick="javascript:delChk('wsmIds', '${pageContext.request.contextPath}/activity/delActivity');" value="删除"/>
    </div>
    <div class="row-fluid">
        <div class="widget-box">
          <div class="widget-content nopadding">
          	<f:form action="${pageContext.request.contextPath}/activity/queryActivity" onsubmit="return false;">
          	<f:hidden path="wtaId"/>
            <table class="table table-bordered with-check">
                <tr>
                  <th class="small" width="2%"><input type="checkbox" onclick="chkall(this.checked, 'wtaIds');"/></th>
                  <th class="small" width="20%">活动名称</th>
                  <th class="small" width="25%">活动类型</th>
                  <th class="small" width="5%">是否收费</th>
                  <th class="small" width="5%">状态</th>
                  <th class="small" width="5%">发布人</th>
                  <th class="small" width="8%">发布时间</th>
                  <th class="small" width="20%">操作</th>
                </tr>
                <c:forEach items="${pageResult.resultList}" var="activity">
                <tr>
                	<td class="text-center small"><input type="checkbox"  name="wtaIds" value="${activity.WTA_ID}"/></td>
                  	<td class="text-center small">${activity.WTA_TITLE}</td>
                  	<td class="text-center small">${activity.WTA_TYPE}</td>
                  	<td class="text-center small">${activity.WTA_FEE_TYPE}</td>
                  	<td class="text-center small">${activity.WTA_STATUS}</td>
                  	<td class="text-center small">${activity.WTA_REGISTOR}</td>
                  	<td class="text-center small">${activity.WTA_REGIST_DATE}</td>
                  	<td class="text-center small"><a href="javascript:toUpd('wtaId', ${activity.WTA_ID}, '${pageContext.request.contextPath}/activity/toUpdActivity');" class="btn btn-primary btn-xs">修改</a></td>
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

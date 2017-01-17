<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<title>${applicationScope.globalTitle}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/fullcalendar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-media.css" />
<link href="${pageContext.request.contextPath}/view1/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>


</head>
<body>

<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a> <a href="#" class="current">商品分类查询</a> </div>
    <h1>商品分类查询</h1>
  </div>
  <div class="container-fluid">
    <hr>
    <div class="btn-group">
	   <input type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/goods/toAddGoodsFenlei/${command.wgfWcsId}'" value="添加"/>
    </div>
    <div class="row-fluid">
        <div class="widget-box">
          <div class="widget-content nopadding">
          	<f:form action="${pageContext.request.contextPath}/goods/queryGoodsFenlei" onsubmit="return false;">
            <table class="table table-bordered with-check ">
              <thead>
                <tr>
                  <th><i class="icon-resize-vertical"></i></th><th>名称</th><th>状态</th><th>录入人</th><th>录入时间</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${pageResult.resultList}" var="fenlei">
                <tr>
                  <td><input type="checkbox" name=""/></td><td>${fenlei.WGF_NAME}</td><td>${fenlei.WGF_STATUS}</td><td>${fenlei.WGF_REGISTOR}</td><td class="center">${fenlei.WGF_REGISTDATE}</td>
                </tr>
                </c:forEach> 
              </tbody>
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

<div class="row-fluid">
  <div id="footer" class="span12"> 2013 &copy; Matrix Admin. Brought to you by <a href="http://themedesigner.in/">Themedesigner.in</a> </div>
</div>

<!--end-Footer-part-->


<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/basic.js"></script>
<script src="${pageContext.request.contextPath}/view1/js/tabList.js"></script>
<script src="${pageContext.request.contextPath}/view1/js/jquery.ui.custom.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.resize.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.peity.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/fullcalendar.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.gritter.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.interface.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.chat.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.validate.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.form_validation.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.wizard.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.uniform.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/select2.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.dataTables.1.10.9.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.tables.js"></script> 



</body>
</html>

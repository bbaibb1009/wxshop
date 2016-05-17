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
<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'>
<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
</script>
</head>
<body>
<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
<!--main-container-part-->
<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#">Sample pages</a> <a href="#" class="current">Error</a> </div>
    <h1>Error 404</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      	<div class="span12">
        <div class="widget-box">
        	<div class="widget-title"> 
				<span class="icon"> <i class="icon-info-sign"></i> </span>
            	<h5>系统错误</h5>
          	</div>
        	<c:if test="${exception == null}">
          		<div class="widget-content">
            		<div class="error_ex">
              		<h1>404</h1>
              		<h3>您请求的页面URL已丢失.</h3>
               		<p>非常抱歉，您可以回到首页重新访问:</p>
              		<a class="btn btn-warning btn-big"  href="${pageContext.request.contextPath}/admin/adminLoginSuccess">回到首页</a> 
           			<a class="btn btn-warning btn-big"  href="${pageContext.request.contextPath}/admin/adminLogin">重新登录</a> 
              		</div>
         		</div>
			</c:if>
			<c:if test="${exception != null}">
          		<div class="widget-content">
            		<div class="error_ex">
              		<h1>系统错误</h1>
               		<p>${exception}</p>
              		<a class="btn btn-warning btn-big"  href="${pageContext.request.contextPath}/admin/adminLoginSuccess">回到首页</a> 
           			<a class="btn btn-warning btn-big"  href="${pageContext.request.contextPath}/admin/adminLogin">重新登录</a> 
              		</div>
         		</div>
			</c:if>
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
<script src="${pageContext.request.contextPath}/view1/js/bootstrap.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.js"></script> 
<jsp:include page="/view1/common/modal-dialog.jsp"></jsp:include>
</body>
</html>

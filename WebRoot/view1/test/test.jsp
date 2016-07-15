<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<title>微商城管理后台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- 这一句是适应移动设备的 必须加 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1, user-scalable=no" />
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
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">测试专用页</a></div>
    <h1>测试专用页</h1>
  </div>
  <div class="container-fluid">
    <div class="btn-group">
	   <input type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/shop/menu/toAddShopMenu'" value="添加"/>
   		<!-- 按钮触发模态框 -->
		<input type="button" value="开始演示模态窗口" class="btn btn-primary " data-toggle="modal"   data-target="#myModal" data-show="true"/>
    </div>
    <div class="row-fluid">
          <div class="col-md-1">
        	fdsfdsfdsf
          </div>
    </div>
  </div>
</div>

<!--end-main-container-part-->
<!--Footer-part-->
<div class="row-fluid">
  <div id="footer" class="span12"> 2013 &copy; Matrix Admin. Brought to you by <a href="http://themedesigner.in/">Themedesigner.in</a></div>
</div>
<div id="myModal" class="modal hide  in">
	<div>
		<h3 id="myModalLabel">Modal header</h3>
	</div>
	<div>
		<h4>Text in a modal</h4>
		<p>You can add some text here.</p>		        
	</div>
</div>
<!--end-Footer-part-->
<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script>		 			
<script src="${pageContext.request.contextPath}/view1/js/jquery-browser.js"></script> 				
<script src="${pageContext.request.contextPath}/view1/js/basic.js"></script>							
<script src="${pageContext.request.contextPath}/view1/js/tabList.js"></script>						
<script src="${pageContext.request.contextPath}/view1/js/jquery.ui.custom.js"></script> 			
<script src="${pageContext.request.contextPath}/view1/js/bootstrap.js"></script> 					
<script src="${pageContext.request.contextPath}/view1/js/bootstrap-typeahead.js"></script> 			
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
<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	$(document).ready(function(){
		
		//$("body").off(".data-api");
	});	
</script>
</body>
</html>

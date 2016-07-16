<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<title>测试下拉选择框</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/fullcalendar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-media.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/select2.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 

<script src="${pageContext.request.contextPath}/view1/js/jquery-browser.js"></script> 		


<script src="${pageContext.request.contextPath}/view1/js/bootstrap.min.js"></script> 


 

<script src="${pageContext.request.contextPath}/view1/js/jquery.peity.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/fullcalendar.min.js"></script> 

<script src="${pageContext.request.contextPath}/view1/js/jquery.wizard.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.uniform.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/select2.min.js"></script> 

<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	$(document).ready(function(){
		$("#wsmLevel").select2();
		
	});
	
</script>
</head>
<body>

<!--main-container-part-->

<!--breadcrumbs-->
 	<div id="content-header">
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">客户端菜单添加</a></div>
    <h1>客户端菜单添加</h1>
    
   
  </div>
  <div class="">

  	<div class="">
  	
  	


 
          	
          	
            
              	
              		
                  	<select id="wsmLevel">
                  		<option value="">-请选择-</option>
                  		<option value="1">1级</option>
                  		<option value="2">2级</option>
                  	</select>
	           
			
          

  	</div>
  </div>
  
  

</body>
</html>

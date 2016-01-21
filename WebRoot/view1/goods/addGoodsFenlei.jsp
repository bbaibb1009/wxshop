<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<title>布丁网</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/fullcalendar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-media.css" />
<link href="${pageContext.request.contextPath}/view1/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/basic.js}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view1/goods/js/goods.js?v=${applicationScope.sysStartUpTime}"></script>
<script >


	function goUrl(adminMenuId3, url)
	{
		var adminMenuId1 = -1;
		var adminMenuId2 = -1;
		
		var adminLeft = $("#adminLeft").children();
		for( var i = 0; i < adminLeft.length; i++ )
		{
			if( adminLeft[i].style.display == "" )
			{
				adminMenuId1 = adminLeft[i].id;
			}
		}
		
		var ulAry = document.getElementById(adminMenuId1).getElementsByTagName("ul");
		for( var i = 0; i < ulAry.length; i++ )
		{
			if( ulAry[i].style.display == "" )
			{
				adminMenuId2 = ulAry[i].id;
			}
		}
		
		window.location.href = path + "/admin/goUrl/" + adminMenuId1 
			+ "/" + adminMenuId2 + "/" + adminMenuId3 + "/" + url.replace(/\//g, "|");
	}
</script>
</head>
<body>

<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a> <a href="#" class="current">商品分类查询</a> </div>
    <h1>商品分类查询</h1>
  </div>
  <div class="container-fluid">
    <hr>
    <div class="btn-group">
	   <input type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/goods/toAddGoodsFenlei/${command.wgfWcsId}'" value="添加"/>
    </div>
    <div class="row-fluid">
        <div class="widget-box">
          	<div class="widget-box">
            	<div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
              		<h5>Security validation</h5>
            	</div>
	            <div class="widget-content nopadding">
	              <f:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/goods/addGoodsFenlei" novalidate="novalidate">
	              	<f:hidden path="wgfWcsId"/>
	                <div class="control-group">
	                  <label class="control-label">名称:</label>
	                  <div class="controls">
	                    <f:input path="wgfName"/>
	                  </div>
	                </div>
	                <div class="form-actions">
	                  <input type="button" value="提交" class="btn btn-success" onclick="javascript:addGoodsFenlei();">
	                </div>
	              </f:form>
	            </div>
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

<script src="${pageContext.request.contextPath}/view1/js/excanvas.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.ui.custom.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.resize.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.peity.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/fullcalendar.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.dashboard.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.gritter.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.interface.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.chat.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.validate.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.form_validation.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.wizard.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.uniform.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/select2.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.popover.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.dataTables.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.tables.js"></script> 

<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  function goPage (newURL) {

      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
      
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }

// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}
</script>
</body>
</html>

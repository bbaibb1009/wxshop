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
    <div id="breadcrumb"><a href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a> <a href="#" class="current">服务号查询</a></div>
  </div>
  <jsp:include page="/view1/common/alertMsg.jsp"></jsp:include>
  <div class="container-fluid">
    <hr>
    <div class="btn-group">
	   <input type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/fuwuhao/toAddFuwuhao'" value="配置一个新的服务号"/> 
	   <input type="button" class="btn btn-danger" onclick="javascript:delChk('fwhIds', '${pageContext.request.contextPath}/fuwuhao/delFuwuhao');" value="服务号注销"/>
    </div>
    <div class="row-fluid">
        <div class="widget-box">
          <div class="widget-content nopadding">
          	<f:form action="${pageContext.request.contextPath}/fuwuhao/queryFuwuhao" onsubmit="return false;">
          	<f:hidden path="fwhId"/>
	        <div class="span6">
	          <div class="widget-title"> <span class="icon"> <i class="icon-file"></i> </span>
	            <h5>公众号</h5>
	          </div>
	          <div class="widget-content nopadding">
	            <ul class=" recent-fuwuhao">
	              <c:forEach items="${pageResult.resultList}" var="fuwuhao">	
		              <li>
		                <div class="" style="padding-bottom: 20px;"> 
		                	<input type="checkbox" name="fwhIds" style="float: left;margin-left:-30px;margin-top:-30px;"/>
		                	<img width="80" height="80" alt="User" src="${pageContext.request.contextPath}/view1/img/demo/av1.jpg" class="fl" style="margin-left:-10px;margin-top:-30px;"> 
		                  	<div class="invoice fl" style="padding-left:15px;margin-top:-30px;font-size:14px;color:#e0a;">${fuwuhao.FWH_APP_NAME}</div>
		                  	<!-- <span class="invoice">${fuwuhao.FWH_ACCOUNT_TYPE}</span> -->
		                  	<p style="padding:15px;float:left;"><a href="#">${fuwuhao.FWH_DESC}</a> </p>
		                </div>
		                <div class="article-post" style="margin-top: -10px;padding-bottom:10px;">
		                  <div class="fr"><a href="javascript:toUpd('fwhId', ${fuwuhao.FWH_ID}, '${pageContext.request.contextPath}/fuwuhao/toUpdFuwuhao');" class="btn btn-primary btn-mini">修改</a> 
		                  <a href="#" class="btn btn-success btn-mini">粉丝</a> 
		                  <a href="#" onclick="javascript:toReplyMsg('${fuwuhao.FWH_APP_ID}')" class="btn btn-danger btn-mini">消息</a></div>
		                </div>
		              </li>
	              </c:forEach>
	            </ul>
	          </div>
	        </div>
        </div>
            </f:form>
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
<script src="${pageContext.request.contextPath}/view1/menu/js/menu.js" ></script>
<script src="${pageContext.request.contextPath}/view1/fuwuhao/js/fuwuhao.js" ></script>
<jsp:include page="/view1/common/modal-dialog.jsp"></jsp:include>
</body>
</html>

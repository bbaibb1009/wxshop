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
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a> <a href="#" class="current">微信回复信息管理</a></div>
  </div>
  <jsp:include page="/view1/common/alertMsg.jsp"></jsp:include>
  <div class="container-fluid">
  	<div class="row-fluid">
  		<table class="table table-bordered">
			<tr>
			 	<th width="12%">应用名称</th><td>${fuwuhao.fwhAppName}</td>
			 	<th width="12%">APPID</th><td>${fuwuhao.fwhAppId}</td>
			</tr>  		
  		</table>
  	</div>
    <div class="widget-box">
          <div class="widget-title"> <span class="icon"><i class="icon-repeat"></i></span>
            <h5>关注回复</h5>
          </div>
          <div class="widget-content">
           	 <ul class="recent-posts">
	             <li>
	                <div class="article-post">
	                  <div class="fr"><a href="#" class="btn btn-primary btn-mini">编辑</a>  <a href="#" class="btn btn-danger btn-mini">删除</a></div>
	                  <p> ${subscribeMsg.wmgContent}</p>
	                </div>
	             </li>
            </ul>
          </div>
    </div>
    
    
    <div class="row-fluid">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"><i class="icon-repeat"></i></span>
            <h5>关键字回复</h5>
          </div>
         
          <div class="widget-content nopadding">
          		
          	<f:form action="${pageContext.request.contextPath}/wxmsg/queryWcWeiMessage/${fuwuhao.fwhAppId}" onsubmit="return false;">
          	<f:hidden path="wmgId"/>
          	<f:hidden path="wmgAppId_Q"/>
          	<div class="stat-boxes">
	   			<input type="button" class="btn btn-primary" onclick="toAdd('${pageContext.request.contextPath}/wxmsg/toAddWxkeyWordMsg/${fuwuhao.fwhAppId}');" value="添加关键字消息"/> 
	   			<input type="button" class="btn btn-primary" onclick="javascript:delChk('wmgIds', '${pageContext.request.contextPath}/wxmsg/delWxMsg');" value="删除"/>
    	  	</div>
            <table class="table table-bordered ">
                <tr>
                  <th class="small" width="2%"><input type="checkbox" onclick="chkall(this.checked, 'wmgIds');"/></th>
                  <th class="small" width="35%">关键字</th>
                  <th class="small" width="45%">回复内容</th>
                  <th class="small" width="10%">操作</th>
                </tr>
                <c:forEach items="${pageResult.resultList}" var="msg">
                <tr>
                	<td class="text-center small"><input type="checkbox"  name="wsaIds" value="${msg.WMG_ID}"/></td>
                	<td class="text-center small">${msg.WKG_KEYWORDS}</td>
                  	<td class="text-center small"><a class="text-primary" href="javacript:alert('haha');">${msg.WMG_CONTENT}</a></td>
                  	<td class="text-center small">
                  		<a href="#" onclick="javascript:toUpd('wmgId', ${msg.WMG_ID}, '${pageContext.request.contextPath}/wxmsg/toUpdWxMsg');" class="btn btn-primary btn-xs">修改</a>
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
<script src="${pageContext.request.contextPath}/view1/wxmsg/js/wxmsg.js" ></script>
<jsp:include page="/view1/common/modal-dialog.jsp"></jsp:include>
</body>
</html>

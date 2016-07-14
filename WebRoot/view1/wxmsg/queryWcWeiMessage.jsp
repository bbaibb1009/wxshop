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
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a> <a href="#" class="current">微信回复信息管理</a></div>
  </div>
 <jsp:include page="/view1/common/alertMsg.jsp"></jsp:include>
  <div class="container-fluid">
  
  	<div class="row-fluid">
  		<span>${fuwuhao.fwhAppName}</span><span>${fuwuhao.fwhAppId}</span>
  	</div>
    <div class="row-fluid">
    	
	   <input type="button" class="btn btn-primary" onclick="toAdd('${pageContext.request.contextPath}/wxmsg/toAddWxMsg');" value="添加"/> 
	   <input type="button" class="btn btn-primary" onclick="javascript:delChk('wmgIds', '${pageContext.request.contextPath}/wxmsg/delWxMsg');" value="删除"/>
	   
    </div>
    <div class="row-fluid">
        <div class="widget-box">
          <div class="widget-content nopadding">
          	<f:form action="${pageContext.request.contextPath}/wxmsg/queryWcWeiMessage" onsubmit="return false;">
          	<f:hidden path="wmgId"/>
          	<f:hidden path="wmgAppId_Q"/>
            <table class="table table-bordered with-check">
                <tr>
                  <th class="small" width="2%"><input type="checkbox" onclick="chkall(this.checked, 'wmgIds');"/></th>
                  <th class="small" width="7%">所属APPID</th>
                  <th class="small" width="7%">回复内容</th>
                  <th class="small" width="15%">回复内容XML</th>
                  <th class="small" width="4%">回复类型</th>
                  <th class="small" width="7%">消息类型</th>
                  <th class="small" width="10%">加密类型</th>
                  <th class="small" width="10%">状态</th>
                  <th class="small" width="10%">备注</th>
                  <th class="small" width="10%">录入人</th>
                  <th class="small" width="10%">录入时间</th>
                  <th class="small" width="50%">操作</th>
                </tr>
                <c:forEach items="${pageResult.resultList}" var="msg">
                <tr>
                	<td class="text-center small"><input type="checkbox"  name="wsaIds" value="${msg.WMG_ID}"/></td>
                 	<td class="text-center small">${msg.WMG_APP_ID}</td>
                  	<td class="text-center small">${msg.WMG_CONTENT}</td>
                  	<td class="text-center small">${msg.WMG_CONTENT_XML}</td>
                    <td class="text-center small"><c:if test="${msg.WMG_REPLY_TYPE=='1'}">男</c:if><c:if test="${msg.WMG_REPLY_TYPE=='2'}">女</c:if></td>
                    <td class="text-center small">${msg.WMG_MSG_TYPE}</td>
                  	<td class="text-center small">${msg.WMG_AES_TYPE}</td>
                  	<td class="text-center small">${msg.WMG_STATUS}</td>
                  	<td class="text-center small">${msg.WMG_DESC}</td>
                  	<td class="text-center small">${msg.WMG_REGISTOR}</td>
                  	<td class="text-center small">${msg.WMG_REGISTDATE}</td>
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

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
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a> <a href="#" class="current">服务号查询</a></div>
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
          	<f:form action="${pageContext.request.contextPath}/fuwuhao/queryFuwuhao" onsubmit="return false;">
          	<f:hidden path="wsmId"/>
            <table class="table table-bordered with-check">
                <tr>
                  <th class="small" width="2%"><input type="checkbox" onclick="chkall(this.checked, 'wsmIds');"/></th>
                  <th class="small" width="20%">应用名称</th>
                  <th class="small" width="25%">APPID</th>
                  <th class="small" width="5%">AppSecret</th>
                  <th class="small" width="5%">URL(服务器回调地址)</th>
                  <th class="small" width="10%">Token(令牌)</th>
                  <th class="small" width="10%">EncodingAESKey</th>
                  <th class="small" width="10%">消息加解密方式</th>
                  <th class="small" width="10%">应用类型</th>
                  <th class="small" width="10%">客户类型</th>
                  <th class="small" width="10%">账户类型</th>
                  <th class="small" width="10%">商家ID</th>
                  <th class="small" width="10%">默认回复消息</th>
                  <th class="small" width="10%">关注消息回复</th>
                  <th class="small" width="10%">状态</th> 
                  <th class="small" width="10%">备注</th> 
                  <th class="small" width="5%">录入人</th>
                  <th class="small" width="8%">录入时间</th>
                  <th class="small" width="20%">操作</th>
                </tr>
                <c:forEach items="${pageResult.resultList}" var="fuwuhao">
                <tr>
                	<td class="text-center small"><input type="checkbox"  name="wsmIds" value="${fuwuhao.FWH_ID}"/></td>
                  	<td class="text-center small">${fuwuhao.FWH_APP_NAME}</td>
                  	<td class="text-center small">${fuwuhao.FWH_APP_ID}</td>
                  	<td class="text-center small">${fuwuhao.FWH_APP_SECRET}</td>
                  	<td class="text-center small">${fuwuhao.FWH_REDERECT_URL}</td>
                  	<td class="text-center small">${fuwuhao.FWH_TOKEN}</td>
                  	<td class="text-center small">${fuwuhao.FWH_ENCODING_AES_KEY}</td>
                  	<td class="text-center small">${fuwuhao.FWH_AES_TYPE}</td>
                  	<td class="text-center small">${fuwuhao.FWH_APP_TYPE}</td>
                  	<td class="text-center small">${fuwuhao.FWH_CUS_TYPE}</td>
                  	<td class="text-center small">${fuwuhao.FWH_ACCOUNT_TYPE}</td>
                  	<td class="text-center small">${fuwuhao.FWH_ENTER_ID}</td>
                  	<td class="text-center small">${fuwuhao.FWH_DEFAULT_MSG}</td>
                  	<td class="text-center small">${fuwuhao.FWH_SUBSCRIBE_MSG}</td>
                  	<td class="text-center small">${fuwuhao.FWH_STATUS}</td>
                  	<td class="text-center small">${fuwuhao.FWH_DESC}</td>
                  	<td class="text-center small">${fuwuhao.FWH_REGISTOR}</td>
                  	<td class="text-center small">${fuwuhao.FWH_REGISTDATE}</td>
                  	<td class="text-center small"><a href="javascript:toUpd('fwhId', ${fuwuhao.FWH_ID}, '${pageContext.request.contextPath}/fuwuhao/toUpdFuwuhao');" class="btn btn-primary btn-xs">修改</a></td>
                </tr>
                </c:forEach> 

            </table>
            
        <div class="span6">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-file"></i> </span>
            <h5>Recent Posts</h5>
          </div>
          <div class="widget-content nopadding">
            <ul class="recent-posts">
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="img/demo/av1.jpg"> </div>
                <div class="article-post">
                  <div class="fr"><a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a> <a href="#" class="btn btn-danger btn-mini">Delete</a></div>
                  <span class="user-info"> By: john Deo / Date: 2 Aug 2012 / Time:09:27 AM </span>
                  <p><a href="#">This is a much longer one that will go on for a few lines.It has multiple paragraphs and is full of waffle to pad out the comment.</a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="img/demo/av2.jpg"> </div>
                <div class="article-post">
                  <div class="fr"><a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a> <a href="#" class="btn btn-danger btn-mini">Delete</a></div>
                  <span class="user-info"> By: john Deo / Date: 2 Aug 2012 / Time:09:27 AM </span>
                  <p><a href="#">This is a much longer one that will go on for a few lines.It has multiple paragraphs and is full of waffle to pad out the comment.</a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="img/demo/av3.jpg"> </div>
                <div class="article-post">
                  <div class="fr"><a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a> <a href="#" class="btn btn-danger btn-mini">Delete</a></div>
                  <span class="user-info"> By: john Deo / Date: 2 Aug 2012 / Time:09:27 AM </span>
                  <p><a href="#">This is a much longer one that will go on for a few lines.Itaffle to pad out the comment.</a> </p>
                </div>
              <li>
                <button class="btn btn-warning btn-mini">View All</button>
              </li>
            </ul>
          </div>
        </div>
        </div>
            
            
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

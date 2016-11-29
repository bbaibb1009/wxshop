<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<title>微商城管理后台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/bootstrap.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/fullcalendar.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-style.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-media.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/select2.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/font-awesome/css/font-awesome.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/jquery.gritter.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/zTree/zTreeStyle.css"/>
<link rel='stylesheet' href="${pageContext.request.contextPath}/view1/css/select2.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/icheck/all.css"/>
<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'  >
<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 


</head>
<body>
<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">赛事添加</a></div>
  </div>
  <div class="container-fluid">
  <hr>
  	<div class="row-fluid">
    <!--这是我自己的 -->
     <div class="span6">
      <div class="widget-box">
      	  <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
             <h5>赛事添加</h5>
          </div>
          <div class="widget-content ">
          	<f:form action="${pageContext.request.contextPath}/match/addMatch" cssClass="form-horizontal" onsubmit="return false;">
            <div class="form-group">
            	<label for="wmaName" class="col-lg-1 control-label">赛事名称</label>
            	<div class="col-lg-3">
               	 	<f:input path="wmaName" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group">
            	<label for="wmaPlace" class="col-lg-1 control-label">地点</label>
            	<div class="col-lg-3">
               	 	<f:input path="wmaPlace" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group">
            	<label for="wmaRuntime" class="col-lg-1 control-label">比赛时间</label>
            	<div class="col-lg-3">
               	 	<f:input path="wmaRuntime" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group ">
            	<label for="wmaGameProject" class="col-lg-1 control-label">项目概述</label> 
            	<div class="col-lg-3">
                	<f:input path="wmaGameProject" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group">
            	<label for="wmaYear" class="col-lg-1 control-label">所属年份</label>
            	<div class="col-lg-3">
                	<f:input path="wmaYear" cssClass="form-control input-ms"/>
            	</div>
            </div>
            
            <div class="form-group">
            	<label for="wmaSessionIndex" class="col-lg-1 control-label">界别</label>
            	<div class="col-lg-3">
            		<f:input path="wmaSessionIndex"  cssClass="form-control input-ms"/>第几届
            	</div>
            </div>
                  
			<div class="form-group ">
            	<label for="wmaLastSessionId" class="col-lg-1 control-label">上届赛事</label> 
            	<div class="col-lg-3">
                	<f:hidden path="wmaLastSessionId" cssClass="form-control input-ms"/>
            	</div>
            </div>
			<div class="form-group ">
            	<label for="wmaEmergencyContract" class="col-lg-1 control-label">通用紧急联系人姓名</label> 
            	<div class="col-lg-3">
                	<f:input path="wmaEmergencyContract" cssClass="form-control input-ms"/>
            	</div>
            </div>
            
            <div class="form-group ">
            	<label for="wmaEmergencyPhone" class="col-lg-1 control-label">通用紧急联系人手机</label> 
            	<div class="col-lg-3">
                	<f:input path="wmaEmergencyPhone" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group ">
            	<label for="wmaDesc" class="col-lg-1 control-label">备注</label> 
            	<div class="col-lg-3">
                	<f:input path="wmaDesc" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group  ">
				<div class="col-lg-1"></div>
				<div class="col-lg-3">
					<button class="btn btn-info" onclick="addMatchSubmit();" >提交</button>
				</div>          	
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
<jsp:include page="/view1/common/footer.jsp"></jsp:include>
<!--end-Footer-part-->
<script>
	var path = "${pageContext.request.contextPath}";
	
	
	$(document).ready(function(){
		
	});
	
</script>
<script src="${pageContext.request.contextPath}/view1/js/jquery-browser.js"></script> 		
<script src="${pageContext.request.contextPath}/view1/js/basic.js"></script>
<script src="${pageContext.request.contextPath}/view1/js/tabList.js"></script>
<script src="${pageContext.request.contextPath}/view1/js/jquery.ui.custom.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap-colorpicker.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap-datepicker.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/masked.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.resize.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.peity.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/fullcalendar.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.gritter.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.interface.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.chat.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.validate.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.form_common.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.tables.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.form_validation.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.wizard.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.uniform.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/select2.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.dataTables.1.10.9.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/fuwuhao/js/fuwuhao.js" ></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.core-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.excheck-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/icheck/icheck.js" ></script>
	

</body>
</html>

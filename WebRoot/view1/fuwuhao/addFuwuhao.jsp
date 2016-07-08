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
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">公众号修改</a></div>
  </div>
  <div class="container-fluid">
  <hr>
  	<div class="row-fluid">
    <!--这是我自己的 -->
     <div class="span6">
      <div class="widget-box">
      	  <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
             <h5>修改公众号</h5>
          </div>
          <div class="widget-content ">
          	<f:form action="${pageContext.request.contextPath}/fuwuhao/updFuwuhao" cssClass="form-horizontal" onsubmit="return false;">
          	<f:hidden path="fwhId"/>
          	<f:hidden path="fwhRegistor"/>
          	<f:hidden path="fwhRegistdate"/>
           	<f:hidden path="fwhStatus"/>
 			<f:hidden path="currentPage"/>
  			<f:hidden path="pageSize"/>
            <div class="form-group">
            	<label for="fwhAppName" class="col-lg-1 control-label">应用名称</label>
            	<div class="col-lg-3">
               	 	<f:input path="fwhAppName" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group">
            	<label for="fwhAppId" class="col-lg-1 control-label">AppId</label>
            	<div class="col-lg-3">
               	 	<f:input path="fwhAppId" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group">
            	<label for="fwhAppSecret" class="col-lg-1 control-label">fwhAppSecret</label>
            	<div class="col-lg-3">
               	 	<f:password path="fwhAppSecret" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group ">
            	<label for="fwhRederectUrl" class="col-lg-1 control-label">fwhRederectUrl</label> 
            	<div class="col-lg-3">
                	<f:input path="fwhRederectUrl" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group">
            	<label for="fwhToken" class="col-lg-1 control-label">fwhToken</label>
            	<div class="col-lg-3">
                	<f:input path="fwhToken" cssClass="form-control input-ms"/>
            	</div>
            </div>
            
            <div class="form-group">
            	<label for="fwhEncodingAesKey" class="col-lg-1 control-label">fwhEncodingAesKey</label>
            	<div class="col-lg-3">
            		<f:input path="fwhEncodingAesKey"  cssClass="form-control input-ms"/>
            	</div>
            </div>
                  
			<div class="form-group ">
            	<label for="fwhAppType" class="col-lg-1 control-label">fwhAppType</label> 
            	<div class="col-lg-3">
                	<f:input path="fwhAppType" cssClass="form-control input-ms"/>
            	</div>
            </div>
			<div class="form-group ">
            	<label for="fwhCusType" class="col-lg-1 control-label">fwhCusType</label> 
            	<div class="col-lg-3">
                	<f:input path="fwhCusType" cssClass="form-control input-ms"/>
            	</div>
            </div>
            
            <div class="form-group ">
            	<label for="fwhAccountType" class="col-lg-1 control-label">fwhAccountType</label> 
            	<div class="col-lg-3">
                	<f:input path="fwhAccountType" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group ">
            	<label for="fwhEnterId" class="col-lg-1 control-label">fwhEnterId</label> 
            	<div class="col-lg-3">
                	<f:input path="fwhEnterId" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group ">
            	<label for="fwhDefaultMsg" class="col-lg-1 control-label">fwhDefaultMsg</label> 
            	<div class="col-lg-3">
                	<f:input path="fwhDefaultMsg" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group ">
            	<label for="fwhSubscribeMsg" class="col-lg-1 control-label">fwhSubscribeMsg</label> 
            	<div class="col-lg-3">
                	<f:input path="fwhSubscribeMsg" cssClass="form-control input-ms"/>
            	</div>
            </div>
             <div class="form-group ">
            	<label for="fwhDesc" class="col-lg-1 control-label">fwhDesc</label> 
            	<div class="col-lg-3">
                	<f:input path="fwhDesc" cssClass="form-control input-ms"/>
            	</div>
            </div>
            
			<div class="form-group  ">
				<div class="col-lg-1"></div>
				<div class="col-lg-3">
					<button class="btn btn-info" onclick="updFuwuhaoSubmit();" >提交</button>
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

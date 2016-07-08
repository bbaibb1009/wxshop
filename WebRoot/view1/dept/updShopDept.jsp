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
<link rel='stylesheet' href="${pageContext.request.contextPath}/view1/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"></script>	
<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'  >
<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 
<script>
	var path = "${pageContext.request.contextPath}";
	var setting = {
		check: {
			enable: true,
			chkboxType: { "Y" : "ps", "N" : "ps" }
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
	$(document).ready(function(){

		getAdminNameList("wdpAdminName");
	});
	
	
</script>

</head>
<body>
<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">团队修改</a></div>
  </div>
  <div class="container-fluid">
  <hr>
  	<div class="row-fluid">
    <!--这是我自己的 -->
     <div class="span6">
      <div class="widget-box">
      	  <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
             <h5>修改团队</h5>
          </div>
          <div class="widget-content ">
          	<f:form action="${pageContext.request.contextPath}/shop/dept/updDept" cssClass="form-horizontal" onsubmit="return false;">
          	<f:hidden path="wdpId"/>
          	
            <div class="form-group">
            	<label for="wdpName" class="col-lg-1 control-label">名称</label>
            	<div class="col-lg-3">
               	 	<f:input path="wdpName" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group">
            	<label for="wdpLevel" class="col-lg-1 control-label">级别</label>
            	<div class="col-lg-3">
                   	<f:select path="wdpLevel" onchange="showParentDept(this.value);">
	    				<f:option value="1">协会</f:option>
	    				<f:option value="2">分部</f:option>
	    			</f:select>
                </div>
            </div>
            <div class="form-group">
            	<label for="wdpOrder" class="col-lg-1 control-label">顺序</label>
            	<div class="col-lg-3">
               	 	<f:input path="wdpOrder"  cssClass="form-control input-ms"/>
                </div>
            </div>
            <div id="pdept1" class="form-group " style="display: none;">
            	<label for="wdpParentName" class="col-lg-1 control-label">选择上级部门：</label> 
            	<div class="col-lg-3">
            		<f:input path="wdpParentName"  cssClass="form-control input-ms"/>
                	<f:input path="wdpParentId"/>
            	</div>
            </div>
            <div id="pdept2" class="form-group " style="display: none;">
            	<label for="tdDeptName1" class="col-lg-1 control-label"><font color="red"><b>*</b></font>上级部门：</label> 
            	<div class="col-lg-3">
            		<span id="tdDeptName1"></span>
            	</div>
            </div>

            
            <div class="form-group">
            	<label for="wdpAdminName" class="col-lg-1 control-label">负责人</label>
            	<div class="col-lg-3">
            		<f:input path="wdpAdminName" cssClass="form-control input-ms"/>
                	<f:hidden path="wdpAdminId" />
            	</div>
            </div>
           
			<div class="form-group  ">
				<div class="col-lg-1"></div>
				<div class="col-lg-3">
					<button class="btn btn-info" onclick="addDeptSubmit();" >保存</button> 
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
<script src="${pageContext.request.contextPath}/view1/admin/js/admin.js" ></script>
<script src="${pageContext.request.contextPath}/view1/dept/js/dept.js" ></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.core-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.excheck-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/jquery.autocomplete.min.js?v=${applicationScope.sysStartUpTime}"></script>	

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<title>${applicationScope.globalTitle}</title>
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
<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'  >
<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.core-3.5.min.js"></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.excheck-3.5.min.js"></script>
<script >
	var path = "${pageContext.request.contextPath}";
	var setting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
</script>
</head>
<body>
<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">客户端菜单添加</a></div>
  </div>
  <div class="container-fluid">
  <hr>
  	<div class="row-fluid">
    <!--这是我自己的 -->
     <div class="span6">
      <div class="widget-box">
      	  <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
             <h5>添加客户端菜单</h5>
          </div>
          <div class="widget-content ">
          	<f:form action="${pageContext.request.contextPath}/shop/menu/updShopMenu" cssClass="form-horizontal" onsubmit="return false;">
          	<f:hidden path="wsmId"/>
          	<f:hidden path="wsmParentId"/>
          	<f:hidden path="wsmLevel"/>
  			<f:hidden path="currentPage"/>
  			<f:hidden path="pageSize"/>
            <div class="form-group">
            	<label for="wsmName" class="col-lg-1 control-label">菜单名称</label>
            	<div class="col-lg-3">
               	 	<f:input path="wsmName" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group ">
            	<label for="wsmUrl" class="col-lg-1 control-label">菜单链接</label> 
            	<div class="col-lg-3">
                	<f:input path="wsmUrl" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group">
            	<label for="wsmLevel" class="col-lg-1 control-label">菜单级别</label>
              	<div class="col-lg-3">
                  	<c:if test="${command.wsmLevel == '1'}">
    					一级菜单
    				</c:if>
    				<c:if test="${command.wsmLevel == '2'}">
    					二级菜单
    				</c:if>
    				<c:if test="${command.wsmLevel == '3'}">
    					三级菜单
    				</c:if>
                </div>
			</div>
			<div class="form-group" id="pmenu">
            	<label for="" class="col-lg-1 control-label">上级菜单</label>
              	<div class="col-lg-3">
                  	<ul id="treeDemo" class="ztree"></ul>
	           	</div>
			</div>
			
            <div class="form-group">
              	<label for="wsmOrder" class="col-lg-1 control-label">菜单顺序</label>
              	<div class="col-lg-3">
                	<f:input path="wsmOrder" cssClass="form-control input-ms"/>
            	</div>
            </div>	
            <div class="form-group  ">
              	<label for="wsmDesc" class="col-lg-1 control-label">备注</label>
              	<div class="col-lg-3">
                	<f:textarea path="wsmDesc" cssClass="form-control input-ms" rows="4"></f:textarea>
            	</div>
            </div>
			<div class="form-group  ">
				<div class="col-lg-1"></div>
				<div class="col-lg-3">
					<button class="btn btn-info" onclick="addMenuSubmit();" >保存</button>
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
<script src="${pageContext.request.contextPath}/view1/menu/js/menu.js" ></script>
<script type="text/javascript">
	
	  loadMenuTree("${pageContext.request.contextPath}/shop/menu/getParentMenuTree/${command.wsmLevel}/${command.wsmParentId}");
	
</script>
</body>
</html>

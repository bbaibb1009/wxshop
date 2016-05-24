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
		
		
		$(".js-example-tags").select2();
		//loadMenuTreeForAdmin(${command.wsaId});
	});
</script>



</head>
<body>
<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">客户端管理员修改</a></div>
  </div>
  <div class="container-fluid">
  <hr>
  	<div class="row-fluid">
    <!--这是我自己的 -->
     <div class="span6">
      <div class="widget-box">
      	  <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
             <h5>修改管理员</h5>
          </div>
          <div class="widget-content ">
          	<f:form action="${pageContext.request.contextPath}/shop/admin/updShopAdmin" cssClass="form-horizontal" onsubmit="return false;">
          	<f:hidden path="wsaId"/>
          	<f:hidden path="wsaPwd"/>
          	<f:hidden path="wsaRegistor"/>
          	<f:hidden path="wsaRegistdate"/>
          	<f:hidden path="wsaLogindate"/>
          	<f:hidden path="wsaStatus"/>
 			<f:hidden path="currentPage"/>
  			<f:hidden path="pageSize"/>
            <div class="form-group">
            	<label for="wsaUsername" class="col-lg-1 control-label">用户名</label>
            	<div class="col-lg-3">
               	 	<f:input path="wsaUsername" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group ">
            	<label for="wsaName" class="col-lg-1 control-label">姓名</label> 
            	<div class="col-lg-3">
                	<f:input path="wsaName" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group">
            	<label for="wsaSex" class="col-lg-1 control-label">性别</label>
            	<div class="col-lg-3">
                	<f:input path="wsaSex" cssClass="form-control input-ms"/>
            	</div>
              	
			</div>
			
			<div class="form-group">

				<label for="wsaSex" class="col-lg-1 control-label">角色设置：</label>
				<div  class="col-lg-10" style="border: 2px blue solid;">
					<select class="js-example-tags form-control" multiple="multiple">
						
						<c:forEach items="${roleList1}" var = "role">
							<c:set var="selected" value="" />
							<c:if test="${role.selected != '0'}">
								<c:set var="selected" value="selected=selected" />
							</c:if>
							<option value="${role.wsrRoleId}" ${selected}>${role.wsrRoleName}</option>
						</c:forEach>
				     </select>
					<%-- 
					<div class="col-lg-5" style="border: 1px red solid;">
						<div>未有角色</div>
						<div>
							<f:select path="roleIds2" multiple="true" size="20" cssStyle="width: 250px;">
								<f:options items="${roleList0}" itemValue="roleId" 	itemLabel="roleName"/>
							</f:select>
						</div>
					</div>
									
					<div class="col-lg-1"  style="border: 1px red solid;">
						<img src="${applicationScope.jsPath}/oilchem/view/image/goright.gif" onclick="changeRole(document.getElementById('roleIds2'), document.getElementById('roleIds'), ${command.wsaId});" title="添加角色" style="cursor: pointer;"/>
						<br/><br/>
						<img src="${applicationScope.jsPath}/oilchem/view/image/goleft.gif"  onclick="changeRole(document.getElementById('roleIds'), document.getElementById('roleIds2'), ${command.wsaId});" title="删除角色" style="cursor: pointer;"/>
					</div>
					
					<div class="col-lg-4"  style="border: 1px red solid;">
						<div>已有角色</div>
						<div>
							<f:select path="roleIds" size="20" cssStyle="width: 250px;">
								<f:options items="${roleList1}" itemValue="roleId" itemLabel="roleName"/>
							</f:select>
						</div>
					</div>
					--%>
					
				</div>
				
		</div>
		<div class="form-group">
			<div><font color="red"><b>*</b></font>管理员权限：</div>
			<div><ul id="treeDemo" class="ztree"></ul></div>
		</div>
			
			<div class="form-group  ">
				<div class="col-lg-1"></div>
				<div class="col-lg-3">
					<button class="btn btn-info" onclick="addAdminSubmit();" >保存</button>
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
<script src="${pageContext.request.contextPath}/view1/menu/js/menu.js" ></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.core-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.excheck-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
	

</body>
</html>

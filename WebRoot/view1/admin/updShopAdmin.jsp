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
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">客户端管理员修改</a></div>
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
          	<f:hidden path="wsaPwdMd5"/>
          	<f:hidden path="wsaRegistor"/>
          	<f:hidden path="wsaRegistdate"/>
          	<f:hidden path="wsaLogindate"/>
          	<f:hidden path="wsaStatus"/>
 			<f:hidden path="currentPage"/>
  			<f:hidden path="pageSize"/>
  			<f:hidden path="menuIds"/>
            <div class="form-group">
            	<label for="wsaUsername" class="col-lg-1 control-label">用户名</label>
            	<div class="col-lg-3">
               	 	<f:input path="wsaUsername" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group">
            	<label for="wsaUsername" class="col-lg-1 control-label">密码</label>
            	<div class="col-lg-3">
               	 	<f:password path="wsaPwd" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group">
            	<label for="wsaUsername" class="col-lg-1 control-label">密码确认</label>
            	<div class="col-lg-3">
               	 	<input type="password" id="pwd2" name="pwd2" class="form-control input-ms"/>
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
                	<label><f:radiobutton path="wsaSex" value="1"/> 男士</label>
            		<label><f:radiobutton path="wsaSex" value="0"/> 女士</label>
            	</div>
            </div>
            
            <div class="form-group">
            	<label for="wsaSex" class="col-lg-1 control-label">部门</label>
            	<div class="col-lg-3">
            		<f:hidden path="wsaDept" />
                	<ul id="treeDemo1" class="ztree"></ul>
            	</div>
            </div>
			<div class="form-group">
				<label for="wsaSex" class="col-lg-1 control-label">角色设置：</label>
				<div class="col-lg-10">
					
					<select id="roleIds" name="roleIds" multiple="multiple" onchange="loadMenuTreeForAdmin(${command.wsaId});">
						<c:forEach items="${roleList1}" var = "role">
							<c:set var="selected" value="" />
							<c:if test="${role.selected != '0'}">
								<c:set var="selected" value="selected=selected" />
							</c:if>
							<option value="${role.wsrRoleId}" ${selected}>${role.wsrRoleName}</option>
						</c:forEach>
				    </select>
				    <%-- 
				    <f:select path="roleIds" multiple="true" size="20" cssStyle="width: 250px;" onchange="loadMenuTreeForAdmin(${command.wsaId});">
						<f:options items="${roleList1}" itemValue="wsrRoleId" itemLabel="wsrRoleName"/>
					</f:select>
					--%>
				</div>
			</div>
			<div class="form-group">
				<label for="treeDemo" class="col-lg-1 control-label">管理员权限：</label>
				<div class="col-lg-3"><ul id="treeDemo" class="ztree"></ul></div>
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
	
	
	var setting1 = {
		check: {
			chkStyle : "radio" ,
			enable: true,
			radioType : "all"
			
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
	$(document).ready(function(){
		$("#roleIds").select2();
		$('input[type=radio]').iCheck({
             radioClass: 	'iradio_minimal',
             increaseArea: 	'10%'
        });
		
		loadMenuTreeForAdmin(${command.wsaId});
		loadDeptTree("${pageContext.request.contextPath}/shop/dept/getDeptTreeForAdmin/${command.wsaId}", "treeDemo1", setting1);
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
<script src="${pageContext.request.contextPath}/view1/admin/js/admin.js" ></script>
<script src="${pageContext.request.contextPath}/view1/menu/js/menu.js" ></script>
<script src="${pageContext.request.contextPath}/view1/dept/js/dept.js" ></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.core-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.excheck-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/icheck/icheck.js" ></script>
	

</body>
</html>

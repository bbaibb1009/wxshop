<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-login.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view1/font-awesome/css/font-awesome.css" />
	<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' type='text/css'>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<jsp:include page="/view1/common/includeFiles.jsp"></jsp:include>
	  	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/login/js/login.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript">
			var path = "${pageContext.request.contextPath}";
			var alertMsg = "${alertMsg}";
			$(document).ready(function(){
				if( alertMsg.length>0)
				{
					alert(alertMsg);
				}
			});
		</script>
	<title>登录</title>
</head>
<body>
	<div id="loginbox">    
	        <f:form path="loginform"  action="${pageContext.request.contextPath}/admin/adminLogin" cssClass="form-vertical"  onsubmit="return false;">	

				 <div class="control-group normal_text"> <h3><img src="${pageContext.request.contextPath}/view1/img/logo.png" alt="Logo" /></h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><f:input path="wsaUsername" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><f:password path="wsaPwd" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">忘记密码?</a></span>
                    <span class="pull-right"><a type="button" href="javascript:void(0);" onclick="javascript:login();" class="btn btn-success">登陆</a></span>
                </div>
            </f:form>
            <form id="recoverform" action="#" class="form-vertical">
				<p class="normal_text">Enter your e-mail address below and we will send you instructions how to recover a password.</p>
				
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                        </div>
                    </div>
               
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; Back to login</a></span>
                    <span class="pull-right"><a class="btn btn-info"/>Reecover</a></span>
                </div>
            </form>
        </div>
        
        <script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script>  
        <script src="${pageContext.request.contextPath}/view1/js/matrix.login.js"></script> 


</body>
</html>
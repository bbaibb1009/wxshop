<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>会员资料修改</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/member/js/member.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery.autocomplete.min.js?v=${applicationScope.sysStartUpTime}"></script>
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
  </head>
  <body>
  	<div class="wrapper">
  	<jsp:include page="/view/login/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  	<f:form action="${pageContext.request.contextPath}/member/updMember" onsubmit="return false;">
  	<f:hidden path="wmbId"/>
  	<f:hidden path="wmbWecId"/>
  	<f:hidden path="wmbWcsId"/>
  	<f:hidden path="wmbCardId"/>
  	<f:hidden path="wmbOpenid"/>
  	<f:hidden path="wmbType"/>
  	<f:hidden path="wmbStatus"/>
  	<f:hidden path="wmbRegistor"/>
  	<f:hidden path="wmbRegistdate"/>
  	<f:hidden path="wmbWecId_Q"/>
  	<f:hidden path="currentPage"/>
  	<f:hidden path="pageSize"/>
  	<f:hidden path="wmbOpenid_Q"/>
  	<f:hidden path="wmbType_Q"/>
  	<f:hidden path="wmbName_Q"/>
  	<f:hidden path="wmbMobule_Q"/>
  	<f:hidden path="ShopAdminId_Q"/>
  	<f:hidden path="wmbWcsId_Q"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr><th width="3%">openId</th><td>${command.wmbOpenid}</td></tr>
    	<tr><th width="3%">卡号</th><td>${command.wmbCardId}</td></tr>
    	<tr><th width="5%">会员类型</th><td>${command.wmbType}</td></tr>
    	<tr><th width="4%">姓名</th><td><f:input path="wmbName"/></td></tr>
    	<tr><th width="8%">手机号码</th><td><f:input path="wmbMobule"/></td></tr>
    	<tr><th width="3%">状态</th><td>${command.wmbStatus}</td></tr>
    	<tr><th width="8%">备注</th><td><f:input path="wmbDesc"/></td></tr>
    	<tr><td colspan=2><input type="button" value="保存" onclick="javascript:updMemberSubmit();"/></td></tr>
    </table>
  
    </f:form>
    </div>
    </div>
    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>

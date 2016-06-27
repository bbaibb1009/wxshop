<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
  	<title>��ʾ��Ϣ</title>

  	<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/basic.js"></script>
    <script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	var msgCode = "${msgCode}";
	if( msgCode == "1" )
	{
		sysTimeout();
	}
	else if( msgCode == "3" )
	{
		alert("����IP��ַ�����ƣ�����ϵ����Ա");
		window.close();
	}
	
	$(document).ready(function(){
		if( msgCode == "2" )
		{
			$("#formHiddSpan").html('${formHidden}');
			alert("${alertMsg}");
			document.forms[0].submit();
		} 
	});
	</script>
  </head>
  
  <body>
  	<c:if test="${msgCode == 2}">
  	<div class="content">
  	<span id="formHiddSpan"></span>
  	</div>
    </c:if>
  </body>
</html>

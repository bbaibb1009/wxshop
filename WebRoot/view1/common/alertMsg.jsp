<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<c:if test="${success != null}">
	<div class="alert alert-success" style="display:none;">
		<button class="close" data-dismiss="alert">×</button>
		<strong>成功!</strong> ${success}
	</div>
</c:if> 
<c:if test="${error != null}">
	<div class="alert alert-danger " style="display:none;">
		<button class="close" data-dismiss="alert">×</button>
		<strong>错误!</strong> ${error}
	</div>
</c:if> 
<script>
	var speed = 1000;
  	$(".alert").fadeIn(speed);
  	
  	setTimeout(function () {
    	$(".alert").fadeOut(speed);
  	}, 3000);
</script>


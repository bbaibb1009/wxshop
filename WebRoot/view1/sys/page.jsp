<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<table class = "table ">
<tbody>
<tr>
<td class="span12">

<ul class="pagination">
	<c:choose>
	<c:when test="${pageResult == null || pageResult.totalRows == 0}">
	<li><a href="#">û����������������</a></li>
	</c:when>

	<c:otherwise>
		<c:choose>
			<c:when test="${pageResult.currentPage == 1}">
			<li><a href="javascript:goPage(1);"><i class="glyphicon glyphicon-step-backward"></i>��ҳ</a></li>
			<li><a href="javascript:goPage(${pageResult.previousPage});"><i class="glyphicon glyphicon-triangle-left"></i>��һҳ</a></li>
			</c:when>
			<c:otherwise>
			<li><a href="javascript:goPage(1);"><i class="glyphicon glyphicon-step-backward" ></i>��ҳ</a></li>
			<li><a href="javascript:goPage(${pageResult.previousPage});"><i class="glyphicon glyphicon-triangle-left"></i>��һҳ</a></li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${pageResult.currentPage == pageResult.totalPages}">
			<li><a href="javascript:goPage(${pageResult.nextPage });"><span class="glyphicon glyphicon-triangle-right" ></span>��һҳ</a></li>
			<li><a href="javascript:goPage(${pageResult.totalPages });"><i class="glyphicon glyphicon-step-forward"></i>βҳ</a></li>
			</c:when>
			<c:otherwise>
			<li><span><a href="javascript:goPage(${pageResult.nextPage });"><span class="glyphicon glyphicon-triangle-right" ></span>��һҳ</a></span></li>
			<li><a href="javascript:goPage(${pageResult.totalPages });"><i class="glyphicon glyphicon-step-forward"></i>βҳ</a></li>
			
			</c:otherwise>
		</c:choose>
		<li><span style="padding-top: 2px; padding-bottom: 0px; border-top-width: 1px; border-bottom-width: 1px; border-left-width: 1px; margin-top: 0px; margin-bottom: 0px; height: 37px;">��<input type="text" style="text-align: center;width: 20px; padding: 0px 1px 0px 0px; margin-bottom: 5px; margin-top: 3px; border-top-width: 0px; border-right-width: 0px; border-left-width: 0px;" value="${pageResult.currentPage}" name="currentPage" id="currentPage">ҳ</span></li>
		<li><span>��${pageResult.totalPages}ҳ</span></li>
		<li><span style="padding-top: 2px; padding-bottom: 0px; border-top-width: 1px; border-bottom-width: 1px; border-left-width: 1px; margin-top: 0px; margin-bottom: 0px; height: 37px;">ÿҳ��ʾ<input type="text" style="text-align: center;width: 20px; padding: 0px 1px 0px 0px; margin-bottom: 5px; margin-top: 3px; border-top-width: 0px; border-right-width: 0px; border-left-width: 0px;" value="${pageResult.pageSize}" name="pageSize" id="pageSize">��</span></li>
		<li><span>��${pageResult.totalRows}������</span></li>
		<li><a href="javascript:goPage(document.getElementById('currentPage').value);">ˢ��</a></li>

	</c:otherwise>
</c:choose>
</ul>

</td>
</tr>
</tbody>
</table>



<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<table class = "table ">
<tbody>
<tr>
<td class="span12">

	<c:choose>
	<c:when test="${pageResult == null || pageResult.totalRows == 0}">
		<tr>
       		<td class="text-center small">没有满足条件的数据</td>
    	</tr>
	</c:when>

	<c:otherwise>
		<ul class="pagination">
		<c:choose>
			<c:when test="${pageResult.currentPage == 1}">
			<li><a href="javascript:goPage(1);"><i class="glyphicon glyphicon-step-backward"></i>首页</a></li>
			<li><a href="javascript:goPage(${pageResult.previousPage});"><i class="glyphicon glyphicon-triangle-left"></i>上一页</a></li>
			</c:when>
			<c:otherwise>
			<li><a href="javascript:goPage(1);"><i class="glyphicon glyphicon-step-backward" ></i>首页</a></li>
			<li><a href="javascript:goPage(${pageResult.previousPage});"><i class="glyphicon glyphicon-triangle-left"></i>上一页</a></li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${pageResult.currentPage == pageResult.totalPages}">
			<li><a href="javascript:goPage(${pageResult.nextPage });"><span class="glyphicon glyphicon-triangle-right" ></span>下一页</a></li>
			<li><a href="javascript:goPage(${pageResult.totalPages });"><i class="glyphicon glyphicon-step-forward"></i>尾页</a></li>
			</c:when>
			<c:otherwise>
			<li><span><a href="javascript:goPage(${pageResult.nextPage });"><span class="glyphicon glyphicon-triangle-right" ></span>下一页</a></span></li>
			<li><a href="javascript:goPage(${pageResult.totalPages });"><i class="glyphicon glyphicon-step-forward"></i>尾页</a></li>
			
			</c:otherwise>
		</c:choose>
		<li><span style="padding-top: 2px; padding-bottom: 0px; border-top-width: 1px; border-bottom-width: 1px; border-left-width: 1px; margin-top: 0px; margin-bottom: 0px; height: 37px;">第<input type="text" style="text-align: center;width: 20px; padding: 0px 1px 0px 0px; margin-bottom: 5px; margin-top: 3px; border-top-width: 0px; border-right-width: 0px; border-left-width: 0px;" value="${pageResult.currentPage}" name="currentPage" id="currentPage">页</span></li>
		<li><span>共${pageResult.totalPages}页</span></li>
		<li><span style="padding-top: 2px; padding-bottom: 0px; border-top-width: 1px; border-bottom-width: 1px; border-left-width: 1px; margin-top: 0px; margin-bottom: 0px; height: 37px;">每页显示<input type="text" style="text-align: center;width: 20px; padding: 0px 1px 0px 0px; margin-bottom: 5px; margin-top: 3px; border-top-width: 0px; border-right-width: 0px; border-left-width: 0px;" value="${pageResult.pageSize}" name="pageSize" id="pageSize">条</span></li>
		<li><span>共${pageResult.totalRows}条数据</span></li>
		<li><a href="javascript:goPage(document.getElementById('currentPage').value);">刷新</a></li>
		</ul>
	</c:otherwise>
</c:choose>


</td>
</tr>
</tbody>
</table>



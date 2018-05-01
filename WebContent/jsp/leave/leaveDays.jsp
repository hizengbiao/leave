<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
</head>
<body>

<form action="<c:url value='/LeaveServlet'/>" method="get">
<input type="hidden" name="method" value="leaveDays"/>
<table border="0" align="center" width="40%" style="margin-left: 100px;">
	<tr>
		<td width="100px">工号</td>
		<td width="40%">
			<input type="text" name="wIds"/>
		</td>

		<td>姓名</td>
		<td>
			<input type="text" name="wNames"/>
		</td>
		
		<td>年份</td>
		<td>
			<input type="text" name="year"/>
		</td>

				
	</tr>

	<tr>
		
	</tr>
		<tr>
		<td>&nbsp;</td>
		<td>
			<input type="submit" value="搜索"/>
			<input type="reset" value="重置"/>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
<font style="color:red;"> ${msg }</font>
<h3 align="center">${requestScope.year }个人请假天数列表</h3>
<table border="1" width="70%" align="center">
	<tr>
		<th>工号</th>
		<th>姓名</th>	
		<th>请假天数</th>
	</tr>
	<c:forEach var="list" items="${requestScope.allMsg.pagelist }">
	<tr>
		<td align="left">${list.wId }</td>
		<td align="center">${list.wName }</td>
		<td align="center">${list.leaveDays }</td>
		
		
	</tr>
	</c:forEach>
</table>
<center>
	第${requestScope.allMsg.pageCode }页/共${requestScope.allMsg.tp }页
	<a href="${requestScope.allMsg.url }&pc=1">首页</a>
	<c:if test="${requestScope.allMsg.pageCode > 1 }">
	<a href="${requestScope.allMsg.url }&pc=${requestScope.allMsg.pageCode-1 }">上一页</a>
	</c:if>
	
	<c:choose>
		<c:when test="${requestScope.allMsg.tp<10 }">
			<c:set var="begin" value="1"></c:set>
			<c:set var="end" value="${requestScope.allMsg.tp }"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="begin" value="${requestScope.allMsg.pageCode-5 }"></c:set>
			<c:set var="end" value="${requestScope.allMsg.pageCode+4 }"></c:set>
			
			<%--溢出问题 --%>
			<c:if test="${begin<1 }">
				<c:set var="begin" value="1"></c:set>
				<c:set var="end" value="10"></c:set>
			</c:if>
			
			<c:if test="${end>requestScope.allMsg.tp }">
			<c:set var="begin" value="${requestScope.allMsg.tp-9 }"></c:set>
			<c:set var="end" value="${requestScope.allMsg.tp }"></c:set>
				
			</c:if>
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${begin }" end="${end }">
	<c:choose>
	<c:when test="${i eq requestScope.allMsg.pageCode }">
		${i }
	</c:when>
	<c:otherwise>
		<a href="${requestScope.allMsg.url }&pc=${i }">${i }</a>
	</c:otherwise>
	</c:choose>
		
	</c:forEach>
	
	
	
	<c:if test="${requestScope.allMsg.pageCode < requestScope.allMsg.tp }">
	<a href="${requestScope.allMsg.url }&pc=${requestScope.allMsg.pageCode+1 }">下一页</a>
	</c:if>
	<a href="${requestScope.allMsg.url }&pc=${requestScope.allMsg.tp }">尾页</a>
</center>
</body>

</html>
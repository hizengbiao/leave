<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
</head>
<body>
<font style="color:red;"> ${msg }</font>
<h3 align="center">部门列表</h3>
<table border="1" width="70%" align="center">
	<tr>
		<th>名称</th>
	</tr>
	<c:forEach var="list" items="${sectionList }">
	<tr>
		<td>${list.sName }</td>
		<td>
			<a href="<c:url value='/SectionServlet?method=delete&id=${list.id }'/>">删除</a>
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>
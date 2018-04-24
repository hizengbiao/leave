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
<h3 align="center">院系列表</h3>
<table border="1" width="70%" align="center">
	<tr>
		<th>院系名称</th>
		<th>所属部门</th>
	</tr>
	<c:forEach var="list" items="${departmentList }">
	<tr>
		<td>${list.dName }</td>
		<td>${list.sName }</td>
		<td>
			<a href="<c:url value='/DepartmentServlet?method=delete&id=${list.id }'/>">删除</a>
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>
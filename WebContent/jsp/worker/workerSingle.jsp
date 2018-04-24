<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
</head>
<body>

<table border="1" width="70%" align="center">
<tr>
		<th>工号</th>
		<th>姓名</th>
		<th>性别</th>
		<th>固定电话</th>
		<th>手机</th>
		<th>职务</th>
		<th>部门</th>
		<th>院系</th>
		<th>权限</th>
	</tr>
	<tr>
		<td>${workerSingle.wId }</td>
		<td>${workerSingle.wName }</td>
		<td>${workerSingle.wSex }</td>
		<td>${workerSingle.wTel }</td>
		<td>${workerSingle.wPhone }</td>
		<td>${workerSingle.duName }</td>
		<td>${workerSingle.sName }</td>
		<td>${workerSingle.dName }</td>
		<td>
			<c:if test="${workerSingle.auth eq 1 }">教职工</c:if>
			<c:if test="${workerSingle.auth eq 2 }">审核员</c:if>
			<c:if test="${workerSingle.auth eq 3 }">管理员</c:if>
		</td>
		
	</tr>
</table>
</body>
</html>
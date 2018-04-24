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
<form action="<c:url value='/DepartmentServlet'/>" method="post">
<input type="hidden" name="method" value="insert"/>
<table border="0" align="center" width="40%" style="margin-left: 100px;">
	<tr>
		<td width="100px">院系名称</td>
		<td width="40%">
			<input type="text" name="dName"/>
		</td>
	</tr>
	<tr>
	    <td width="10%">部门：</td>
	    <td width="20%">
	    	<select name="sId"  >
			    <option value="">----请--选--择----</option>
			    <c:forEach var="section" items="${sectionList }">
			    	<option value="${section.id }">${section.sName }</option>
			    </c:forEach>
			</select>
	    </td>
	     </tr>
	
		<tr>
		<td>&nbsp;</td>
		<td>
			<input type="submit" value="添加"/>
			<input type="reset" value="重置"/>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
	
</body>


</html>
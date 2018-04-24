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
<form action="<c:url value='/WorkerServlet'/>" method="post">
<input type="hidden" name="method" value="modifyPass"/>
<table border="0" align="center" width="40%" style="margin-left: 100px;">
	<tr>
		<td width="100px">原密码</td>
		<td width="40%">
			<input type="text" name="oldPass" required="required"/>
		</td>
	</tr>
		<tr>
		<td>新密码</td>
		<td>
			<input type="text" name="newPass" required="required"/>
		</td>	
	</tr>
		<tr>
		<td>&nbsp;</td>
		<td>
			<input type="submit" value="修改"/>
			<input type="reset" value="重置"/>
		</td>
		<td>&nbsp;</td>
	</tr>

</table>
</form>

<script type="text/javascript">
	<c:if test="${msg == '1'}">
		parent.location.href="../../leave/login.jsp";
	</c:if>
</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
</head>
<body>
<p style="color: red; font-weight: 900">${msg }</p>
        <FORM action="<c:url value='/WorkerServlet'/>" method="post">
            <input type="hidden" name="method" value="login"/>
            用户名：<input type="text"  name="wId" value="">
            <span style="color: red; font-weight: 900">${errors.wId }</span><br/>
            密  码：<input type="password"  name="wPassword" value="">
            <span style="color: red; font-weight: 900">${errors.wPassword }</span><br/>
            <input type="submit" name="button" value="登录"><br/>
              </FORM>
</body>
</html>
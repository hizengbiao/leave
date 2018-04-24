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
        <FORM action="<c:url value='/AdministerServlet'/>" method="post">
            <input type="hidden" name="method" value="login"/>
            用户名：<input type="text"  name="aId" value="">
            <span style="color: red; font-weight: 900">${errors.aId }</span><br/>
            密  码：<input type="password"  name="aPassword" value="">
            <span style="color: red; font-weight: 900">${errors.aPassword }</span><br/>
            <input type="submit" name="button" value="登录"><br/>
              </FORM>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
    	
    <script type="text/javascript" src="<c:url value='/jquery/jquery-1.4.2.js'/>"></script>
   <link  rel="stylesheet" href="<c:url value='/jquery/jquery.datepick.css'/>" type="text/css">
   
    <script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
</head>
 <script type="text/javascript">
	
		$(document).ready(function(){
			//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
			$('#startTime').datepick({dateFormat: 'yy-mm-dd'}); 
			$('#endTime').datepick({dateFormat: 'yy-mm-dd'});
		});
	</script>   
<body>
<font style="color:red;"> ${msg }</font>
<form action="<c:url value='/LeaveServlet'/>" method="post">
<input type="hidden" name="method" value="insert"/><br/>
请假开始时间<input type="text" name="startTime" size="20" readonly="readonly" id="startTime" /><br/>
请假结束时间<input type="text" name="endTime" size="20" readonly="readonly" id="endTime" /><br/>
原因<textarea rows="10" cols="10" name="remark"></textarea><br/>
<input type="submit" value="添加"/>
<input type="reset" value="重置"/>
</form>
					
</body>				
</html>
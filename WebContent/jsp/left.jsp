<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
var bar1 = new Q6MenuBar("bar1", "ITCAST网络图书商城");
function load() {
	bar1.colorStyle = 2;
	bar1.config.imgDir = "<c:url value='/menu/img/'/>";
	bar1.config.radioButton=false;
	<c:if test="${user.auth eq 1 }">
	bar1.add("个人信息管理", "查看个人信息", "<c:url value='/WorkerServlet?method=queryOne'/>", "body");
	bar1.add("个人信息管理", "修改密码", "<c:url value='/jsp/worker/modifyPass.jsp'/>", "body");
	bar1.add("请假信息管理", "请假", "<c:url value='/jsp/leave/addLeave.jsp'/>", "body");
	bar1.add("请假信息管理", "查看请假记录", "<c:url value='/LeaveServlet?method=findCd'/>", "body");

	</c:if>
	<c:if test="${user.auth eq 2 }">
	bar1.add("个人信息管理", "查看个人信息", "<c:url value='/WorkerServlet?method=queryOne'/>", "body");
	bar1.add("个人信息管理", "修改密码", "<c:url value='/jsp/worker/modifyPass.jsp'/>", "body");
	bar1.add("请假信息管理", "查看请假记录", "<c:url value='/LeaveServlet?method=findCd1'/>", "body");

	
	</c:if>
	  <c:if test="${user.auth eq 3 }">
	  bar1.add("教职工信息管理", "添加教职工", "<c:url value='/WorkerServlet?method=preInsert'/>", "body");
		bar1.add("教职工信息管理", "查看教职工", "<c:url value='/WorkerServlet?method=findCd'/>", "body");
		bar1.add("部门管理", "添加部门", "<c:url value='/jsp/section/addSection.jsp'/>", "body");
		bar1.add("部门管理", "查看部门", "<c:url value='/SectionServlet?method=query'/>", "body");
		bar1.add("院系管理", "添加院系", "<c:url value='/DepartmentServlet?method=preInsert'/>", "body");
		bar1.add("院系管理", "查看院系", "<c:url value='/DepartmentServlet?method=query'/>", "body");
		bar1.add("请假信息管理", "查看请假记录", "<c:url value='/LeaveServlet?method=findCd1'/>", "body");

	  </c:if>
	

	
	var d = document.getElementById("menu");
	d.innerHTML = bar1.toString();
}
</script>

</head>

<body onload="load()" style="margin: 0px; background: rgb(254,238,189);">
<div id="menu"></div>

</body>
</html>

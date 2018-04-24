<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
</head>
<body>

<form action="<c:url value='/WorkerServlet'/>" method="get">
<input type="hidden" name="method" value="findCd"/>
<table border="0" align="center" width="40%" style="margin-left: 100px;">
	<tr>
		<td width="100px">工号</td>
		<td width="40%">
			<input type="text" name="wId"/>
		</td>

		<td>姓名</td>
		<td>
			<input type="text" name="wName"/>
		</td>

		<td>固定电话</td>
		<td>
			<input type="text" name="wTel"/>
		</td>		

		<td>手机</td>
		<td>
			<input type="text" name="wPhone"/>
		</td>		
	</tr>

	<tr>
		<td width="8%">职务：</td>
	    <td width="62%">
	    	<select name="dId" id="postSelectId">
			    <option >----请--选--择----</option>
			    <c:forEach var="duty" items="${dutyList }">
			    	<option value="${duty.id }">${duty.dName }</option>
			    </c:forEach>			    
			</select>
	    </td>

	    <td width="10%">部门：</td>
	    <td width="20%">
	    	<select name="sId"  onchange="changePost(this)">
			    <option >----请--选--择----</option>
			    <c:forEach var="section" items="${sectionList }">
			    	<option value="${section.id }">${section.sName }</option>
			    </c:forEach>
			</select>
	    </td>

	    <td width="10%">院系：</td>
	    <td width="20%">
	    	<select name="departmentId"  id="departmentId" >
			   </select>

	    </td>

		<td>权限</td>
		<td>
			<select name="auth">
				<option >==请选择==</option>
				<option value="1">教职工</option>
				<option value="2">审核员</option>
			</select>
		</td>
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
<h3 align="center">职工列表</h3>
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
	<c:forEach var="list" items="${requestScope.allMsg.pagelist }">
	<tr>
		<td>${list.wId }</td>
		<td>${list.wName }</td>
		<td>${list.wSex }</td>
		<td>${list.wTel }</td>
		<td>${list.wPhone }</td>
		<td>${list.duName }</td>
		<td>${list.sName }</td>
		<td>${list.dName }</td>
		<td>
			<c:if test="${list.auth eq 1 }">教职工</c:if>
			<c:if test="${list.auth eq 2 }">审核员</c:if>
			<c:if test="${list.auth eq 3 }">管理员</c:if>
		</td>
		<td>
			<a href="<c:url value='/WorkerServlet?method=preEdit&id=${list.id }'/>">编辑</a>
			<a href="<c:url value='/WorkerServlet?method=delete&id=${list.id }'/>">删除</a>
		</td>
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
<script>
var tempPostId = null;
function changePost(departmentObj,departmentId){
	//1 选择的部门id
	var depId = departmentObj.value;
	tempPostId = departmentId;
	
	//2 发送ajax 通过部门id 查询对应职务
	var url = "${pageContext.request.contextPath}/WorkerServlet?method=ajax&sid=" + depId;
	
	//2.1 创建核心对象
	var xmlhttp=null;
	if (window.XMLHttpRequest) {
		xmlhttp=new XMLHttpRequest();
	}else if (window.ActiveXObject) {// code for IE5 and IE6
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	// 2.2 设置回调函数，当ajax请求完成之后，进行处理
	xmlhttp.onreadystatechange = function(){
		// * 发送成功， 并发送的正常页面
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			var postSelectObject = document.getElementById("departmentId");
			postSelectObject.innerHTML = "<option>----请--选--择----</option>";
			
			//获得数据 json，并处理
			//@1 获得普通文本数据
			var textData = xmlhttp.responseText;
			//@2 将文本转换成json数据  eval()  ,但兼容  eval("("+...+")")
			var jsonData = eval("("+textData+")");
			//@3 遍历数据，将数据添加到“职务”select
			for(var i = 0 ; i < jsonData.length ; i ++){
				var postObj = jsonData[i];
				// @3.1 编号
				var postId = postObj.id;
				// @3.2 名称
				var postName = postObj.dName;
				// @3.3 追加
				postSelectObject.innerHTML = postSelectObject.innerHTML + "<option value='"+postId+"'>"+postName+"</option>";
			}
		}
	};
	
	// 2.3 打开连接 ,以get请求方式发送数据
	xmlhttp.open("get",url);
	
	// 2.4 发送 , 没有请求体的内容，设置成null
	xmlhttp.send(null);
}
</script>
</html>
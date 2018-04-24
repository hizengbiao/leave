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
<input type="hidden" name="method" value="insert"/>
<table border="0" align="center" width="40%" style="margin-left: 100px;">
	<tr>
		<td width="100px">工号</td>
		<td width="40%">
			<input type="text" name="wId" required="required"/>
		</td>
	</tr>
	<tr>
		<td>职工姓名</td>
		<td>
			<input type="text" name="wName" required="required"/>
		</td>
	</tr>
	<tr>
		<td>职工性别</td>
		<td>
			<select name="wSex" required="required">
				<option value="">==请选择性别==</option>
				<option value="男">男</option>
				<option value="女">女</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>固定电话</td>
		<td>
			<input type="text" name="wTel" required="required"/>
		</td>		
	</tr>
	<tr>
		<td>手机</td>
		<td>
			<input type="text" name="wPhone" required="required"/>
		</td>		
	</tr>
	<tr>
		<td>初始密码</td>
		<td>
			<input type="text" name="wPassword" required="required"/>
		</td>
	</tr>

	<tr>
		<td width="8%">职务：</td>
	    <td width="62%">
	    	<select name="dId" id="postSelectId">
			    <option value="">----请--选--择----</option>
			    <c:forEach var="duty" items="${dutyList }">
			    	<option value="${duty.id }">${duty.dName }</option>
			    </c:forEach>			    
			</select>
	    </td>
	    </tr>
	    <tr>
	    <td width="10%">部门：</td>
	    <td width="20%">
	    	<select name="sId"  onchange="changePost(this)">
			    <option value="">----请--选--择----</option>
			    <c:forEach var="section" items="${sectionList }">
			    	<option value="${section.id }">${section.sName }</option>
			    </c:forEach>
			</select>
	    </td>
	     </tr>
	    <tr>
	    <td width="10%">院系：</td>
	    <td width="20%">
	    	<select name="departmentId"  id="departmentId" >
			   </select>

	    </td>
	  </tr>
	  <tr>
		<td>权限</td>
		<td>
			<select name="auth" required="required">
				<option value="">==请选择==</option>
				<option value="1">教职工</option>
				<option value="2">审核员</option>
			</select>
		</td>
	</tr>
		<tr>
		<td>&nbsp;</td>
		<td>
			<input type="submit" value="提交"/>
			<input type="reset" value="重置"/>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
	
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
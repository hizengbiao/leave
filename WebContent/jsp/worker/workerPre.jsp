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
<input type="hidden" name="method" value="update"/>
<table border="0" align="center" width="40%" style="margin-left: 100px;">
	<input type="hidden" name="id" value="${workerInfo.id }"/>
	<tr>
		<td width="100px">工号</td>
		<td width="40%">
			<input type="text" name="wId" value="${workerInfo.wId }"/>
		</td>
	</tr>
	<tr>
		<td>客户姓名</td>
		<td>
			<input type="text" name="wName" value="${workerInfo.wName }"/>
		</td>
	</tr>
	<tr>
		<td>客户性别</td>
		<td>
			<select name="wSex">
				<option value="">==请选择性别==</option>
				<option value="男" 
					<c:if test="${workerInfo.wSex == '男' }">
						selected="selected"
					</c:if>
				>男</option>
				<option value="女"
					<c:if test="${workerInfo.wSex == '女' }">
						selected="selected"
					</c:if>
				> 女</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>固定电话</td>
		<td>
			<input type="text" name="wTel" value="${workerInfo.wTel }"/>
		</td>		
	</tr>
	<tr>
		<td>手机</td>
		<td>
			<input type="text" name="wPhone" value="${workerInfo.wPhone }"/>
		</td>		
	</tr>
	<tr>
		<td>密码</td>
		<td>
			<input type="text" name="wPassword"  value="${workerInfo.wPassword }"/>
		</td>
	</tr>

	<tr>
		<td width="8%">职务：</td>
	    <td width="62%">
	    	<select name="dId" id="postSelectId">
			    <option value="">----请--选--择----</option>
			    <c:forEach var="duty" items="${dutyList }">
			    	<option value="${duty.id }"
			    	<c:if test="${workerInfo.dId == duty.id }">
						selected="selected"
					</c:if>
			    	>${duty.dName }</option>
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
			    	<option value="${section.id }"
			    	<c:if test="${workerInfo.sId == section.id }">
						selected="selected"
					</c:if>
			    	>${section.sName }</option>
			    </c:forEach>
			</select>
	    </td>
	     </tr>
	    <tr>
	    <td width="10%">院系：</td>
	    <td width="20%">
	    	<select name="departmentId"  id="departmentId" >
			   <option value="">----请--选--择----</option>
			    <c:forEach var="dep" items="${departList }">
			    	<option value="${dep.id }"
			    	<c:if test="${workerInfo.departmentId == dep.id }">
						selected="selected"
					</c:if>
			    	>${dep.dName }</option>
			    </c:forEach>
			</select>

	    </td>
	  </tr>
	  <tr>
		<td>权限</td>
		<td>
			<select name="auth">
				<option value="0">==请选择==</option>
				<option value="0"
				<c:if test="${workerInfo.auth eq 1 }">
						selected="selected"
					</c:if>
				>教职工</option>
				<option value="1"
				<c:if test="${workerInfo.auth eq 2 }">
						selected="selected"
					</c:if>
				>审核员</option>
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
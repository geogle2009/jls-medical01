<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="page/css/table-style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="page/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
function showrecharge(){  
	$("#recharge").html("救助金发放名称:<input name=\"subject\" type=\"text\" size=\"40\" />每账户充入金额<input name=\"money\" type=\"text\" size=\"10\" id=\"income\" /><br/><input name=\"submit\" type=\"submit\" value=\"确定\" />");
}
function showzeroclearing(){ 
	$("#recharge").html("救助对象账户清零原因:<input name=\"subject\" type=\"text\" size=\"40\" /><br/><input name=\"submit\" type=\"submit\" value=\"确定\"/>"); 
} 
</script>
</head>
<body>
<table width="100%" class="t1">
	<tr>
		<th height="35" colspan="4">账目信息</th>
	</tr>
	<tr>
		<th width="120">总救助人次</th>
		<td><s:property value="statinfo.zrc" /></td>
		<th width="120">总消费金额</th>
		<td><s:property value="statinfo.zxf" /></td>
	</tr>
	<tr>
		<th width="120">年度总救助人次</th>
		<td><s:property value="statinfo.ndzrc" /></td>
		<th width="120">年度总消费金额</th>
		<td><s:property value="statinfo.ndzxf" /></td>
	</tr>
	<tr>
		<th width="120">余额总和</th>
		<td><s:property value="statinfo.zbal" /></td>
		<th width="120">享受救助人数</th>
		<td><s:property value="statinfo.dqrs" /></td>
	</tr>
</table>
<s:form action="/page/business/chronic/saveGeneratebill" method="post"
	theme="simple">
	<p><input type="radio" name="opt" value="0"
		onclick="showzeroclearing();" /> 救助对象账户清零 <input type="radio"
		name="opt" value="1" onClick="showrecharge()" />救助对象账户充值 <input
		type="radio" name="opt" value="2" onClick="showrecharge()" />救助对象账户清零并充值
	</p>
	<p id="recharge"></p>
</s:form>
</body>
</html>

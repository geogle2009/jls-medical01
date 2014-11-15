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
<link rel="stylesheet" href="page/css/table-style.css" type="text/css"></link>
<title></title>
</head>
<body>
<br></br>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>慢性病名称</th>
		<th>当前城市人数</th>
		<th>当前农村人数</th>
		<th>总人数</th>
	</tr>
	<s:iterator value="cas">
		<tr>
			<td><s:property value="address" /></td>
		 
			<td><s:property value="apridea" /></td>
		 
			<td><s:property value="apridea1" /></td>
			<td><s:property value="apridea3" /></td>
		</tr>
	</s:iterator>
</table>
<div align="center">
<button
		onclick="window.open('../downloadExcel.action?type=6')">导出excel</button>
</div>
</body>
</html>
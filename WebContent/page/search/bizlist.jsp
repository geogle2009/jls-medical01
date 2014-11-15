<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!--
author:Administrator
create time:2009-9-5
-->
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>救助对象列表</title>
</head>
<body>
<table align="center" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th scope="col">姓名</th>
		<th scope="col">家庭编号</th>
		<th scope="col">就医时间</th>
		<th scope="col">就诊医院</th>
		<th scope="col">总费用</th>
		<th scope="col">救助金额</th>
		<th scope="col">个人承担</th>
	</tr>
	<s:iterator value="result">
		<tr>
			<td><s:property value="membername" /></td>
			<td><s:property value="familyno" /></td>
			<td><s:property value="beginDate1" /></td>
			<td><s:property value="hospitalName" /></td>
			<td><s:property value="allmoney" /></td>
			<td><s:property value="assistpay" /></td>
			<td><s:property value="personpay" /></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>
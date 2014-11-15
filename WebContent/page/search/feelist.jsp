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
<title>购药明细</title>
</head>
<body>
<table align="center" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th scope="col">药店</th>
		<th scope="col">名称</th>
		<th scope="col">单价</th>
		<th scope="col">数量</th>
		<th scope="col">费用</th>
		<th scope="col">购买时间</th>
	</tr>
	<s:iterator value="feelist">
		<tr>
			<td><s:property value="hname" /></td>
			<td><s:property value="itemName" /></td>
			<td><s:property value="price" /></td>
			<td><s:property value="dosage" /></td>
			<td><s:property value="money" /></td>
			<td><s:date name="operTime" format="yyyy-MM-dd:hh:mm:ss" /></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>
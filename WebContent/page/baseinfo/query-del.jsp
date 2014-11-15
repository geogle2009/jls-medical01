<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<!--
author:Administrator
create time:2009-9-1
-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除救助对象</title>
</head>
<body>
<form action="query-del.jsp" method="post" name="inputform">&nbsp;&nbsp;&nbsp;&nbsp;姓名：<input
	name="membername" value="" type="text" />&nbsp;&nbsp;&nbsp;&nbsp;证件类型：<select
	name="papertype">
	<option value="idcard">身份证</option>
	<option value="idcard">军人证</option>
	<option value="idcard">其他</option>
</select>&nbsp;&nbsp;&nbsp;&nbsp;证件号码：<input name="paperid" value="" type="text" />&nbsp;&nbsp;<input
	type="submit" value="查询" /></form>
<br>
<table align="left" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>证件类型</th>
		<th>证件号码</th>
		<th>社会保险号</th>
		<th>参保情况</th>
		<th>操作</th>
	</tr>
</table>
</body>
</html>
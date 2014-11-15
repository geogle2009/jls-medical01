<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增救助对象</title>
</head>
<body>
<form action="family.jsp" method="post" name="inputform">
<table align="center" width="400" class="t1" border="0" cellpadding="0"
	cellspacing="0" class="t1">
	<tr>
		<th colspan="2">查询救助对象</th>
	</tr>
	<tr>
		<td>姓名：</td>
		<td><input name="membername" value="" type="text" /></td>
	</tr>
	<tr>
		<td>证件类型：</td>
		<td><select name="papertype">
			<option value="idcard">身份证</option>
			<option value="idcard">军人证</option>
			<option value="idcard">其他</option>
		</select></td>
	</tr>
	<tr>
		<td>证件类型：</td>
		<td><input name="paperid" value="" type="text" /></td>
	</tr>
	<tr>
		<td colspan="2">
		<div align="center"><input type="submit" value="查询" /></div>
		</td>
	</tr>
</table>
</form>
</body>
</html>
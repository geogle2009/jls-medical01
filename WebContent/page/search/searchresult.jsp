<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

<table align="center" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th scope="col">姓名</th>
		<th scope="col">家庭编号</th>
		<th scope="col">社会保障号</th>
		<th scope="col">身份证</th>
		<th scope="col">就医时间</th>
		<th scope="col">医疗机构</th>
		<th scope="col">结算时间</th>
		<th scope="col">确认诊断名称</th>
		<th scope="col">医疗救助金</th>
		<th scope="col">统筹</th>
		<th scope="col">总费用</th>
	</tr>
	<s:iterator value="result">
		<tr>
			<td><s:property value="membername" /></td>
			<td><s:property value="familyno" /></td>
			<td><s:property value="ssn" /></td>
			<td><s:property value="paperid" /></td>
			<td><s:date name="beginDate1" format="yyyy-MM-dd" /></td>
			<td><s:property value="hospitalName" /></td>
			<td><s:property value="finDate" /></td>
			<td><s:property value="diagnoseName" /></td>
			<td><s:property value="assistpay" /></td>
			<td><s:property value="insurancepay" /></td>
			<td><s:property value="allmoney" /></td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="toolsmenu" escape="false" /></div>
</body>
</html>
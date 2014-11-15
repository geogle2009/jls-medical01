<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="emeCheckDTO.membername" />走访调查情况表</title>
</head>
<body>
<s:form theme="simple" action="saveInterview" method="post"
	cssStyle="font-size:12px">
	<s:hidden name="emeCheckDTO.ssn"></s:hidden>
	<s:hidden name="emeCheckDTO.memberId"></s:hidden>
	<table align="center" width="90%" class="t1" border="0" cellpadding="0"
		cellspacing="0">
		<caption>填写走访调查情况表</caption>
		<tr>
			<th width="180px">家庭编号</th>
			<td><s:property value="emeCheckDTO.familyno" /></td>
		</tr>
		<tr>
			<th>救助人姓名</th>
			<td><s:property value="emeCheckDTO.membername" /></td>
		</tr>
		<tr>
			<th>家庭住址</th>
			<td><s:property value="emeCheckDTO.address" /></td>
		</tr>
		<tr>
			<th>救助原因</th>
			<td><s:textfield name="emeCheckDTO.reliefbecause" cssStyle="width:100%"></s:textfield>
			</td>
		</tr>
		<tr>
			<th>走访情况</th>
			<td><s:textarea name="emeCheckDTO.interview" rows="6"
				cssStyle="width:100%"></s:textarea></td>
		</tr>
	</table>
	<p align="center">
		<s:submit value="保存"></s:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="window.close()">关闭</button>
	</p>
</s:form>
</body>
</html>
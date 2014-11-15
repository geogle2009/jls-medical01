<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日常救助</title>
</head>
<body>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>社会保险号</th>
		<th>业务类型</th>
		<th>操作</th>
	</tr>
	<s:iterator value="personlist">
		<s:url action="approveDailyMedicalInit" id="hand">
			<s:param name="type">
				<s:property value="type" />
			</s:param>
			<s:param name="assistype">
				<s:property value="assisTypeId" />
			</s:param>
			<s:param name="ssn">
				<s:property value="ssn" />
			</s:param>
		</s:url>
		<tr>
			<td><s:property value="familyno" /></td>
			<td><s:property value="membername" /></td>
			<td><s:property value="ssn" /></td>
			<td><s:property value="assistType" /></td>
			<td><s:if test="%{ssn==null||ssn==''}">
				没有社会救助号码
			</s:if> <s:else>
				<s:a href="%{hand}">填写</s:a>
			</s:else></td>
		</tr>
	</s:iterator>
</table>
<p align="center">
<button onclick="history.go(-1)">返回</button>
</p>
</body>
</html>
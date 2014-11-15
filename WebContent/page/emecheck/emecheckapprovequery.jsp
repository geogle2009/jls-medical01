<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>临时救助</title>
</head>
<body>
<s:form theme="simple" action="showEmeCheckApproves" method="post"
	cssStyle="font-size:12px">
	查询条件：
	<s:select value="term" name="term"
		list="#{'':'全部','SSN':'社会保险号','FAMILYNO':'家庭编号','MEMBERNAME':'姓名','PAPERID':'身份证号'}"
		label="查询条件：" listKey="key" listValue="value">
	</s:select>
	操作符：
	<s:select value="operational" name="operational"
		list="#{'=':'等于','like':'相似于'}" label="操作符：" listKey="key"
		listValue="value">
	</s:select>
	查询值：
	<s:textfield name="value"></s:textfield>
	<s:submit value="查询"></s:submit>
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>身份证号</th>
		<th>社会保险号</th>
		<th>街道初审结果</th>
		<th>区县审批结果</th>
		<th>救助金额</th>
		<th>操作</th>
	</tr>
	<s:iterator value="emes">
		<tr>
			<td><s:property value="familyno" /></td>
			<td><s:property value="membername" /></td>
			<td><s:property value="paperid" /></td>
			<td><s:property value="ssn" /></td>
			<td><s:if test="resultofstreet==0">
			未审批
			</s:if><s:if test="resultofstreet==1">
			同意
			</s:if><s:if test="resultofstreet==2">
			不同意
			</s:if></td>
			<td><s:if test="resultofareg==0">
			未审批
			</s:if><s:if test="resultofareg==1">
			同意
			</s:if><s:if test="resultofareg==2">
			不同意
			</s:if></td>
			<td><s:property value="salmoney" /></td>
			<td>
			<s:if test="pagetype==2">
			<a href="#"
				onclick="window.open('emecheckapproves.action?emecheckId=<s:property value="emecheckId"/>&memberId=<s:property value="memberId" />','','height=600, width=800, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')">填写情况表</a>
			</s:if>
			&nbsp;
			</td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="toolsmenu" escape="false" /></div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>补录查询页面</title>
</head>
<body>
<s:form theme="simple" action="querymanual" method="post"
	cssStyle="font-size:12px">
	选择地区：<s:select
		name="oid" list="orgs" listKey="orgid" listValue="orgname"></s:select>
	查询条件：
	<s:select value="term" name="term"
		list="#{'':'全部','SSN':'社会保险号','FAMILYNO':'家庭编号','MEMBERNAME':'姓名','PAPERID':'审批流水号'}"
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
<table align="center" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<caption style="font-size: 12px">业务信息</caption>
	<tr>
		<th>编号</th>
		<th>发表编号</th>
		<th>就医医院</th>
		<th>患病名称</th>
		<th>费用总额</th>
		<th>自理费用</th>
		<th>自费金额</th>
		<th>操作</th>
	</tr>
	<s:iterator value="manualapproves">
		<tr>
			<td><s:property value="manualno" /></td>
			<td><s:property value="invoice" /></td>
			<td><s:property value="hospitalname" /></td>
			<td><s:property value="sickname" /></td>
			<td><s:property value="hospitalmoney" default="0" />元</td>
			<td><s:property value="selfmoney" default="0" />元</td>
			<td><s:property value="selfall" default="0" />元</td>
			<td><s:url action="viewmanual" id="view">
				<s:param name="manual.manualId">
					<s:property value="manualId" />
				</s:param>
				<s:param name="manual.ssn">
					<s:property value="ssn" />
				</s:param>
			</s:url> <s:url action="printmanual1" id="print">
				<s:param name="manual.manualId">
					<s:property value="manualId" />
				</s:param>
				<s:param name="manual.ssn">
					<s:property value="ssn" />
				</s:param>
			</s:url><s:a
				href="%{print}" cssStyle="cursor:hand" target="_blank">打印</s:a></td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="toolsmenu" escape="false" /></div>
</body>
</html>
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
<s:form theme="simple" action="queryafter" method="post"
	cssStyle="font-size:12px">
	选择地区：<s:select
		name="oid" list="orgs" listKey="orgid" listValue="orgname"></s:select>
	查询条件：
	<s:select value="term" name="term"
		list="#{'':'全部','SSN':'社会保险号','FAMILYNO':'家庭编号','MEMBERNAME':'姓名','PAPERID':'身份证号码'}"
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
		<th>家庭编号</th>
		<th>姓名</th>
		<th>身份证号码</th>
		<th>医保卡号</th>
		<th>医院名称</th>
		<th>医院级别</th>
		<th>业务类别</th>
		<th>开始时间</th>
		<th>结束时间</th>
		<th>总费用（元）</th>
		<th>报销金额（医保/农合）（元）</th>
		<th>不参与补偿金额（元）</th>
		<th>大病保险金额（元）</th>
		<th>商业保险金额（元）</th>
		<th>救助金额（元）</th>
		<th>救助时间</th>
		<th>保险类型</th>
		<th>审批状态</th>
	</tr>
	<s:iterator value="medicalafters">
		<tr>
			<td width="6%"><s:property value="familyno"/></td>
			<td width="4%"><s:property value="membername"/></td>
			<td width="9%"><s:property value="paperid"/></td>
			<td width="9%"><s:property value="ssn"/></td>
			<td width="13%"><s:property value="hospital"/></td>
			<td width="3%">
				<s:if test="hospitallevel==1">省级</s:if>
				<s:elseif test="hospitallevel==2">市级</s:elseif>
				<s:elseif test="hospitallevel==3">区级</s:elseif>
			</td>
			<td width="3%">
				<s:if test="medicaltype==1">住院</s:if>
				<s:elseif test="medicaltype==2">门诊</s:elseif>
			</td>
			<td width="6%"><s:date name="begintime" format="yyyy-MM-dd"/></td>
			<td width="6%"><s:date name="endtime" format="yyyy-MM-dd"/></td>
			<td width="4%" align="right"><s:property value="totalcost"/></td>
			<td width="4%" align="right"><s:property value="insurepay"/></td>
			<td width="4%" align="right"><s:property value="outpay"/></td>
			<td width="4%" align="right"><s:property value="capay"/></td>
			<td width="4%" align="right"><s:property value="businesspay"/></td>
			<td width="4%" align="right"><s:property value="asisstpay"/></td>
			<td width="6%"><s:date name="updatetime" format="yyyy-MM-dd"/></td>
			<td width="3%">
				<s:if test="insuretype==1">医保</s:if>
				<s:elseif test="insuretype==2">农合</s:elseif>
				<s:elseif test="insuretype==3">其他</s:elseif>
			</td>
			<td width="6%">
				<s:if test="approveresult==1">同意救助</s:if>
				<s:elseif test="approveresult==0">不同意救助</s:elseif>
				<s:elseif test="approveresult==-1">作废</s:elseif>
			</td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="toolsmenu" escape="false" /></div>
</body>
</html>
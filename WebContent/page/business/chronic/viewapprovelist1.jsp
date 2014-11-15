<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.medical.common.FileHandle"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String a=request.getScheme() + "://"
			+ request.getServerName()+"/pic";
			String f =FileHandle.getInstance().webpath1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV='pragma' CONTENT='no-cache'>
<META HTTP-EQUIV='Cache-Control' CONTENT='no-cache, must-revalidate'>
<META HTTP-EQUIV='expires' CONTENT='0'>
<link rel="stylesheet" href="page/css/table-style.css" type="text/css"></link>
<title>查看审批流程</title>
</head>
<body>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>姓名</th>
		<td><s:property value="chronicApproveDTO.name" /></td>
		<th>社会保险号</th>
		<td><s:property value="chronicApproveDTO.ssn" /></td>
	</tr>
	<tr>
		<th>身份证号</th>
		<td><s:property value="chronicApproveDTO.paperid" /></td>
		<th>出生日期</th>
		<td><s:date name="chronicApproveDTO.birthday" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<th>性别</th>
		<td><s:property value="chronicApproveDTO.sex" /></td>
		<th>与户主关系</th>
		<td><s:property value="chronicApproveDTO.relmaster" /></td>
	</tr>
	<tr>
		<th>户主姓名</th>
		<td><s:property value="chronicApproveDTO.masterName" /></td>
		<th>家庭地址</th>
		<td><s:property value="chronicApproveDTO.rpraddress" /></td>
	</tr>
	<tr>
		<th>救助病种</th>
		<td colspan="3"><s:property value="chronicApproveDTO.entityval" /></td>
	</tr>
</table>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<caption>街道审批</caption>
	<tr>
		<th width="180px">审批人</th>
		<td><s:property value="chronicApproveDTO.aprperson1"></s:property></td>
	</tr>
	<tr>
		<th>审批时间</th>
		<td><s:date name="chronicApproveDTO.aprtime1" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<th>审批结果</th>
		<td><s:if test="1==chronicApproveDTO.aprresult1">
		同意
		</s:if> <s:if test="2==chronicApproveDTO.aprresult1">
		不同意
		</s:if></td>
	</tr>
	<tr>
		<th>审批内容</th>
		<td><s:property value="chronicApproveDTO.apridea1"></s:property></td>
	</tr>
</table>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<caption>区县审批</caption>
	<tr>
		<th width="180px">审批人</th>
		<td><s:property value="chronicApproveDTO.aprperson2"></s:property></td>
	</tr>
	<tr>
		<th>审批时间</th>
		<td><s:date name="chronicApproveDTO.aprtime2" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<th>审批结果</th>
		<td><s:if test="1==chronicApproveDTO.aprresult2">
		同意
		</s:if> <s:if test="2==chronicApproveDTO.aprresult2">
		不同意
		</s:if></td>
	</tr>
	<tr>
		<th>审批内容</th>
		<td><s:property value="chronicApproveDTO.apridea2"></s:property></td>
	</tr>
</table>
<!--<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<caption>市级审批</caption>
	<tr>
		<th width="180px">审批人</th>
		<td><s:property value="chronicApproveDTO.aprperson3"></s:property></td>
	</tr>
	<tr>
		<th>审批时间</th>
		<td><s:date name="chronicApproveDTO.aprtime3" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<th>审批结果</th>
		<td><s:if test="1==chronicApproveDTO.aprresult3">
		同意
		</s:if> <s:if test="2==chronicApproveDTO.aprresult3">
		不同意
		</s:if></td>
	</tr>
	<tr>
		<th>审批内容</th>
		<td><s:property value="chronicApproveDTO.apridea3"></s:property></td>
	</tr>
</table>
--><table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>附件</th>
	</tr>
	<tr>
		<td><s:iterator value="hrs">
			<a href="<%=a%>/<%=f%><s:property value="value"/>" target="_blank"><s:property
				value="key" /></a>
		</s:iterator></td>
	</tr>
</table>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>社会保险号</th>
		<th>科目</th>
		<th>收入（元）</th>
		<th>支出（元）</th>
		<th>余额（元）</th>
		<th>时间</th>
		
	</tr>
	<s:iterator value="cbs">
		<tr>
			<td><s:property value="familyId" /></td>
			<td><s:property value="name" /></td>
			<td><s:property value="ssn" /></td>
			<td><s:property value="subject" /></td>
			<td><s:property value="income" /></td>
			<td><s:property value="payout" /></td>
			<td><s:property value="balance" /></td>
			<td><s:date name="opttime" format="yyyy-MM-dd" /></td>
			
		</tr>
	</s:iterator>
</table>
<div align="center">
	<button type="button" onclick="window.close()">关闭</button>
</div>
</body>
</html>
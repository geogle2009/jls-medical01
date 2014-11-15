<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.medical.common.FileHandle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String a = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + "/pic";
	String b = request.getScheme() + "://" + request.getServerName()
	+ ":" + request.getServerPort();
	String f = FileHandle.getInstance().webpath2;
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
<title>因病申请低保审批</title>
</head>
<script type="text/javascript">
function printhr(id){
	 window.open("<%=basePath%>page/business/printasp.action?aspApproveDTO.aspapproveId="+id,null,
	 "height=600,width=800,status=yes,toolbar=no,menubar=no,location=no,scrollbars=yes");
		}
</script>
<body>
<br />
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>姓名</th>
		<td><s:property value="aspApproveDTO.name" /></td>
		<th>社会保险号</th>
		<td><s:property value="aspApproveDTO.ssn" /></td>
	</tr>
	<tr>
		<th>身份证号</th>
		<td><s:property value="aspApproveDTO.paperid" /></td>
		<th>家庭编号</th>
		<td><s:property value="aspApproveDTO.familyId" /></td>
	</tr>
	<tr>
			<th>身份证</th>
			<td colspan="3"><img width="324px" height="204px" alt="身份证 "
				src="<%=a%>/jlsidcard/<s:property value="aspApproveDTO.familyId.substring(0,10)" />/<s:property value="aspApproveDTO.memberId"/>/<s:property value="aspApproveDTO.memberId"/>.jpg">
			</td>
		</tr>
	<tr>
		<th>患病名称</th>
		<td colspan="3"><s:property value="aspApproveDTO.aprmedical" />
		</td>
	</tr>
	
	<s:if test="#session.user.organizationId.length()<=8">
		<tr>
			<th colspan="4">审批详细信息</th>
		</tr>
		<tr>
			<td>街道审批:</td>
			<td><s:if test="aspApproveDTO.aprresult1==1">
			同意
			</s:if> <s:if test="aspApproveDTO.aprresult1==2">
			不同意
			</s:if></td>
			<td><s:property value="aspApproveDTO.apridea1" /></td>
			<td><s:date name="aspApproveDTO.aprtime1" format="yyyy-MM-dd" /></td>
		</tr>
	</s:if>
	<s:if test="#session.user.organizationId.length()<=6">
		<tr>
			<td>区县审批:</td>
			<td><s:if test="aspApproveDTO.aprresult2==1">
			同意
			</s:if> <s:if test="aspApproveDTO.aprresult2==2">
			不同意
			</s:if></td>
			<td><s:property value="aspApproveDTO.apridea2" /></td>
			<td><s:date name="aspApproveDTO.aprtime2" format="yyyy-MM-dd" /></td>
		</tr>
	</s:if>
	<s:if test="#session.user.organizationId.length()<=4">
		<tr>
			<td>市级审批:</td>
			<td><s:if test="aspApproveDTO.aprresult3==1">
			同意
			</s:if> <s:if test="aspApproveDTO.aprresult3==2">
			不同意
			</s:if> <s:if test="aspApproveDTO.aprresult3==3">
			体检中
			</s:if></td>
			<td><s:property value="aspApproveDTO.apridea3" /></td>
			<td><s:date name="aspApproveDTO.aprtime3" format="yyyy-MM-dd" /></td>
		</tr>
	</s:if>
	<tr>
		<th colspan="4">附件</th>
	</tr>
	<tr>
		<td colspan="4"><s:iterator value="hrs">
			<a href="<%=a%>/<%=f%><s:property value="value"/>" target="_blank"><s:property
				value="key" /></a>
		</s:iterator></td>
	</tr>
	<tr>
		<td colspan="4">
		<div align="center">
		<button type="button" onclick="window.close();">关闭</button>
		&nbsp;&nbsp;&nbsp;&nbsp; <s:if
			test="#session.user.organizationId.length()==4">
			<button id="bprint" type="button"
				onclick="printhr(<s:property value="aspApproveDTO.aspapproveId"/>)">打印</button>
		</s:if></div>
		</td>
	</tr>
</table>
</body>
</html>
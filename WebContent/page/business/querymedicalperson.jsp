<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!--
author:Administrator
create time:2009-8-17
-->
<%
	String type = request.getParameter("type");
	String assistype = request.getParameter("assistype");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p align="center" style="font-size: 12px"><br>
<s:form action="queryMedicalFamily" method="post" theme="simple">
家庭编号：<s:textfield name="personDTO.familyno"></s:textfield>
	<br>
	<div align="center"><s:actionerror theme="simple"
		cssStyle="font-size: 12px;color:red" /></div>
	<div align="center"><s:submit value="查询"></s:submit></div>
	<div align="center"><input type="hidden" name="type"
		value="<%=type%>" /> <input type="hidden" name="assistype"
		value="<%=assistype%>" />
		</div>
</s:form></p>
</body>
</html>
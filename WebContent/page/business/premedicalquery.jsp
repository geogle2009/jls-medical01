<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医前救助查询</title>
</head>
<body>
<p align="center" style="font-size: 12px"><br>
<s:form action="queryPreMedical" method="post" theme="simple">
家庭编号：<s:textfield name="personDTO.familyno"></s:textfield>
	<br>
	<div align="center"><s:actionerror theme="simple"
		cssStyle="font-size: 12px;color:red" /></div>
	<div align="center"><s:submit value="查询"></s:submit></div>
	<div align="center"></div>
</s:form></p>
</body>
</html>
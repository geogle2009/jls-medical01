<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结果显示页面</title>
</head>
<body>
<p align="center"><s:if test="pagetype==null">
保存成功
</s:if><s:else>
	<s:property value="pagetype" />
</s:else></p>
<p align="center">
<button onclick="window.opener.location.reload();window.close();">关闭</button>
</p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<s:head theme="xhtml" />
<sx:head parseContent="true" />
<!--
author:Administrator
create time:2009-8-20
-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误信息页面</title>
</head>
<body>
<p style="font-size:12px:color:red">
<s:actionerror/>
</p>
<button onclick="history.go(-1)">返回</button>
</body>
</html>